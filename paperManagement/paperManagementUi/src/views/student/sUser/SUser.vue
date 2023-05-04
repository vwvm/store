<template>
    <el-card :bordered="false">

        <el-descriptions title="用户信息">
            <el-descriptions-item label="姓名:">{{ student.getStudentName }}</el-descriptions-item>
            <el-descriptions-item label="专业:">{{ student.getMajorName }}</el-descriptions-item>
            <el-descriptions-item label="班级:">{{ student.getStudentClass }}</el-descriptions-item>
            <el-descriptions-item label="信息:">{{ student.getStudentDescribe }}</el-descriptions-item>
            <el-descriptions-item label="成绩:">{{ student.getStudentScore }}</el-descriptions-item>
            <el-descriptions-item label="状态:">{{ stateName }}</el-descriptions-item>
            <el-descriptions-item label="指导教师:">{{ student.getTeacherName }}</el-descriptions-item>
        </el-descriptions>
        <el-divider style="margin-bottom: 32px"/>
        <el-steps :active="type1" align-center>
            <el-step title="选题" :description="student.getStudentState > 0 ? '已完成':'进行中'"/>
            <el-step title="提交稿件" :description="student.getStudentState > 1 ? '已完成':'进行中'" @click=""/>
            <el-step title="Step 3" :description="student.getStudentState > 2 ? '已完成':'进行中'"/>
            <el-step title="Step 4" :description="student.getStudentState > 3 ? '已完成':'进行中'"/>
        </el-steps>
        <br/>
        <br/>
        <div style="height: 300px" v-if="student.getStudentState===0">
            <el-steps direction="vertical" :active="student.getStudentState + 1">
                <el-step title="选题情况" :description="stepDescription1"/>
            </el-steps>
        </div>
        <div style="height: 300px" v-if="student.getStudentState >= 1 && student.getStudentState <= 3">
            <el-steps direction="vertical" :active="student.getStudentState">
                <el-step title="提交开题开题报告" :description="stepDescription21"/>
                <el-step title="提交过程稿件" :description="stepDescription22"/>
                <el-step title="提交的最终稿件" :description="stepDescription23"/>
            </el-steps>
        </div>
        <div style="height: 300px" v-if="student.getStudentState >= 4 && student.getStudentState <= 7">
            <el-steps direction="vertical" :active="student.getStudentState - 4">
                <el-step title="提交开题开题报告" :description="stepDescription21"/>
                <el-step title="提交过程稿件" :description="stepDescription22"/>
                <el-step title="提交的最终稿件" :description="stepDescription23"/>
            </el-steps>
        </div>
    </el-card>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import {useStudent, useUser} from "@/store/index.js";
import {studentApi, projectApi, downloadApi} from "@/api/api.js";

const student = useStudent();
const user = useUser();
const stateName = ref("");
const type1 = ref(0);

const stepDescription1 = ref("已选题：")
const stepDescription21 = ref("开题报告: " + "")
const stepDescription22 = ref("过程稿件:")
const stepDescription23 = ref("最终稿件:")
const stepDescription24 = ref("最终稿件:")
// 获取状态
const state = async () => {
    type1.value = 0;
    student.setStudent(await studentApi.getStudent({id: user.getId}));
    if (student.getStudentState >= 0) {
        stateName.value = "未选题"
    }
    if (student.getStudentState >= 1) {
        type1.value = 1;
        stateName.value = "已选题"
        const {data} = await downloadApi.getDownloadFileName({userId: user.getId, dataIndex1: 2, dataIndex2: 1});
        stepDescription21.value += data
    }
    if (student.getStudentState >= 2) {
        stateName.value = "已提交开题报告"
        // const {data} = await projectApi.selectedProject({studentId: student.getUserId});
        const {data} = await downloadApi.getDownloadFileName({userId: user.getId, dataIndex1: 3, dataIndex2: 1});
        for (let dataKey in data) {
            stepDescription22.value += data[dataKey] + "\n"
        }
    }
    if (student.getStudentState >= 3) {
        type1.value = 1;
        stateName.value = "已提交过程文档"
        const {data} = await downloadApi.getDownloadFileName({userId: user.getId, dataIndex1: 4, dataIndex2: 1});
        stepDescription23.value += data
    }
    if (student.getStudentState >= 3) {
        type1.value = 2;
        stateName.value = "已提交最终稿"
        const {data} = await downloadApi.getDownloadFileName({userId: user.getId, dataIndex1: 2, dataIndex2: 1});
        stepDescription24.value += data
    }
}

onMounted(() => {
    state()
})

</script>

<style scoped lang="scss" src="./SUser.scss">

</style>
