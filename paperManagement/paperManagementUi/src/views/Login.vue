<template>
  <el-form :model="loginForm" :rules="rules" label-width="120px" ref="loginFormRef">
    <el-form-item label="账号" prop="username">
      <el-input v-model="loginForm.username"/>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="loginForm.password"/>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm()">登录</el-button>
      <el-button @click="resetFrom()">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {reactive, ref, unref, getCurrentInstance} from 'vue'
import api from "@/api";
import {login} from "@/api/user";
import {ElMessage} from "element-plus";
import router from "@/router";

//  获取挂载的原型
const {proxy} = getCurrentInstance();
//
const loginFormRef = ref(null);

const loginForm = reactive({
  username: '',
  password: '',
})

const rules = reactive({
  username: [
    {required: true, message: '请输入账号', trigger: 'blur'},
    {min: 4, max: 10, message: '账号的长度4-10', trigger: 'blur'},
  ],

  password: [

    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 4, max: 10, message: '密码的长度4-10', trigger: 'blur'},
  ],
})

const submitForm = async () => {
  const form = unref(loginFormRef);
  if (form) {
    await api.user.login(loginForm)
    const res = await login(loginForm)
    console.log("res", res)
    if (res.data.status === 200) {
      //登录成功
      ElMessage({
        message: '登录成功',
        type: 'success',
      })
      window.sessionStorage.setItem("token", res.data.msg)
      await router.push("/home")
    } else {
      ElMessage({
        message: '登录失败',
        type: 'error',
      })
    }
  }
}
const resetFrom = () => {
  const form = unref(loginFormRef);
  if (!form) {
    return
  }
  // loginFormRef.resetFields();
  form.resetFields();
}


</script>


<script>
export default {
  loginUsername: "Login"

}
</script>

<style scoped>

</style>
