<template>
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>全部商品</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-tabs
      v-model="page.type"
      type="card"
      class="demo-tabs"
      @tab-change="loadList"
  >
    <el-tab-pane label="全部" name=""></el-tab-pane>
    <el-tab-pane :label="item.categoryName" :name="item.categoryId" v-for="item in productCategory"
                 :key="item.categoryId"></el-tab-pane>
  </el-tabs>
  <div class="products">
    <Item v-for="item in product" :item="item"/>
  </div>
  <div class="pagination">
    <el-pagination :page-size="page.pageSize" background @current-change="loadList"
                   v-model:current-page="page.pageNum" layout="prev, pager, next" :total="page.total"/>
  </div>
</template>
<script setup>
import {ArrowRight} from '@element-plus/icons-vue'
import {onMounted, ref} from 'vue'
import api from "@/api/api.js";
import Item from "@/components/Item.vue";

const productCategory = ref([{
  "id": null,
  "categoryId": 1,
  "categoryName": "点心/蛋糕",
  "categoryLevel": 1,
  "parentId": 0,
  "categoryIcon": "cake.png",
  "categorySlogan": "每一道甜品都有一个故事",
  "categoryPic": "act1.png",
  "categoryBgColor": "black",
  "creationTime": null,
  "creationUserId": null,
  "updateTime": null,
  "updateUserId": null,
  "deleteFlag": null,
  "productCategoryBeans": [
    {
      "id": null,
      "categoryId": 11,
      "categoryName": "蛋糕",
      "categoryLevel": null,
      "parentId": 1,
      "categoryIcon": null,
      "categorySlogan": null,
      "categoryPic": null,
      "categoryBgColor": null,
      "creationTime": null,
      "creationUserId": null,
      "updateTime": null,
      "updateUserId": null,
      "deleteFlag": null,
      "productCategoryBeans": [
        {
          "id": null,
          "categoryId": 43,
          "categoryName": "铜锣烧",
          "categoryLevel": 3,
          "parentId": 11,
          "categoryIcon": null,
          "categorySlogan": null,
          "categoryPic": null,
          "categoryBgColor": null,
          "creationTime": null,
          "creationUserId": null,
          "updateTime": null,
          "updateUserId": null,
          "deleteFlag": null,
          "productCategoryBeans": null
        },
      ]
    },
  ]
},])
const product = ref([{
  "id": 2, "productId": "10", "productName": "商品10", "categoryId": 10, "rootCategoryId": 1,
  "soldNum": 127, "productStatus": 1, "content": "商品说明", "creationTime": "2021-04-26T11:11:11",
  "creationUserId": null, "updateTime": "2021-04-26T11:11:11", "updateUserId": null, "deleteFlag": false
},])
const page = ref({str: '', type: '', pageNum: 1, pageSize: 12, total: 55})

// 加载事件
const loadList = async (item) => {
  // 加载商品分类
  productCategory.value = await api.__product.productCategory()
  // 加载商品
  const {records, total} = await api.__product.getProductSearchPage(page.value)
  product.value = records
  page.value.total = total
  console.log(product.value)
  console.log(page.value.total)
}
onMounted(() => {
  loadList()
})
</script>
<style scoped lang="scss">
.el-breadcrumb {
  font-size: 16px;
}

.products {
  overflow: auto;
  margin: 0 auto;
}
.pagination{
  display: flex;
  justify-content: center;
}
</style>