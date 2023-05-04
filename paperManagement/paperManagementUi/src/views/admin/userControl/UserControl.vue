<template>
    <el-card>
        <el-menu bordered :data-source="showData" class="a-list">
            <div slot="header">
                <h3>用户管理</h3>
            </div>
            <el-divider/>
            <el-row :gutter="20">
                <el-col :span="2">
                    <el-button type="primary" @click="addData()">添加</el-button>
                </el-col>
                <el-col :span="4">
                    <el-input v-model="page.findUsername" placeholder="请输入要查找的内容"></el-input>
                </el-col>
                <el-col :span="9">
                    <el-date-picker v-model="value1"
                                    type="datetimerange"
                                    value-format="YYYY-MM-DD HH:mm:ss"
                                    range-separator="至"
                                    start-placeholder="开始时间"
                                    end-placeholder="结束时间"/>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" @click="getUserList()" :icon="Search"> 查找</el-button>
                </el-col>
            </el-row>
            <br/>
            <br/>
            <el-table :data="showData" border style="width: 100%">
                <el-table-column type="index" width="80" label="序号"/>
                <el-table-column prop="userUsername" label="用户名"/>
                <el-table-column prop="userTelephone" label="用户电话"/>
                <el-table-column prop="userEmail" label="用户邮箱"/>
                <el-table-column prop="dataDescribe" label="操作" width="200" v-slot="slotProps">
                    <el-button type="primary" @click="showInformation(slotProps.row)">
                        <el-icon>
                            <View/>
                        </el-icon>
                    </el-button>
                    <el-button type="warning" @click="editInformation(slotProps.row)">
                        <el-icon>
                            <Edit/>
                        </el-icon>
                    </el-button>
                    <el-popconfirm title="是否删除?" @confirm="deleteData(slotProps.row)">
                        <template #reference>
                            <el-button type="danger">
                                <el-icon>
                                    <Close/>
                                </el-icon>
                            </el-button>
                        </template>
                    </el-popconfirm>
                </el-table-column>
            </el-table>
            <br/>
            <el-pagination
                    v-model:current-page="page.currentPage"
                    v-model:page-size="page.pageSize"
                    :page-sizes="[5, 10, 15, 20]"
                    :small="small"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="page.total"
                    @size-change="getUserList()"
                    @current-change="getUserList()"
            />
        </el-menu>
    </el-card>

    <el-dialog v-model="dialogVisible" title="Tips" width="40%" draggable :before-close="beforeClose">
        <el-form
                ref="ruleFormRef"
                :model="ruleForm"
                status-icon
                label-width="120px"
                class="demo-ruleForm"
                :disabled="fromDisabled"
                :rules="rules"
        >
            <el-form-item label="id" prop="id">
                <el-input placeholder="自动添加" v-model="ruleForm.id" disabled/>
            </el-form-item>
            <el-form-item label="用户名" prop="userUsername">
                <el-input placeholder="请输入用户名" v-model="ruleForm.userUsername"/>
            </el-form-item>
            <el-form-item label="密码" prop="userPassword">
                <el-input placeholder="请输入密码" v-model="ruleForm.userPassword"/>
            </el-form-item>
            <el-form-item label="电话" prop="userTelephone">
                <el-input placeholder="请输入电话" v-model="ruleForm.userTelephone"/>
            </el-form-item>
            <el-form-item label="邮箱" prop="userEmail">
                <el-input placeholder="请输入邮箱" v-model="ruleForm.userEmail"/>
            </el-form-item>
            <el-form-item label="用户权限"  prop="userRoles">
                <el-input placeholder="自动添加" v-model="ruleForm.user_roles">
                    <template #append>
                        <el-select v-model="ruleForm.user_roles" multiple placeholder="Select" style="width: 115px">
                            <el-option label="ROLE_admin" value="ROLE_admin" />
                            <el-option label="ROLE_teacher" value="ROLE_teacher" />
                            <el-option label="ROLE_student" value="ROLE_student" />
                        </el-select>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item label="更新时间" prop="updateTime">
                <el-input placeholder="自动添加" v-model="ruleForm.updateTime" disabled/>
            </el-form-item>
            <el-form-item>
                <div class="flex-grow"></div>
                <el-button @click="ruleFormReset()">取消</el-button>
                <el-button type="primary" @click="ruleFormSubmit(ruleFormRef)">确定</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import * as userApi from "@/api/user.js";
import {Edit, Close, View, Search} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus";
import md5 from "md5";


const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const dialogVisible = ref(false);
const editVisible = ref(false);
const ruleFormRef = ref();
const fromDisabled = ref(false);
let tempPassword = "";
const value1 = ref([
    "",
    ""
])
const value = ref([])
const validateUsername = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('请输入用户名'));
    }
    setTimeout(async () => {
        if (!/^\w{5,12}$/.test(value)) {
            callback(new Error('用户名由5到12位的数字字母和下划线组成'))
        } else {
            const {msg} = await userApi.getUserByUsername({username: value});
            if (msg === "用户不存在") {
                callback()
            } else {
                callback(new Error('用户名已存在'));

            }
        }
    }, 1000)
}


const beforeClose = (done) => {
    fromDisabled.value = false
    done()
}

const rules = reactive({
    userUsername: [{validator: validateUsername, trigger: 'blur'}],
})

const page = reactive({
    currentPage: 1,
    pageSize: 5,
    findUsername: "",
    startTime: "",
    endTime: "",
    total: 1
})
const ruleForm = ref({
    "id": 1,
    "userUsername": "admin",
    "userPassword": "{bcrypt}$2a$10$w77GlW49mY56KnZUzboIXOUZ.Z6FwjqUCyY5h6KjwGS9y0DZoJzRy",
    "userTelephone": "15695912345",
    "userEmail": "1647010108@qq.com",
    "user_roles": "用户权限",
    "updateTime": "更新时间",
})
const showData = ref([{
    "id": 1,
    "userUsername": "admin",
    "userPassword": "{bcrypt}$2a$10$w77GlW49mY56KnZUzboIXOUZ.Z6FwjqUCyY5h6KjwGS9y0DZoJzRy",
    "userTelephone": "15695912345",
    "userEmail": "1647010108@qq.com",
    "user_roles": "用户权限",
    "updateTime": "更新时间",
}])
const deleteData = (value) => {
    const data = userApi.deleteUserByUsername({username: value.userUsername});
    if (data.data < 0) {
        ElMessage.error(data.msg)
    } else {
        ElMessage.success(data.msg)
    }
}
const addData = () => {
    ruleForm.value.id = null;
    ruleForm.value.updateTime = null;
    ruleForm.value.user_roles = null;
    ruleForm.value.userEmail = null;
    ruleForm.value.userUsername = null;
    ruleForm.value.userTelephone = null;
    ruleForm.value.userPassword = null;
    editVisible.value = false;
    dialogVisible.value = true;
    page.currentPage = 1;
    getUserList()
}

// 取消
const ruleFormReset = () => {
    dialogVisible.value = false;
    fromDisabled.value = false
}

// 提交
const ruleFormSubmit = (value) => {
    if (editVisible){
        if (tempPassword === ruleForm.value.userPassword){
            ruleForm.value.userPassword = md5(ruleForm.value.userPassword)
        }
        userApi.putUser(ruleForm.value)
        ElMessage.success("操作成功")
        dialogVisible.value = false;
    }
    value.validate((valid) => {
        if (valid) {
            ruleForm.value.userPassword = md5(ruleForm.value.userPassword)
            userApi.postUser(ruleForm.value)
            dialogVisible.value = false;
            ElMessage.success("添加成功")
        } else {
            return false
        }
    })
}

const showInformation = (value) => {
    console.log(value.user_roles)
    if (!value) return;
    dialogVisible.value = true;
    fromDisabled.value = true;
    ruleForm.value = value
}

const editInformation = (value) => {
    if (!value) return;
    dialogVisible.value = true;
    ruleForm.value = value;
    tempPassword = ruleForm.value.userPassword;
    editVisible.value = true;
}

// 获取列表
const getUserList = async () => {
    if (value1.value != null){
        page.startTime = value1.value[0]
        page.endTime = value1.value[1]
    }else {
        page.startTime = ""
        page.endTime = ""
    }


    const data = await userApi.getUserList(page)
    showData.value = data.data
    page.total = Number(data.msg)
}

// 获取文件下载列表;


onMounted(() => {
    getUserList()
})

</script>


<style scoped lang="scss" src="./UserControl.scss">

</style>
