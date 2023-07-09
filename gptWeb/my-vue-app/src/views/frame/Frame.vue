<template id="app">
    <el-container class="container">
        <el-aside width="200px">
            <el-menu
                    router
                    unique-opened
                    active-text-color="#ffd04b"
                    background-color="#545c64"
                    class="el-menu-vertical-demo"
                    text-color="#fff"
            >

                <template v-for="menu in menuList">
                    <el-menu-item :index="menu.path" :key="menu.id" v-if="!menu.children"
                                  >
                        <el-icon><House /></el-icon>
                        {{ menu.authName }}
                    </el-menu-item>
                    <el-sub-menu v-if="menu.children" :index="menu.id">
                        <template #title>
                            <el-icon><DArrowRight /></el-icon>
                            <span>{{ menu.authName }}</span>
                        </template>
                        <el-menu-item :index="menu2.path" :key="menu2.id" v-for="menu2 in menu.children"
                                     >
                            {{ menu2.authName }}
                        </el-menu-item>
                    </el-sub-menu>
                </template>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <router-view :key="activePath"></router-view>
            </el-main>
            <el-footer>
            </el-footer>
        </el-container>

    </el-container>
</template>
<script setup>
import api from "@/api/api.js";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router";
import {Plus, House, DArrowRight} from "@element-plus/icons-vue"
import {ref, reactive} from "vue";

const menuList = reactive([
    {
        id: 1,
        path: "/ffffff",
        authName: "用户首页",
    },
    {
        id: 2,
        path: "/generalLedger",
        authName: "课题申报",
    },
    {
        id: 3,
        path: "/generalLedger",
        authName: "流程管理",
        children: [
            {
                id: 3 - 1,
                path: "/generalLedger",
                authName: "确认选题",
            },
            {
                id: 3 - 2,
                path: "/generalLedger",
                authName: "流程管理",
            },
            {
                id: 3 - 3,
                path: "/generalLedger",
                authName: "成绩评定",
            }
        ]
    },
    {
        id: 4,
        path: "/generalLedger",
        authName: "历史文库",
    },
    {
        id: 5,
        path: "/generalLedger",
        authName: "文件资料下载",
    }
])

</script>

<style scoped lang="scss" src="./frame.scss">

</style>
