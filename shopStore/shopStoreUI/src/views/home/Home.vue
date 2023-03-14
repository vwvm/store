<template>

      <!-- 轮播图 -->
      <Swiper :list="carouselList" height="600px" width="1500px"/>
      <!-- 分类开始 -->
      <Types :types="item" v-for="item in productCategory">
        <img src="src/assets/img_1.png" alt="" style="width: 234px">
      </Types>

</template>

<script setup>
import {ref, onMounted} from "vue";
import Swiper from "@/components/Carousel.vue";
import api from "@/api/api.js";
import Types from "@/components/Types.vue";

const activeIndex = ref('1')
// 商品类别信息
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
// 商品数据
const product = ref([{
  "id": 2, "productId": "10", "productName": "商品10", "categoryId": 10, "rootCategoryId": 1,
  "soldNum": 127, "productStatus": 1, "content": "商品说明", "creationTime": "2021-04-26T11:11:11",
  "creationUserId": null, "updateTime": "2021-04-26T11:11:11", "updateUserId": null, "deleteFlag": false
},])

const carouselList = ref([{id: 1, imgUrl: '/one/bg1.png', imgBgColor: 'green', productId: '101', categoryId: null}])


// 获取商品列表
const loadList = async () => {
  carouselList.value = await api.__home.carouselList()
  productCategory.value = await api.__product.productCategory()
  product.value = await api.__product.getProduct()
}

onMounted(() => {
  loadList()
})

</script>

<script>
export default {
  name: "Home"
}
</script>

<style scoped lang="scss">
.flex-grow {
  flex-grow: 1;
}
.content {
  width: 80vw;
  margin: 20px auto;
  //border-bottom: 1px solid #b0b0b0;
  .el-menu--horizontal > .el-menu-item {
    border-bottom: none;
    text-decoration: none;
  }
}

:deep(.pr) {

}


</style>