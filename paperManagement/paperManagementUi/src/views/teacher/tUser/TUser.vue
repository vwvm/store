<template>
    <el-card :bordered="false">

        <el-descriptions title="用户信息">
            <el-descriptions-item label="姓名">{{ teacher.teacherName }}</el-descriptions-item>
            <el-descriptions-item label="部门">{{ 3 }}</el-descriptions-item>
            <el-descriptions-item label="信息">{{ teacher.teacherDescribe }}</el-descriptions-item>
            <el-descriptions-item label="职称">{{ teacher.teacherRank }}</el-descriptions-item>
        </el-descriptions>
        <el-divider style="margin-bottom: 32px"/>

    </el-card>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import {teacherApi} from "@/api/api.js";
import {useUser} from "@/store/index.js";

const user = useUser();

const teacher = ref({
    "creationTime": "2023-04-11T00:26:56",
    "deleteFlag": false,
    "departmentId": 1,
    "id": 1,
    "teacherDescribe": "用来测试使用的",
    "teacherName": "小明",
    "teacherProjectNum": 1,
    "teacherRank": "未知",
    "updateTime": "2023-04-11T00:26:56",
    "updateUserId": null,
    "userId": 1
})

const getTeacher = async () => {
    teacher.value = await teacherApi.getTeacher({id: user.getId})
    console.log(teacher.value)
}

onMounted(() => {
    getTeacher();
})


</script>

<style scoped lang="scss" src="./TUser.scss">

</style>