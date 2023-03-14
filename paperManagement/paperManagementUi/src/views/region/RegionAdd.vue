<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item>当前：域区管理</el-breadcrumb-item>
    <el-breadcrumb-item>添加</el-breadcrumb-item>
  </el-breadcrumb>
  <el-divider/>
  <el-form :model="form" style="padding: 30px">
    <el-form-item label="区域名称">
      <el-input v-model="form.regionName"/>
    </el-form-item>
    <el-button type="primary" @click="addCard()">+新增区域城市</el-button>
    <br>
    <br>
    <div v-for="(city, index) in form.regionalCity">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <el-form-item :label="'区域城市' + (index + 1)" v-slot="slot">
              <el-select
                  v-model="form.regionalCity[index]"
                  filterable
                  allow-create
                  default-first-option
                  :reserve-keyword="false"
                  placeholder="Choose tags for your article"
              >
                <el-option
                    v-for="item in cityList"
                    :key="item.city"
                    :value="item.city"
                />
              </el-select>
              <div style="flex-grow: 1;"/>
              <el-button type="warning" @click="deCard(index)">删除</el-button>
            </el-form-item>
          </div>
        </template>
        <div v-for="index2 in Number(form.addressSize[index])" :key="index2" style="width: 100%;height: 40px">
          <el-row :gutter="20">
            <el-col :span="2">
              <div class="grid-content ep-bg-purple"/>
              <div style="margin-top: 5px">{{ "地址" + (index2) + ":" }}</div>
            </el-col>
            <el-col :span="20">
              <div class="grid-content ep-bg-purple"/>
              <el-input v-model="form.address[form.addressSize.getNumArrayTotal(index) + index2 -1]"/>
            </el-col>
            <el-col :span="1">
              <div class="grid-content ep-bg-purple"/>
              <el-button type="primary" v-if="index2 === 1" @click="addAddress(index)">
                <div style="width: 15px">+</div>
              </el-button>
              <el-button type="primary" v-if="index2 !== 1" @click="deAddress(index, index2)">
                <div style="width: 15px">-</div>
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <br>
    </div>
    <el-form-item>
      <div style="margin: 0 auto">
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="onRe">返回</el-button>
      </div>
    </el-form-item>
  </el-form>

</template>

<script>
import router from "@/router/index.js";
import api from "@/api/index.js";
import {ElMessage} from "element-plus";

//实现一个数组前n项和
Array.prototype.getNumArrayTotal = function (num) {
  if (num <= 0) {
    return 0;
  }
  return this.reduce(function (pre, cur, index) {
    pre = Number(pre);
    cur = Number(cur);
    if (index > num - 1) {
      return pre;
    }
    return pre + cur
  });
};

export default {
  name: "RegionAdd",
  data() {
    return {
      form: {
        regionName: '',
        regionalCity: [],
        address: [],
        addressSize: [],
        id: 0,
        city: "",
      },
      cityList: [
        {
          id: 7,
          city: "厦门",
          creationTime: "2023-02-21T10:08:07",
          updateTime: "2023-02-21T10:08:07",
          deleteFlag: false
        }
      ],


    }
  },
  methods: {
    onSubmit() {
      this.add()
    },
    onRe() {
      router.push("/home/region")
    },
    // 添加删除区域城市
    addCard() {
      this.form.regionalCity.push("请选择城市")
      this.form.addressSize.push(1)
      this.form.address.push("请输入")
    },
    deCard(index) {
      console.log(this.form)
      const temp = this.form.addressSize[index]
      const temp2 = this.form.addressSize.getNumArrayTotal(index)
      this.form.address.splice(temp2, temp)
      this.form.regionalCity.splice(index, 1)
      this.form.addressSize.splice(index, 1)
      console.log(this.form)
    },

    // 添加删除地址
    addAddress(index) {
      const temp = this.form.addressSize[index]
      const temp2 = this.form.addressSize.getNumArrayTotal(index + 1)
      this.form.address.splice(temp2, 0, "请输入")
      this.form.addressSize[index] = Number(temp) + 1
    },
    deAddress(index, index2) {
      const temp = this.form.addressSize[index]
      const temp2 = this.form.addressSize.getNumArrayTotal(index) + index2 - 1
      this.form.address.splice(temp2, 1)
      this.form.addressSize[index] = Number(temp) - 1

    },


    async add() {
      const {data: res} = await api.region.add(this.form)
      if (res.data !== true) {
        return ElMessage.error("修改失败！")
      }
      await router.push("/home/region")
      return ElMessage.success("修改成功！")
    },
    //查询cityList
    async queryList() {
      const {data: res} = await api.city.list()
      if (res.status !== 200) {
        return ElMessage.error("查询失败！")
      }
      this.cityList = res.data;
    }
  },
  mounted() {
    this.queryList()
  }
}
</script>

<style scoped>

</style>
