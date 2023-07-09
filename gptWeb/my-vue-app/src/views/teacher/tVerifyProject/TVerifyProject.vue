<template>
    <el-card title="选题列表">
        <el-row :gutter="20">
            <el-col :span="4">
                <el-input v-model="page.findStr" placeholder="请输入要查找的学生"></el-input>
            </el-col>
            <el-col :span="9">
                <el-date-picker v-model="timeStartEnd"
                                type="datetimerange"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                range-separator="至"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"/>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="getProjectList()" :icon="Search"> 查找</el-button>
            </el-col>
        </el-row>

        <el-divider/>
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="studentName" label="学生名字"/>
            <el-table-column prop="proposalName" label="开题报告名称"/>
            <el-table-column prop="dataUrl" label="下载文件" v-slot="slotProps">
                <el-button type="primary" :icon="Download" @click="downloadFile(slotProps.row)"/>
            </el-table-column>
            <el-table-column prop="state" label="状态" v-slot="slotProps">
                <template v-if="slotProps.row.state === 0" style="font-size: 20px">
                    <el-icon size="20" color="green">
                        <Check/>
                    </el-icon>
                    通过
                </template>
                <template v-if="slotProps.row.state === 1">
                    <el-icon size="20" color="">
                        <List/>
                    </el-icon>
                    待审核
                </template>
                <template v-if="slotProps.row.state === 2">
                    <el-icon size="20" color="red">
                        <CloseBold/>
                    </el-icon>
                    不通过
                </template>

            </el-table-column>
            <el-table-column prop="dataDescribe" label="操作" width="180" v-slot="slotProps">
                <el-button type="primary" v-if="slotProps.row.state === 1"
                           @click="editClick(slotProps.row, 0)">通过
                </el-button>
                <el-button type="primary"
                           v-if="slotProps.row.state === 1"
                           @click="editClick(slotProps.row,2)">不通过
                </el-button>
            </el-table-column>
        </el-table>
        <br/>
        <el-pagination
                v-model:current-page="page.pageNum"
                v-model:page-size="page.pageSize"
                :page-sizes="[5, 10, 15, 20]"
                :small="small"
                :disabled="disabled"
                :background="background"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="getProjectList()"
                @current-change="getProjectList()"
        />

        <!-- 弹出选题添加窗口 -->
        <el-dialog title="选题添加" v-model="visible" :show-close="false">
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
                    <el-button type="primary" @click="checkForm(true)">通过</el-button>
                    <el-button @click="checkForm(false)">不通过</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!-- 弹出提交窗口 -->
        <el-dialog v-model="isShow" :show-close="false">
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
                    <el-input v-model="editRef.projectName" disabled autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课题来源" prop="age">
                    <el-input disabled v-model="editRef.projectFrom"/>
                </el-form-item>
                <el-form-item label="课题描述" prop="checkPass" disabled>
                    <el-input disabled v-model="editRef.projectDescribe"
                              type="textarea" :autosize="autosize"/>
                </el-form-item>
                <el-form-item>
                    <!--<el-button type="primary" @click="editForm(true)">提交</el-button>-->
                    <!--<el-button @click="editForm(false)">撤回</el-button>-->
                </el-form-item>
            </el-form>
        </el-dialog>
    </el-card>
</template>
<script setup>
import {reactive, ref, onMounted} from 'vue'
import {ElButton, ElDialog} from 'element-plus'
import {CircleCloseFilled, CloseBold, Operation, Check, Sunny, List, Search, Download} from '@element-plus/icons-vue'
import api, {teacherApi, departmentApi, studentApi, projectApi, downloadApi} from "@/api/api.js";
import {useUser, useStudent, useTeacher} from "@/store/index.js";


const user = useUser();
const teacher = useTeacher();
// 展示提交表单
const visible = ref(false)
const ruleFormRef = ref();
// 总数量
const total = ref(1)
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const isEdit = ref(false);
const isShow = ref(false);
// 表列表数据
const tableData = ref([{
    id: 0,
    dataUrl: "下载文件路径",
    proposalName: "开题报告名称",
    studentId: "1",
    studentName: "学生名字",
    state: 0 // "状态 0 通过 1审核 2不通过",
}])
// 开始时间和结束时间字符串
const timeStartEnd = ref([
    "", ""
])
// 请求使用: role 0 管理 1 教师 2 学生
const page = reactive({
    pageNum: 1, pageSize: 5, role: 1, id: teacher.getId, findStr: '', startTime: "",
    endTime: "", teacherId: '', index1: 2, index2: 1
});
// 编辑的内容
const editRef = reactive({
    studentName: "学生名字",
    proposalName: "开题报告名称",
})
// 文本的高度
const autosize = reactive(
    {minRows: 2, maxRows: 8}
)

// 提交
const editClick = async (slotProps, audit_mark) => {
    await downloadApi.putAuditMark({
        id: slotProps.id,
        auditMark: audit_mark,
        studentId: slotProps.studentId,
        studentState: 2
    })
    await getProjectList();
}


const validateProjectName = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('不能为空'));
    }
    callback()
}
const rules = reactive({
    projectName: [{validator: validateProjectName, trigger: 'blur'}],
    projectFrom: [{validator: validateProjectName, trigger: 'blur'}],
    projectDescribe: [{validator: validateProjectName, trigger: 'blur'}],
})
// 下载文件
const downloadFile = (slotProps) => {
    console.log(slotProps.dataUrl)
    window.open("http://localhost:49090/browser/paper/" + slotProps.dataUrl)
}


// 提交审核选题
const checkForm = (value) => {
    editRef.projectState = value ? 21 : 2;
    // 更改选题状态选题
    api.__project.putProject({id: editRef.id, projectState: editRef.projectState});
    if (value) {
        studentApi.putStudent({id: editRef.studentId, teacherId: teacher.getId, studentState: 21})
    }

    isEdit.value = false;
    page.currentPage = 1
    getProjectList()
    isShow.value = false;
}

// 获取列表
const getProjectList = async () => {
    page.startTime = timeStartEnd.value[0];
    page.endTime = timeStartEnd.value[1]
    const {data, msg} = await downloadApi.getDownloadListByTeacher(page);
    total.value = Number.parseInt(msg);
    tableData.value = data
};


onMounted(() => {
    getProjectList()
})

</script>
