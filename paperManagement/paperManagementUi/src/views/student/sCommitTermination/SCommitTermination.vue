<template>
    <el-card>
        <h4> 提交最终搞 </h4>
        <br/>

        <el-descriptions title="" :column="3" border v-if="poAuditMark.auditMark !== 2">
            <el-descriptions-item
                    label="名称"
                    label-align="right"
                    align="center"
                    label-class-name="my-label"
                    class-name="my-content"
                    width="150px">
                {{ poAuditMark.name }}
            </el-descriptions-item
            >
            <el-descriptions-item label="审核状态" label-align="right" align="center"
            >
                <span v-if="poAuditMark.auditMark === 0"><el-icon color="green"><SuccessFilled/></el-icon>通过</span>
                <span v-if="poAuditMark.auditMark === 1"><el-icon color="blue"> <QuestionFilled/></el-icon>待审核</span>
                <span v-if="poAuditMark.auditMark === 2"> <el-icon color="red"><CircleCloseFilled/></el-icon>未通过</span>
            </el-descriptions-item
            >
            <el-descriptions-item label="指导教师" label-align="right" align="center">
                {{ student.getTeacherName }}
            </el-descriptions-item>
            <el-descriptions-item label="下载" label-align="right" align="center">
                <el-button type="primary" @click="downloadFile()"><a :href="minioBaseUrl + poAuditMark.url" download>下载</a>
                </el-button>
            </el-descriptions-item
            >
            <el-descriptions-item label="资源描述" label-align="right" align="center">
                {{ poAuditMark.data_describe }}
            </el-descriptions-item
            >
        </el-descriptions>

        <el-form v-if="checkAuditMark || poAuditMark.auditMark === 2"
                 ref="ruleFormRef"
                 :model="otherParam"
                 status-icon
                 :rules="rules"
                 label-width="120px"
                 class="demo-ruleForm"
                 @validate="showUpload2()"
        >
            <el-form-item label="文件名称" prop="data_name">
                <el-input v-model.number="otherParam.data_name"/>
            </el-form-item>
            <el-form-item label="详细描述" prop="data_describe">
                <el-input v-model.number="otherParam.data_describe" type="textarea"/>
            </el-form-item>
            <el-form-item v-if="showUpload">
                <Upload :otherParam="otherParam"/>
            </el-form-item>
            <el-divider/>
        </el-form>
    </el-card>

</template>
<script setup>
import Upload from "@/components/load/Upload.vue"
import {onMounted, reactive, ref} from "vue";
import {useUser, useStudent} from "@/store/index.js";
import {downloadApi} from "@/api/api.js";
import {Check, SuccessFilled, QuestionFilled, CircleCloseFilled} from "@element-plus/icons-vue"
import {minioBaseUrl} from "@/config/index.js"

const student = useStudent();

const ruleFormRef = ref()
const otherParam = reactive({
    user_id: useUser().getId,
    data_index1: 4,
    data_index2: 1,
    data_name: "",
    data_describe: "",
})
const poAuditMark = ref({
    name: '开题报告1',
    url: '2121&资料&1682564640831.docx',
    auditMark: 2, // 1审核,0通过,2不通过
    data_describe: "资源描述"
});
const checkAuditMark = ref(false);
const showUpload = ref(false)
const showUpload2 = () => {
    if (otherParam.data_name.length !== 0 && otherParam.data_describe.length !== 0) {
        showUpload.value = true;
    }
}

const checkAge = (rule, value, callback) => {
    if (!value) {
        return callback(new Error("请输入内容"))
    }
    callback()
}
const checkDataDescribe = (rule, value, callback) => {
    if (!value) {
        return callback(new Error("请输入内容"))
    }
    callback()
}
const downloadFile = () => {

}

const rules = reactive({
    data_name: [{validator: checkAge, trigger: 'blur'}],
    data_describe: [{validator: checkDataDescribe, trigger: 'blur'}],
})


const getAuditMark = async () => {
    const {code, data} = await downloadApi.getAuditMark(otherParam);
    if (code === 200) {
        checkAuditMark.value = false;
        poAuditMark.value = data;
    } else {
        checkAuditMark.value = true;
    }
}

const resetForm = (formEl) => {
    if (!formEl) return
    formEl.resetFields()
}
onMounted(() => {
    getAuditMark()
})

</script>

<style lang="scss" scoped>

</style>
