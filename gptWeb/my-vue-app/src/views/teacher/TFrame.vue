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
                    default-active="1"
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
                <router-view></router-view>
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
        path: "/tUser",
        authName: "用户首页",
    },
    {
        id: 2,
        path: "/tProject",
        authName: "课题管理",
    },
    {
        id: 3,
        path: "/teacher",
        authName: "流程管理",
        children: [
            {
                id: 3 - 1,
                path: "/tVerifyProject",
                authName: "审核开题报告",
            },
            {
                id: 3 - 2,
                path: "/tSubmitManuscript",
                authName: "审核过程文档",
            },
            {
                id: 3 - 3,
                path: "/tCommitTermination",
                authName: "审核最终稿",
            }
        ]
    },
    {
        id: 4,
        path: "/tStudentManage",
        authName: "学生管理",
    },
    {
        id: 5,
        path: "/tLibrary",
        authName: "历史文库",
    },
    {
        id: 6,
        path: "/tDownload",
        authName: "文件资料下载",
    }
])

</script>

<style scoped lang="scss" src="./frame.scss">

</style>
