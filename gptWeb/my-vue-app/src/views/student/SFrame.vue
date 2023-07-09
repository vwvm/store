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
                    <el-menu-item :index="menu.path" :disabled="menu.disabled" :key="menu.id" v-if="!menu.children"
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
                                      :disabled="menu2.disabled"
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
import {useUser, useStudent} from "@/store/index.js";

const menuList = reactive([
    {
        id: 1,
        path: "/sUser",
        authName: "用户首页",
        disabled: false,
    },
    {
        id: 2,
        path: "/sProject",
        authName: "选题管理",
        disabled: useStudent().getStudentState >= 1,
    },
    {
        id: 3,
        path: "/subProcess",
        authName: "流程管理",
        children: [
            {
                id: 3 - 1,
                path: "/sOpeningReport",
                authName: "提交开题报告",
                disabled: useStudent().getStudentState >= 2,
            },
            {
                id: 3 - 2,
                path: "/sSubmitManuscript",
                authName: "提交稿件",
                disabled: useStudent().getStudentState >= 3,
            },
            {
                id: 3 - 3,
                path: "/sCommitTermination",
                authName: "提交最终搞",
            }
        ]
    },
    {
        id: 4,
        path: "/sDefenseManagement",
        authName: "答辩管理",
        disabled: useStudent().getStudentState >= 1,
        children: [
            {
                id: 4 - 1,
                path: "/sDefenseManagement/showDefense",
                authName: "查看答辩安排",
            },
            {
                id: 4 - 2,
                path: "/sDefenseManagement/submitDefense",
                authName: "提交答辩记录",
            },
        ]
    },
    {
        id: 5,
        path: "/sDownload",
        authName: "文件资料下载",
        disabled: useStudent().getStudentState >= 1,
    }
])

</script>

<style scoped lang="scss" src="./SFrame.scss">

</style>
