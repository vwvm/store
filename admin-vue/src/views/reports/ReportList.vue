<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>数据统计</el-breadcrumb-item>
    <el-breadcrumb-item>数据报表</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card>
    <div id="main" style="width: 600px;height:400px;"></div>
  </el-card>


</template>

<script>
import * as echarts from 'echarts'
import _ from "lodash" //简化数据操作的工具类
import api from "@/api/index.js";
import {ElMessage} from "element-plus";

export default {
  name: "ReportList",

  data() {
    return {
      options: {
        title: {
          text: '用户来源'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#E9EEF3'
            }
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            boundaryGap: false
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ]
      }
    }
  },

  methods: {
    async getReport() {
      const {data: res} = await api.report.getReport()
      console.log(res)
      if (res.meta.status === 200) {
        // 这个操作待研究
        _.merge(res.data, this.options)
        this.options = res.data;
      } else {
        const msg = res.meta.msg
        ElMessage.error('请求菜单失败:' + msg)
      }

    },
  },

  async mounted() {
    const myChart = echarts.init(document.getElementById('main'));
    await this.getReport()
    myChart.setOption(this.options)
  }
}
</script>

<style scoped>

</style>
