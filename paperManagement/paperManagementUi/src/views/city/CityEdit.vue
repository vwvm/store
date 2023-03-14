<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item>当前：城市信息</el-breadcrumb-item>
    <el-breadcrumb-item>编辑</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
  <br>
  <div style="border:2px solid">
    <el-form :model="form" style="padding: 30px">
      <br>
      <el-form-item label="城市名称">
        <el-input v-model="form.city"/>
      </el-form-item>
      <el-form-item>
        <div style="margin: 0 auto">
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="onRe">返回</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>


</template>

<script>
import api from "@/api/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

export default {
  name: "CityEdit",
  data() {
    return {
      form: {
        id: 0,
        city: "",
      }
    }
  },
  methods: {
    onSubmit() {
      this.edit()
    },
    onRe() {
      router.push("/home/city")
    },
    getQuery(){
      this.form = this.$route.query
    },

    async edit() {
      const {data: res} = await api.city.edit(this.form)
      if (res.data !== true) {
        return ElMessage.error("修改失败！")
      }
      await router.push("/home/city")
      return ElMessage.success("修改成功！")
    }
  },
  mounted() {
    this.getQuery()
  }
}
</script>

<style scoped>

</style>
