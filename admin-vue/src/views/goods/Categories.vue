<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    <el-breadcrumb-item>商品分类</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card class="box-card">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-button type="primary" @click="centerDialogVisible = true">添加商品分类</el-button>
      </el-col>
    </el-row>
    <br>
    <el-table stripe :data="categoriesList" border style="width: 100%">
      <!-- 下拉表格框 -->
      <el-table-column type="expand" v-slot:default="scope">
        <el-row v-for="item1 in scope.row.children" :key="item1.id">
          <el-col :span="5">
            <el-tag size="large" @close="deleteRoleRight(scope.row, item1)" closable>{{ item1.authName }}</el-tag>
          </el-col>
          <el-col :span="19">
            <el-row v-for="(item2, index) in item1.children" :key="item2.id">
              <el-col :span="6">
                <el-tag type="success" closable @close="deleteRoleRight(scope.row, item2)">{{ item2.authName }}</el-tag>
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
      <el-table-column prop="cat_name" label="分类名称"/>
      <el-table-column prop="cat_deleted" label="是否有效" v-slot:default="scope">
          <el-switch
              v-model=scope.row.cat_deleted
              class="mt-2"
              style="margin-left: 24px"
              inline-prompt
              @change="change(scope.row)"
          />
      </el-table-column>
      <el-table-column prop="cat_level" label="分类级别" v-slot:default="scope">
        <el-tag v-if="scope.row.cat_level === 0">级别一</el-tag>
        <el-tag class="ml-2" type="success" v-if="scope.row.cat_level === 1">级别二</el-tag>
        <el-tag class="ml-2" type="info" v-if="scope.row.cat_level === 2">级别三</el-tag>
      </el-table-column>
      <el-table-column prop="操作" label="操作" v-slot:default="scope">
        <el-button type="primary" :icon="Edit" circle @click="editUser(scope.row);"/>
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
import {Delete, Edit, InfoFilled} from "@element-plus/icons-vue";

</script>
<script>
import api from "@/api/index.js";
import {ElMessage} from "element-plus";

export default {
  name: "categories",

  data(){
    return{
      categoriesList: [
        {
          "cat_id": 1,
          "cat_name": "大家电",
          "cat_pid": 0,
          "cat_level": 0,
          "cat_deleted": false,
          "children": [
            {
              "cat_id": 3,
              "cat_name": "电视",
              "cat_pid": 1,
              "cat_level": 1,
              "cat_deleted": false,
              "children": [
                {
                  "cat_id": 6,
                  "cat_name": "曲面电视",
                  "cat_pid": 3,
                  "cat_level": 2,
                  "cat_deleted": false
                },
                {
                  "cat_id": 7,
                  "cat_name": "海信",
                  "cat_pid": 3,
                  "cat_level": 2,
                  "cat_deleted": false
                }
              ]
            }
          ]
        }
      ],
    }
  },

  methods: {
    async getCategories() {
      const {data: res} = await api.goods.getCategories()
      if (res.meta.status !== 200) {
        return ElMessage.error("获取商品分类列表失败！")
      }
      this.categoriesList = res.data;
    },
  },

  mounted() {
    this.getCategories();
  }
}
</script>

<style scoped>

</style>
