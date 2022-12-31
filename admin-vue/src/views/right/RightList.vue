<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>权限管理</el-breadcrumb-item>
    <el-breadcrumb-item>权限列表</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card class="box-card">
    <el-table stripe :data="rightList" border style="width: 100%">
      <el-table-column type="index"/>
      <el-table-column prop="authName" label="权限名称" />
      <el-table-column prop="path" label="路径"/>
      <el-table-column prop="level" label="权限等级" v-slot:default="scope">
        <el-tag v-if="scope.row.level === '0'">等级一</el-tag>
        <el-tag v-if="scope.row.level === '1'" type="success">等级二</el-tag>
        <el-tag v-if="scope.row.level === '2'" type="danger">等级三</el-tag>
        <el-tag v-if="scope.row.level === '3'" type="info">权限3</el-tag>
      </el-table-column>
    </el-table>
  </el-card>
  <br>

</template>

<script>
import api from "@/api/index.js";
import {ElMessage} from "element-plus";

export default {
  name: "RightList",

  data(){
    return{
      rightList: [{
        authName: "",
        path: "",
        level: "",
      }],

    }
  },


  methods:{
    async getRightList() {
      const res = await api.right.getRight("list")
      if (res.data.meta.status !== 200) {
        return ElMessage.error("获取权限列表失败！")
      }
      this.rightList = res.data.data;
    },
  },


  mounted() {
    this.getRightList()
  }
}
</script>

<style scoped>

</style>
