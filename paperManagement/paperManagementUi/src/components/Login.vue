<template>
    <el-dialog
            v-model="showLogin"
            title="登录"
            width="30%"
            :before-close="handleClose"
    >
        <el-form
                ref="ruleFormRef"
                :model="ruleForm"
                status-icon
                :rules="rules"
        >
            <el-form-item prop="username">
                <el-input :prefix-icon="User" placeholder="请输入账号" v-model.number="ruleForm.username"/>
            </el-form-item>
            <el-form-item prop="password">
                <el-input :prefix-icon="View" placeholder="请输入密码" v-model="ruleForm.password" type="password"
                          autocomplete="off"/>
            </el-form-item>
            <el-form-item prop="captcha">
                <el-row :gutter="5">
                    <el-col :span="18">
                        <el-input :prefix-icon="CaretTop" placeholder="请输入验证码" v-model="ruleForm.captcha"
                                  autocomplete="off"/>
                    </el-col>
                    <el-col :span="6" style="display: flex">
                        <el-image :src="captcha.captchaImg" @click="getCaptcha()"></el-image>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
            </el-form-item>
        </el-form>

    </el-dialog>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import {User, View, CaretTop} from "@element-plus/icons-vue";
import api from "@/api/api.js";
import md5 from 'md5'
import {ElMessage} from "element-plus";
import {useUser} from "@/store/index.js";
import router from "@/router/index.js";


const captcha = ref({
    captchaImg: "",
    token: '',
});

// 获取全局对象useUser
const user = useUser()
const showLogin = ref(false)
const ruleFormRef = ref()
// 定义表单数据
const ruleForm = reactive({
    username: '',
    password: '',
    token:  '',
    captcha: '',
})
// 获取验证码
const getCaptcha = async () => {
    captcha.value = await api.__captcha.getCaptcha();
    ruleForm.token = captcha.value.token;
    console.log(captcha)
}
// 验证用户名
const validateUsername = (rules, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        if (/^\w{5,12}$/.test(value)) {
            callback()
        } else {
            callback(new Error("用户名由字母，数字，下划线，5-12位组成"))
        }
    }
}
// 验证密码
const validatePassword = (rules, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        if (/^\w{5,12}$/.test(value)) {
            callback()
        } else {
            callback(new Error("密码由字母，数字，下划线，5-12位组成"))
        }
    }
}
// 验证验证码
const validateCaptcha = (rules, value, callback) => {
    if (value === '') {
        callback(new Error('请输入验证码'))
    } else {
        if (/^\w{5}$/.test(value)) {
            callback()
        } else {
            callback(new Error("验证码格式不正确"))
        }
    }
}

// 验证规则
let rules = reactive({
    username: [{validator: validateUsername, trigger: 'blur'}],
    password: [{validator: validatePassword, trigger: 'blur'}],
    captcha: [{validator: validateCaptcha, trigger: 'blur'}],
})

const fromMessage = {
    username: '',
    password: '',
    token:  '',
    captcha: '',
}

// 提交事件
const submitForm = (formEl) => {
    if (!formEl) return
    formEl.validate(async () => {
        fromMessage.password = md5(ruleForm.password)
        fromMessage.username = ruleForm.username
        fromMessage.token = ruleForm.token
        fromMessage.captcha = ruleForm.captcha
        const newVar = await api.__user.postLogin(fromMessage);
        console.log(newVar)
        if (newVar.code === 200) {
            ElMessage.success("登录成功")
            useUser().setUser(newVar)
            localStorage.setItem("token", user.getToken)
            showLogin.value = false
            const roles = useUser().user.role;
            if (roles.indexOf("ROLE_Admin")){
                await router.push("/admin")
                return true
            }
            if (roles.indexOf("ROLE_Teacher")){
                await router.push("/teacher")
                return true
            }
            if (roles.indexOf("ROLE_Student")){
                await router.push("/student")
                return true
            }
            await router.push("/home")
            return true
        } else {
            ElMessage.error("登录失败")
            return false
        }
    })
}

// 关闭对话框
const handleClose = (done) => {
    ruleFormRef.value.resetFields()
    done()
}
// 暴露组件成员
defineExpose({
    showLogin
})
onMounted(() => {
    getCaptcha()
})

</script>

<style scoped lang="scss">
.el-button--primary {
  width: 100%;
}
</style>