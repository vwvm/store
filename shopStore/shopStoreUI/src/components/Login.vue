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
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import {User, View} from "@element-plus/icons-vue";
import api from "@/api/api.js";
import md5 from 'md5'
import {ElMessage} from "element-plus";
import {useUser} from "@/store/index.js";

// 获取全局对象useUser
let user = useUser()
const showLogin = ref(false)
const ruleFormRef = ref()
// 定义表单数据
let ruleForm = reactive({
  username: '',
  password: '',
})
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

// 验证规则
let rules = reactive({
  username: [{validator: validateUsername, trigger: 'blur'}],
  password: [{validator: validatePassword, trigger: 'blur'}],
})

// 提交事件
const submitForm = (formEl) => {
  if (!formEl) return
  formEl.validate(async () => {
    ruleForm.password = md5(ruleForm.password)
    const newVar = await api.__user.getLogin(ruleForm);
    if (newVar.code === 0) {
      ElMessage.success("登录成功")
      showLogin.value = false
      return true
    } else {
      ElMessage.error(newVar.msg)
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

})
</script>

<style scoped lang="scss">
.el-button--primary {
  width: 100%;
}
</style>