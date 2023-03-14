<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item>当前：城市信息</el-breadcrumb-item>
    <el-breadcrumb-item>列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
  <br>
  <el-button type="primary" @click="add">添加</el-button>
  <el-button type="primary" @click="isEdit = !isEdit">编辑</el-button>
  <el-button type="primary" @click="isDelete = !isDelete">删除</el-button>
  <br>
  <br>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column align="center" prop="id" label="序号" width="180" v-slot="slot">
      <span>{{ pageSize * (pageNum - 1) + slot.$index + 1 }}</span>
    </el-table-column>
    <el-table-column align="center" prop="city" label="城市名称"></el-table-column>
    <el-table-column align="center" label="操作" width="180" v-if="isEdit || isDelete" v-slot="slot">
      <el-button type="primary" @click="edit(slot)" v-if="isEdit">编辑</el-button>
      <el-button type="primary" @click="cityDelete(slot)" v-if="isDelete">删除</el-button>
    </el-table-column>
  </el-table>

  <div class="demo-pagination-block">
    <div class="demonstration"><br></div>
    <el-pagination
        v-model:current-page="pageNum"
        :page-sizes="[4, 6, 8, 10, 12]"
        v-model:page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        v-model:total="pageTotal"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import api from "@/api/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

export default {
  name: "CityList",
  data() {
    return {
      queryInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      pageNum: 1,
      pageSize: 10,
      pageTotal: 5,
      isEdit: false,
      isDelete: false,

      tableData: [
        {
          id: 7,
          city: "厦门",
          creationTime: "2023-02-21T10:08:07",
          updateTime: "2023-02-21T10:08:07",
          deleteFlag: false
        }
      ]
    }
  },
  methods: {
    handleSizeChange() {
      this.queryInfo.pageSize = this.pageSize
      this.queryInfo.pageNum = 1
      this.queryList()
    },
    handleCurrentChange() {
      this.queryInfo.pageNum = this.pageNum
      this.queryList()
    },
    //添加
    add() {
      router.push("/home/cityAdd");
    },
    //修改
    edit(slot) {
      const row = slot.row
      router.push({path: "/home/cityEdit", query: {id: row.id, city: row.city}});
    },
    //删除
    async cityDelete(slot) {
      const {data: res} = await api.city.cityDelete(slot.row)
      if (res.data === true) {
        router.go(0)
        return ElMessage.success("删除成功！")
      }
      return ElMessage.error("删除失败！")
    },
    //查询
    async queryList() {
      const {data: res} = await api.city.list(this.queryInfo)
      if (res.status !== 200) {
        return ElMessage.error("查询失败！")
      }
      this.pageTotal = res.msg
      this.tableData = res.data;
    }
  },
  mounted() {
    this.queryList();
  }
}
</script>

<style scoped>

</style>
