<template>
    <el-card title="选题列表">
        <el-button @click="resetForm(); visible = true">
            新增选题
        </el-button>

        <el-divider/>
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="projectName" label="课题名称"/>
            <el-table-column prop="projectFrom" label="课题来源"/>
            <el-table-column prop="updateTime" label="更新时间"/>
            <el-table-column prop="projectState" label="状态" v-slot="slotProps">
                <template v-if="slotProps.row.projectState === 0" style="font-size: 20px">
                    <el-icon size="20" color="green">
                        <Check/>
                    </el-icon>
                    可选
                </template>
                <template v-if="slotProps.row.projectState === 1">
                    <el-icon size="20" color="">
                        <List/>
                    </el-icon>
                    待审核
                </template>
                <template v-if="slotProps.row.projectState === 2">
                    <el-icon size="20" color="green">
                        <Sunny/>
                    </el-icon>
                    被选择
                </template>
                <template v-if="slotProps.row.projectState === 3">
                    <el-icon size="20" color="red">
                        <CloseBold/>
                    </el-icon>
                    不通过
                </template>
            </el-table-column>
            <el-table-column prop="dataDescribe" label="操作" width="180" v-slot="slotProps">
                <el-button type="primary" :icon="Operation" @click="editClick(slotProps.row)"/>
            </el-table-column>
        </el-table>
        <br/>
        <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[5, 10, 15, 20]"
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="getProjectList()"
                @current-change="getProjectList()"
        />

        <!-- 弹出添加窗口 -->
        <el-dialog v-model="visible" :show-close="false">
            <template #header="{ close, titleId, titleClass }">
                <div class="my-header">
                    <h4 :id="titleId" :class="titleClass">选题</h4>
                    <el-button type="danger" @click="close">
                        <el-icon class="el-icon--left">
                            <CircleCloseFilled/>
                        </el-icon>
                        关闭
                    </el-button>
                </div>
            </template>
            <el-form
                    ref="ruleFormRef"
                    :model="formRef"
                    status-icon
                    label-width="120px"
                    class="demo-ruleForm"
            >
                <el-form-item label="课题名称" prop="pass">
                    <el-input v-model="formRef.projectName" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课题来源" prop="age">
                    <el-input v-model="formRef.projectFrom"/>
                </el-form-item>
                <el-form-item label="课题描述" prop="checkPass">
                    <el-input v-model="formRef.projectDescribe"
                              type="textarea" :autosize="autosize"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm()">清除</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!-- 弹出编辑窗口 -->
        <el-dialog v-model="isEdit" :show-close="false">
            <template #header="{ close, titleId, titleClass }">
                <div class="my-header">
                    <h4 :id="titleId" :class="titleClass">选题</h4>
                    <el-button type="danger" @click="close">
                        <el-icon class="el-icon--left">
                            <CircleCloseFilled/>
                        </el-icon>
                        关闭
                    </el-button>
                </div>
            </template>
            <el-form
                    ref="ruleFormRef"
                    :model="editRef"
                    status-icon
                    label-width="120px"
                    class="demo-ruleForm"
            >
                <el-form-item label="课题名称" prop="pass">
                    <el-input v-model="editRef.projectName" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课题来源" prop="age">
                    <el-input v-model="editRef.projectFrom"/>
                </el-form-item>
                <el-form-item label="课题描述" prop="checkPass">
                    <el-input v-model="editRef.projectDescribe"
                              type="textarea" :autosize="autosize"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="editForm(true)">通过</el-button>
                    <el-button @click="editForm(false)">不通过</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </el-card>
</template>
<script setup>
import {reactive, ref, onMounted} from 'vue'
import {ElButton, ElDialog} from 'element-plus'
import {CircleCloseFilled, CloseBold, Operation, Check, Sunny, List} from '@element-plus/icons-vue'
import api from "@/api/api.js";
// 展示提交表单
const visible = ref(false)
const currentPage = ref(1)
// 页码大小
const pageSize = ref(5)
// 总数量
const total = ref(1)

const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const isEdit = ref(false);

const tableData = ref([{
    "id": 1,
    "projectName": "课题名称",
    "projectDescribe": "课题表述",
    "projectFrom": "课题来源",
    "teacherId": "1",
    "studentId": "2",
    "projectState": "该选题的状态，默认状态为0,该课题可选；为1时，待审核，不可选；选题状态2，已被选择，不可选。3不通过",
    "creationTime": "2023-04-07T04:05:57",
    "updateTime": "2023-04-07T04:05:57",
    "updateUserId": null,
    "deleteFlag": false
}])

// 提交的内容
const formRef = reactive({
    // id: 0,
    projectName: '',
    projectDescribe: '',
    projectFrom: '',
})
const editRef = reactive({
    id: 0,
    projectName: '',
    projectDescribe: '',
    projectFrom: '',
    projectState: 0,
})
// 文本的高度
const autosize = reactive(
    {minRows: 2, maxRows: 8}
)

// 重置提交
const resetForm = () => {
    formRef.projectName = '';
    formRef.projectDescribe = '';
    formRef.projectFrom = '';
}

const editClick = (slotProps) => {
    editRef.id = slotProps.id
    editRef.projectFrom = slotProps.projectFrom
    editRef.projectDescribe = slotProps.projectDescribe
    editRef.projectName = slotProps.projectName
    isEdit.value = true;
}

// 提交选题
const submitForm = () => {
    const {data} = api.__project.postProject(formRef);
    isEdit.value = false;
    currentPage.value = 1
    getProjectList()
}
// 提交编辑选题
const editForm = (value) => {
    editRef.projectState = value ? 0 : 3;
    console.log(editRef)
    const {data} = api.__project.putProject(editRef);
    isEdit.value = false;
    currentPage.value = 1
    getProjectList()
}
// 获取课题列表
const getProjectList = async () => {
    const page = {pageNum: currentPage.value, pageSize: pageSize.value};
    const {data, msg} = await api.__project.getProjectList(page);
    total.value = Number.parseInt(msg);
    tableData.value = data
};


onMounted(() => {
    getProjectList()
})

</script>

<style scoped lang="scss" src="./TProject.scss">

</style>
