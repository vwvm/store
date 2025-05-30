<template>
  <el-card>
    <el-menu bordered :data-source="showData" class="a-list">
      <div slot="header">
        <h3>学生管理</h3>
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
        <el-table-column prop="studentName" label="学生名字"/>
        <el-table-column prop="studentName" label="学生所在学院" v-slot="slotProps">
                    <span v-for="item in departmentList">
                        <span v-if="item.id === slotProps.row.majorId">
                               {{ item.departmentName }}
                        </span>
                    </span>
        </el-table-column>
        <el-table-column prop="studentClass" label="学生班级"/>
        <el-table-column prop="studentDescribe" label="学生描述"/>
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
      <el-form-item label="学生名字" prop="userUsername">
        <el-input placeholder="请输入姓名" v-model="ruleForm.userUsername"/>
      </el-form-item>
      <el-form-item label="学生班级" prop="userPassword">
        <el-cascader
            style="width: 100%"
            v-model="formRef.teacherId"
            :props="props" placeholder="请选择教师"/>
        <el-input placeholder="请选择院系" v-model="ruleForm.userPassword"/>
      </el-form-item>
      <el-form-item label="学生描述" prop="userTelephone">
        <el-input placeholder="请输入学生所在班级" v-model="ruleForm.userTelephone"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="userEmail">
        <el-input placeholder="请输入学生描述" v-model="ruleForm.userEmail"/>
      </el-form-item>
      <el-form-item label="用户权限" prop="userRoles">
        <el-input placeholder="自动添加" v-model="ruleForm.userRoles" disabled/>
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
import {Edit, Close, View, Search} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus";
import {departmentApi, studentApi, userApi} from "@/api/api.js";


const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const dialogVisible = ref(false);
const ruleFormRef = ref()
const fromDisabled = ref(false)
const findData = ref("");
const value1 = ref([
  "",
  ""
])

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
  "userRoles": "用户权限",
  "updateTime": "更新时间",
})
const showData = ref([{
  "id": 1,
  "userUsername": "admin",
  "userPassword": "{bcrypt}$2a$10$w77GlW49mY56KnZUzboIXOUZ.Z6FwjqUCyY5h6KjwGS9y0DZoJzRy",
  "userTelephone": "15695912345",
  "userEmail": "1647010108@qq.com",
  "userRoles": "用户权限",
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
  ruleForm.value.userRoles = null;
  ruleForm.value.userEmail = null;
  ruleForm.value.userUsername = null;
  ruleForm.value.userTelephone = null;
  ruleForm.value.userPassword = null;
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
  value.validate((valid) => {
    if (valid) {
      userApi.putUser(ruleForm.value)
      dialogVisible.value = false;
      ElMessage.success("添加成功")
    } else {
      return false
    }
  })
}

const showInformation = (value) => {
  if (!value) return;
  dialogVisible.value = true;
  fromDisabled.value = true;
  ruleForm.value = value
}

const editInformation = (value) => {
  if (!value) return;
  dialogVisible.value = true;
  ruleForm.value = value

}

const departmentList = ref({});
// 获取院系
const getDepartment = async () => {
  const {data} = await departmentApi.getDepartmentList()
  departmentList.value = data
  console.log(" departmentList.value", departmentList.value)
}

// 获取列表
const getUserList = async () => {
  if (value1.value != null) {
    page.startTime = value1.value[0]
    page.endTime = value1.value[1]
  } else {
    page.startTime = ""
    page.endTime = ""
  }
  console.log(page)
  const data = await studentApi.getStudentList(page)
  showData.value = data.data
  page.total = Number(data.msg)
}
const notAddStudentList = ref()
// 获取未添加学生列表;
const getNotStudentList = async () => {
  console.log("1234")
  if (value1.value != null) {
    page.startTime = value1.value[0]
    page.endTime = value1.value[1]
  } else {
    page.startTime = ""
    page.endTime = ""
  }
  const {data} = await studentApi.getNotStudentList(page)
  notAddStudentList.value = data
  console.log(data)
}

onMounted(() => {
  getUserList()
  getDepartment()
  getNotStudentList()
})

</script>

<style scoped lang="scss" src="./StudentControl.scss">

</style>
