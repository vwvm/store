<template>
    <el-card title="学生列表">
        <el-row :gutter="20">
            <el-col :span="2">
                <el-button type="primary" @click="resetForm(); visible = true">选题添加</el-button>
            </el-col>
            <el-col :span="4">
                <el-input v-model="page.findUsername" placeholder="请输入要查找的内容"></el-input>
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
            <el-table-column prop="projectName" label="课题名称"/>
            <el-table-column prop="projectFrom" label="课题来源"/>
            <el-table-column prop="updateTime" label="更新时间"/>
            <el-table-column prop="studentName" label="指导学生"/>
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
                    <el-icon size="20" color="red">
                        <CloseBold/>
                    </el-icon>
                    不通过
                </template>
                <template v-if="slotProps.row.projectState === 21">
                    <el-icon size="20" color="green">
                        <Sunny/>
                    </el-icon>
                    已选
                </template>

            </el-table-column>
            <el-table-column prop="dataDescribe" label="操作" width="180" v-slot="slotProps">
                <el-button type="primary" v-if="slotProps.row.projectState === 1"
                           :icon="Operation"
                           @click="editClick(slotProps.row)">审核
                </el-button>
                <el-button type="primary"
                           v-if="slotProps.row.projectState === 0 || slotProps.row.projectState === 21 || slotProps.row.projectState === 2"
                           :icon="Search"
                           @click="showClick(slotProps.row)">查看选题
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
            <el-form
                    ref="ruleFormRef"
                    :model="formRef"
                    status-icon
                    label-width="120px"
                    class="demo-ruleForm"
                    :rules="rules"
            >
                <el-form-item label="课题名称" prop="projectName">
                    <el-input v-model="formRef.projectName" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课题来源" prop="projectFrom">
                    <el-input v-model="formRef.projectFrom"/>
                </el-form-item>
                <el-form-item label="课题描述" prop="projectDescribe">
                    <el-input v-model="formRef.projectDescribe"
                              type="textarea" :autosize="autosize"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
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
import {CircleCloseFilled, CloseBold, Operation, Check, Sunny, List, Search} from '@element-plus/icons-vue'
import api, {teacherApi, departmentApi, studentApi, projectApi} from "@/api/api.js";
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
const tableData = ref([{
    "id": 1,
    "projectName": "课题名称",
    "projectDescribe": "课题表述",
    "projectFrom": "课题来源",
    "teacherId": "1",
    "teacherName": "教师名字",
    "studentId": "1",
    "studentName": "学生名字",
    "projectState": "该选题的状态，默认状态为0,有老师和学生，该课题已选，有老师没学生可以选；为1时，待审核，不可选；选题状态2，不可选。",
    "creationTime": "2023-04-07T04:05:57",
    "updateTime": "2023-04-07T04:05:57",
    "updateUserId": null,
    "deleteFlag": false
}])
const timeStartEnd = ref([
    "", ""
])
// role 0管理 1教师 2学生
const page = reactive({
    pageNum: 1, pageSize: 5, role: 1, id: teacher.getId, findName: '', startTime: "",
    endTime: "",
});
// 提交的选题内容
const formRef = reactive({
    // id: 0,
    projectName: '',
    projectDescribe: '',
    projectFrom: '',
    projectState: 1,
    teacherId: '',
    studentId: '',
})
const editRef = reactive({
    id: 0,
    projectName: '',
    projectDescribe: '',
    projectFrom: '',
    projectState: 0,
    teacherId: '',
    studentId: '',
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
    formRef.teacherId = teacher.getId;
}

const editClick = (slotProps) => {
    editRef.id = slotProps.id
    editRef.projectFrom = slotProps.projectFrom
    editRef.projectDescribe = slotProps.projectDescribe
    editRef.projectName = slotProps.projectName
    editRef.studentId = slotProps.studentId
    editRef.teacherId = slotProps.teacherId

    isEdit.value = true;
}

// 查看选题
const showClick = (slotProps) => {
    editRef.id = slotProps.id
    editRef.projectFrom = slotProps.projectFrom
    editRef.projectDescribe = slotProps.projectDescribe
    editRef.projectName = slotProps.projectName
    editRef.studentId = slotProps.studentId
    editRef.teacherId = slotProps.teacherId

    isShow.value = true;
}

// 动态加载教师
let id = 0
const props = reactive({
    label: "departmentName",
    value: "id",
    lazy: true,
    lazyLoad: async (node, resolve) => {
        if (node.level === 0) {
            resolve(await getDepartmentList())
        }
        if (node.level === 1) {
            let teacherList = await getTeacherList(node.value);
            teacherList = teacherList.map((x) => ({
                id: x.id,
                departmentName: x.teacherName,
                leaf: true
            }));
            resolve(teacherList);
        }
    }
})
const validateProjectName = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('不能为空'));
    }
    callback()
}
const validateSelectTeacher = (rule, value, callback) => {
    if (!formRef.teacherId) {
        return callback(new Error('请选择教师'));
    }
    callback()
}
const rules = reactive({
    projectName: [{validator: validateProjectName, trigger: 'blur'}],
    projectFrom: [{validator: validateProjectName, trigger: 'blur'}],
    projectDescribe: [{validator: validateProjectName, trigger: 'blur'}],
    teacherId: [{validator: validateSelectTeacher, trigger: 'blur'}],
})

// 提交选题
const submitForm = (value) => {
    if (!value) return
    value.validate((valid) => {
        if (valid) {
            formRef.teacherId = teacher.getId;
            formRef.projectState = 0;
            api.__project.postProject(formRef);
            isEdit.value = false;
            page.currentPage = 1
            getProjectList()
            visible.value = false;
        } else {
            console.log('error submit!')
            visible.value = false;
        }
    })

}
// 获取学院部门列表
const getDepartmentList = async () => {
    const {data} = await departmentApi.getDepartmentList();
    return data
}
const getTeacherList = async (value) => {
    const {data} = await teacherApi.getTeacherList({departmentId: value});
    return data;
}

// 提交审核选题
const checkForm = (value) => {
    editRef.projectState = value ? 21 : 2;
    console.log("editRef", editRef)
    // 更改选题状态选题
    api.__project.putProject({id: editRef.id, projectState: editRef.projectState});
    if (value){
        studentApi.putStudent({id: editRef.studentId, teacherId: teacher.getId, studentState: 21})
    }

    isEdit.value = false;
    page.currentPage = 1
    getProjectList()
    isShow.value = false;
}
// 获取课题列表
const getProjectList = async () => {
    page.startTime = timeStartEnd.value[0];
    page.endTime = timeStartEnd.value[1]
    const {data, msg} = await studentApi.getStudent(page);
    total.value = Number.parseInt(msg);
    tableData.value = data
};


onMounted(() => {
    getProjectList()
})

</script>


