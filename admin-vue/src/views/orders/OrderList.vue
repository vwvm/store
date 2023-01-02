<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>订单管理</el-breadcrumb-item>
    <el-breadcrumb-item>订单列表</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card class="box-card">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-input
            clearable
            @clear="getOrders"
            v-model="queryInfo.query"
            placeholder="请输入内容"
            class="input-with-select"
        >
          <template #append>
            <el-button :icon="Search" @click="getOrders"/>
          </template>
        </el-input>
      </el-col>
    </el-row>
    <br>
    <el-table stripe :data="OrderList" border style="width: 100%">
      <el-table-column type="index"/>\
      <el-table-column prop="order_number" label="订单编号" min-width="70"/>
      <el-table-column prop="order_price" label="订单价格" min-width="18"/>
      <el-table-column prop="order_pay" label="是否付款" min-width="18"/>
      <el-table-column prop="is_send" label="是否发货" min-width="18"/>
      <el-table-column prop="create_time" label="下单时间" min-width="18" v-slot:default="scope">
        {{ dateFormat(scope.row.create_time) }}
      </el-table-column>
      <el-table-column prop="操作" label="操作" min-width="18" v-slot:default="scope">
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
      </el-table-column>
    </el-table>
    <br>
    <el-pagination
        v-model:current-page="queryInfo.pagenum"
        v-model:page-size="queryInfo.pagesize"
        :page-sizes="[4, 6, 8, 10]"
        layout="total, sizes, prev, pager, next, jumper"
        page-size="10"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
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
      <el-form-item label="商品名称" prop="goods_name">
        <el-input v-model="ruleForm.username"/>
      </el-form-item>
      <el-form-item label="商品价格" prop="goods_price">
        <el-input type="password" v-model="ruleForm.password"/>
      </el-form-item>
      <el-form-item label="商品数量" prop="goods_number">
        <el-input v-model="ruleForm.email"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="add_time">
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
</template>
<script setup>
import {Delete, Edit, Search, InfoFilled} from '@element-plus/icons-vue'
import {ref} from "vue";
import {ElMessage} from "element-plus";

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
import {ElMessage} from "element-plus";
import {reactive, ref} from "vue";

export default {
  name: "OrderList",

  data() {
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 4,
      },
      total: 0,
      OrderList: [
        {
          order_id: 47,
          user_id: 133,
          order_number: "itcast-59e7502d7993d",
          order_price: 322,
          order_pay: "1",
          is_send: "是",
          trade_no: "",
          order_fapiao_title: "个人",
          order_fapiao_company: "",
          order_fapiao_content: "办公用品",
          consignee_addr: "a:7:{s:6:\"cgn_id\";i:1;s:7:\"user_id\";i:133;s:8:\"cgn_name\";s:9:\"王二柱\";s:11:\"cgn_address\";s:51:\"北京市海淀区苏州街长远天地大厦305室\";s:7:\"cgn_tel\";s:11:\"13566771298\";s:8:\"cgn_code\";s:6:\"306810\";s:11:\"delete_time\";N;}",
          pay_status: "1",
          create_time: 1508331565,
          update_time: 1508331565
        }
      ],
      editForm: reactive({
        username: "",
        email: "",
        mobile: "",
        id: "",
      }),
    }
  },

  methods: {
    async getOrders() {
      const {data: res} = await api.order.getOrders(this.queryInfo)
      if (res.meta.status !== 200) {
        const msg = res.meta.msg
        return ElMessage.error("获取用户列表失败: " + msg)
      }
      console.log(res)
      this.OrderList = res.data.goods;
      this.total = res.data.total;
    },

    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getOrders();
    },

    handleCurrentChange(currentPage) {
      this.queryInfo.pagenum = currentPage;
      this.getOrders();
    },

    async change(user) {
      console.log(user)
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
        console.log(res)
        return ElMessage.error("删除用户失败！")
      }
      this.queryInfo.pagenum = 1;
      await this.getOrders()
      ElMessage.success("删除用户成功！")
    },

    dateFormat(originStr) {
      const data = new Date(originStr)
      const year = data.getFullYear();
      const month = (data.getMonth()+1+"").padStart(2, "0");
      const day = (data.getDate()+"").padStart(2, "0");
      const hour = (data.getHours()+"").padStart(2, "0");
      const min = (data.getMinutes()+"").padStart(2, "0");
      const sec = (data.getSeconds()+"").padStart(2, "0");
      return `${year}-${month}-${day} ${hour}:${min}:${sec}`
    }


  },

  mounted() {
    this.getOrders()
  }
}
</script>

<style scoped>

</style>
