<template>
  <el-dialog
      v-model="showRegister"
      title="注册"
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
        <el-input :prefix-icon="View" placeholder="请输入密码" v-model="ruleForm.password" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item prop="rePassword">
        <el-input :prefix-icon="View" placeholder="请确认密码" v-model="ruleForm.rePassword" type="password" autocomplete="off"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">注册</el-button>
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

const showRegister = ref(false)
const ruleFormRef = ref()
// 定义表单数据
let ruleForm = reactive({
  username: '',
  password: '',
  rePassword: '',
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
// 验证确认密码
const validateRePassword = (rules, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== ruleForm.password) {
    callback(new Error("两次密码必须一致"))
  } else {
    callback()
  }
}

// 验证规则
let rules = reactive({
  username: [{validator: validateUsername, trigger: 'blur'}],
  password: [{validator: validatePassword, trigger: 'blur'}],
  rePassword: [{validator: validateRePassword, trigger: 'blur'}],
})

// 提交事件
const submitForm = (formEl) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    ruleForm.password = md5(ruleForm.password)
    ruleForm.rePassword = md5(ruleForm.rePassword)
    const newVar = await api.__user.getRegister(ruleForm);
    if (newVar.code === 0) {
      ElMessage.success(newVar.msg)
      showRegister.value = false
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
  showRegister
})

onMounted(()=>{

})
</script>

<style scoped lang="scss">
.el-button--primary {
  width: 100%;
}
</style>