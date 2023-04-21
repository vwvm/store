<template>
    <el-card :bordered="false">

        <el-descriptions title="用户信息">
            <el-descriptions-item label="姓名">{{ curUserName }}</el-descriptions-item>
            <el-descriptions-item label="电话">{{ curUserTel }}</el-descriptions-item>
            <el-descriptions-item label="部门">{{ teacherData.deptId }}</el-descriptions-item>
            <el-descriptions-item label="信息">{{ teacherData.teacherDescribe }}</el-descriptions-item>
            <el-descriptions-item label="职称">{{ teacherData.zhicheng }}</el-descriptions-item>
        </el-descriptions>
        <el-divider style="margin-bottom: 32px"/>

    </el-card>
</template>

<script setup>
import {ref, reactive} from "vue";

const curUserId = ref("");
const curUserName = ref("")
const curUserTel = ref("")
const teacherData = reactive({deptId: '', teacherDescribe: '', zhicheng: ''})

</script>

<style scoped lang="scss" src="./SUser.scss">

</style>
