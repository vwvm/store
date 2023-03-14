<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item>当前：域区管理</el-breadcrumb-item>
    <el-breadcrumb-item>查看</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
  <br>
  <el-form :model="form" style="padding: 30px">
    <br>
    <el-form-item label="区域名称">
      <el-input disabled v-model="form.regionName"/>
    </el-form-item>
    <div v-for="(city, index) in form.regionalCity">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ city }}</span>
          </div>
        </template>
        <div v-for="(add, index2) in Number(form.addressSize[index])" :key="index2" class="text item">
          {{ form.address[index * form.addressSize[index] + index2 ]}}
        </div>
      </el-card>
      <br>
    </div>
    <el-form-item>
      <div style="margin: 0 auto">
        <el-button @click="onRe">返回</el-button>
      </div>
    </el-form-item>
  </el-form>

</template>

<script>
import router from "@/router/index.js";
import api from "@/api/index.js";
import {ElMessage} from "element-plus";

export default {
  name: "RegionShow",
  data() {
    return {
      form: {
        regionName: '',
        regionalCity: [],
        address: [],
        addressSize: [],
        id: 0,
        city: "",
      }
    }
  },
  methods: {
    onSubmit() {
      this.add()
    },
    onRe() {
      router.push("/home/region")
    },
    getQuery() {
      this.form = this.$route.query
      console.log(this.form)
    },
  },
  mounted() {
    this.getQuery()
  }
}
</script>

<style scoped>

</style>
