<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>权限管理</el-breadcrumb-item>
    <el-breadcrumb-item>角色列表</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card class="box-card">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-button type="primary" @click="centerDialogVisible = true">添加角色</el-button>
      </el-col>
    </el-row>
    <br>
    <el-table stripe :data="RoleList" border style="width: 100%">
      <!-- 下拉表格框 -->
      <el-table-column type="expand" v-slot:default="scope">
        <el-row v-for="item1 in scope.row.children" :key="item1.id">
          <el-col :span="5">
            <el-tag size="large" @close="deleteRoleRight(scope.row, item1)" closable>{{ item1.authName }}</el-tag>
            <el-icon>
              <CaretRight/>
            </el-icon>
          </el-col>
          <el-col :span="19">
            <el-row v-for="(item2, index) in item1.children" :key="item2.id">
              <el-col :span="6">
                <el-tag type="success" closable @close="deleteRoleRight(scope.row, item2)">{{ item2.authName }}</el-tag>
                <el-icon>
                  <CaretRight/>
                </el-icon>
              </el-col>
              <el-col :span="18">
                <el-tag closable v-for="item3 in item2.children" :key="item3.id" type="info"
                        @close="deleteRoleRight(scope.row, item3)">
                  {{ item3.authName }}
                </el-tag>
              </el-col>
              <el-divider border-style="dotted" v-if="Object.keys(item1.children).length !== (index + 1)"/>
            </el-row>
          </el-col>
          <el-divider border-style="dashed"/>
        </el-row>

      </el-table-column>
      <el-table-column prop="roleName" label="角色名称"/>
      <el-table-column prop="roleDesc" label="角色描述"/>
      <el-table-column prop="操作" label="操作" v-slot:default="scope">
        <el-button type="primary" :icon="Edit" circle @click="editUser(scope.row);editDialogVisible = true;"/>
        <el-popconfirm
            confirm-button-text="Yes"
            cancel-button-text="No"
            :icon="InfoFilled"
            icon-color="#626AEF"
            title="确定要删除？"
            @confirm="deleteUser(scope.row)"
        >
          <template #reference>
            <el-button type="danger" :icon="Delete" circle/>
          </template>
        </el-popconfirm>
        <el-button type="warning" @click="permission(scope.row)">分配权限</el-button>
      </el-table-column>
    </el-table>
  </el-card>
  <br>
  <!-- 添加用户对话框 -->
  <el-dialog
      v-model=centerDialogVisible
      title="添加用户"
      width="50%"
      align-center
  >
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        status-icon
        label-width="auto"
    >
      <el-form-item label="用户" prop="username">
        <el-input v-model="ruleForm.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"/>
      </el-form-item>
      <el-form-item label="手机" prop="mobile">
        <el-input v-model="ruleForm.mobile"/>
      </el-form-item>

    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="ruleFormRef.resetFields()">清除</el-button>
        <el-button type="primary" @click="addUsers()">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑用户对话框 -->
  <el-dialog
      v-model=editDialogVisible
      title="编辑用户"
      width="50%"
      align-center
  >
    <el-form
        ref="editFormRef"
        :model="editForm"
        :rules="rules"
        class="demo-ruleForm"
        status-icon
        label-width="auto"
    >
      <el-form-item label="用户" prop="username">
        <el-input v-model="editForm.username" disabled/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="editForm.email"/>
      </el-form-item>
      <el-form-item label="手机" prop="mobile">
        <el-input v-model="editForm.mobile"/>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editForm.resetFields()">清除</el-button>
        <el-button type="primary" @click="editUserSubmit(); editDialogVisible = false">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <!--分配权限对话框 -->
  <el-dialog
      v-model=permissionVisible
      title="分配权限"
      width="50%"
      align-center
      @close="checkedKeys = []"
  >
    <el-tree
        ref="treeRef"
        :data=rightTree
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        :props="defaultProps"
        :default-checked-keys=checkedKeys

    />

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="">清除</el-button>
        <el-button type="primary" @click="postPermission(); permissionVisible = false">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {Delete, Edit, InfoFilled, CaretRight} from '@element-plus/icons-vue'
import {ref} from "vue";
import {ElMessage} from "element-plus";
import {getUsers} from "@/api/user.js";

const centerDialogVisible = ref(false)
const editDialogVisible = ref(false)
const ruleFormRef = ref()
const editFormRef = ref()
const ruleForm = reactive({
  username: "",
  password: "",
  email: "",
  mobile: "",
})

const rules = reactive({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '长度为3-10', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 3, max: 10, message: '长度为3-10', trigger: 'blur'},
  ],
  email: [
    {
      type: 'email',
      message: '请输入邮箱',
      trigger: ['blur', 'change'],
    },
  ],
  mobile: [{},],
})

async function addUsers() {
  const res = await api.user.addUser(ruleForm)
  if (res.data.meta.status !== 201) {
    return ElMessage.error("添加用户失败！")
  }
  return ElMessage.success("添加用户成功！")
}


</script>
<script>
import api from "@/api/index.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {reactive, ref} from "vue";
import {getUsers} from "@/api/user.js"
export default {
  name: "RoleList",

  data() {
    return {
      permissionVisible: ref(false),
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 4,
      },
      treeRef: ref(),
      roleId: "",
      total: 0,
      RoleList: [
        {
          roleName: "超级管理员",
          roleDesc: "admin",
          children: []
        },
      ],
      editForm: reactive({
        username: "",
        email: "",
        mobile: "",
        id: "",
      }),
      defaultProps: {
        children: 'children',
        label: 'authName',
      },
      rightTree: reactive([
        {
          id: 101,
          authName: '商品管理',
          path: null,
          pid: 0,
          children: [
            {
              id: 104,
              authName: '商品列表',
              path: null,
              pid: 101,
              children: [
                {
                  id: 105,
                  authName: '添加商品',
                  path: null,
                  pid: '104,101'
                }
              ]
            }
          ]
        }
      ]),
      checkedKeys: reactive([])


    }
  },

  methods: {
    // 启动莫名会调用两次待解决
    async getRoleList() {
      const {data: res} = await api.right.getRoleList()
      if (res.meta.status !== 200) {
        return ElMessage.error("获取角色列表失败！")
      }
      this.RoleList = res.data;
    },

    async change(user) {
      const res = await api.user.changeState(user)
      if (res.data.meta.status !== 200) {
        user.mg_state = !user.mg_state
        return ElMessage.error("修改失败！")
      }
      return ElMessage.success("修改成功！")
    },

    async editUserSubmit() {
      const res = await api.user.editUser(this.editForm)
      if (res.data.meta.status !== 200) {
        ElMessage.error("编辑用户失败！")
      }
      await this.getOrders()
      ElMessage.success("编辑用户成功！")
    },

    editUser(user) {
      this.editForm.id = user.id;
      this.editForm.username = user.username;
      this.editForm.email = user.email;
      this.editForm.mobile = user.mobile;
    },

    async deleteUser(user) {
      const res = await api.user.deleteUser(user)
      if (res.data.meta.status !== 200) {
        return ElMessage.error("删除用户失败！")
      }
      this.queryInfo.pagenum = 1;
      await this.getOrders()
      ElMessage.success("删除用户成功！")
    },

    deleteRoleRight(role, right) {
      ElMessageBox.confirm(`这个操作将会删除 ${right.authName} 权限`, "注意", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        const {data: res} = await api.right.deleteRoleRight(role.id, right.id)
        if (res.meta.status !== 200) {
          return ElMessage.error(`删除角色 ${right.authName} 权限失败！`)
        }
        ElMessage.success(`删除角色 ${right.authName} 权限成功！`)
        role.children = res.data
      }).catch(() => {
        return ElMessage.info("已取消删除")
      })
    },

    async getRightTree() {
      const {data: res} = await api.right.getRight("tree")
      if (res.meta.status !== 200) {
        return ElMessage.error("获取权限树失败！")
      }
      this.rightTree = res.data;
    },

    // 打开分配权限对话框
    permission(role) {
      this.getRightTree()
      this.permissionVisible = true
      this.getCheckedKeys(role, this.checkedKeys)
      this.roleId = role.id;
    },

    //提交分配权限
    async postPermission(){
      const keys = [...this.$refs.treeRef.getHalfCheckedKeys(),...this.$refs.treeRef.getCheckedKeys()]
      console.log(keys)
      const {data: res} = await api.right.postPermission(this.roleId, {rids:keys.join(",")})
      if (res.meta.status !== 200) {
        return ElMessage.error("权限分配失败！")
      }
      ElMessage.success("权限分配成功")
      await this.getRoleList()
    },

    //获取所有最后一级权限
    getCheckedKeys(node, arr) {
      if (!node.children) {
        return arr.push(node.id)
      }
      node.children.forEach(node =>{
        this.getCheckedKeys(node, this.checkedKeys)
      })


    }

  },

  mounted() {
    this.getRoleList()
  }
}
</script>

<style scoped>
.el-row {
  display: flex;
  align-items: center;
}


</style>
