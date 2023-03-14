<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item>当前：域区管理</el-breadcrumb-item>
    <el-breadcrumb-item>列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
  <br>
  <el-form :inline="true" :model="query" class="demo-form-inline">
    <el-form-item label="区域名称">
      <el-input v-model="query.regionName" placeholder="区域名称"/>
    </el-form-item>
    <el-form-item label="区域城市">
      <el-input v-model="query.regionCity" placeholder="区域城市"/>
    </el-form-item>
    <el-form-item label="地址">
      <el-input v-model="query.address" placeholder="地址"/>
    </el-form-item>
    <el-form-item label="创建时间">
      <el-date-picker
          v-model="creationTime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="Start date"
          end-placeholder="End date"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">搜索</el-button>
    </el-form-item>
  </el-form>
  <br>
  <el-button type="primary" @click="add">添加</el-button>
  <el-button type="primary" @click="isShow = !isShow">查看</el-button>
  <el-button type="primary" @click="isEdit = !isEdit">编辑</el-button>
  <el-button type="primary" @click="isDelete = !isDelete">删除</el-button>
  <br>
  <br>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column align="center" prop="id" label="序号" width="100" v-slot="slot">
      <span>{{ pageSize * (pageNum - 1) + slot.$index + 1 }}</span>
    </el-table-column>
    <el-table-column align="center" prop="regionName" label="区域名称"></el-table-column>
    <el-table-column align="center" prop="regionalCity" label="区域城市"></el-table-column>
    <el-table-column align="" prop="address" label="地址" v-slot="slot" width="350">
      <div v-for="(address, index) in slot.row.address">
        <span>{{ (index + 1) + "." + address }}</span><br>
      </div>
    </el-table-column>
    <el-table-column align="center" prop="creationTime" label="创建时间" v-slot="slot">
      <span>{{ slot.row.creationTime.replace("T", " ") }}</span>
    </el-table-column>
    <el-table-column align="center" label="操作" width="180" v-if="isEdit || isDelete || isShow" v-slot="slot">
      <el-button type="primary" @click="editRegion(slot)" v-if="isEdit">编辑</el-button>
      <el-button type="primary" @click="showRegion(slot)" v-if="isShow">查看</el-button>
      <el-button type="primary" @click="deleteRegion(slot)" v-if="isDelete">删除</el-button>
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
  name: "RegionList",
  data() {
    return {
      query: {
        regionName: "",
        regionCity: "",
        address: "",
        creationTimeA: "",
        creationTimeB: "",
      },
      creationTime: "",
      isEdit: false,
      isShow: false,
      isDelete: false,
      pageNum: 1,
      pageTotal: 100,
      pageSize: 4,
      queryInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      tableData: [{
        regionName: "",
        regionalCity: "",
        address: "",
        creationTime: "",
      }],
    }
  },
  methods: {
    editRegion(slot) {
      router.push({path: "/home/regionEdit", query: slot.row})
    },
    showRegion(slot) {
      router.push({path: "/home/regionShow", query: slot.row})
    },
    async deleteRegion(slot) {
      const {data: res} = await api.region.regionDelete(slot.row)
      if (res.data === true) {
        router.go(0)
        return ElMessage.success("删除成功！")
      }
      return ElMessage.error("删除失败！")
    },
    add() {
      router.push("/home/regionAdd")
    },
    handleSizeChange() {
      this.queryInfo.pageSize = this.pageSize
      this.queryInfo.pageNum = 1
      this.queryList()
    },
    handleCurrentChange() {
      this.queryInfo.pageNum = this.pageNum
      this.queryList()
    },
    onSubmit() {
      this.query.creationTimeA = Date.parse(this.creationTime[0]) + ""
      this.query.creationTimeB = Date.parse(this.creationTime[1]) + ""
      this.queryInfo.query = this.query
      this.queryList()
    },
    //查询
    async queryList() {
      console.log(this.queryInfo)
      const {data: res} = await api.region.list(this.queryInfo)
      if (res.status !== 200) {
        return ElMessage.error("查询失败！")
      }
      this.pageTotal = res.msg
      this.tableData = res.data;
      console.log(this.tableData)
    }
  },
  mounted() {
    this.onSubmit();
  }


}
</script>

<style scoped>


.el-container {
  height: 100%;
}

.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  height: 60px !important;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #9ea4aa;
}


</style>
