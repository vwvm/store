<template>
    <el-menu
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#fff"
    >
        <el-menu-item index="/home">
            <router-link to="/home2">
                <p style="font-size: 30px">{{}}毕业论文管理系统</p>
            </router-link>
        </el-menu-item>
        <div class="flex-grow"/>
        <el-menu-item @click="showLogin()" v-if="!useUser().user.username">
            登录
        </el-menu-item>
        <el-sub-menu v-if="useUser().user.username">
            <template #title>{{ useUser().getUsername }}</template>
            <el-menu-item @click="loginOut()">登出
            </el-menu-item>
        </el-sub-menu>
    </el-menu>
    <Register ref="showRegisterRef"/>
    <Login ref="showLoginRef"/>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import Register from "@/components/Register.vue";
import Login from "@/components/Login.vue";
import {useUser} from "@/store/index.js";

const router = useRouter()

// 绑定是否显示注册登录Ref
const showRegisterRef = ref(null)
const showLoginRef = ref(null)
// 是否显示注册
const showRegister = () => {
    showRegisterRef.value.showRegister = true
}
const showLogin = () => {
    showLoginRef.value.showLogin = true
    console.log("click this")
}
const loginOut = () => {
    useUser().empty();
    localStorage.removeItem('token');
    router.push('/home')
}

const openOrder = () => {
    router.push('/order')
}
const openCollect = () => {
    router.push('/collect')
}
const openShoppingCart = () => {
    router.push('/shoppingCart')
}
</script>

<style scoped lang="scss" src="./header.scss"></style>