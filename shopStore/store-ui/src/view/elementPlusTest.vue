<template>
  <el-row class="mb-4">
    <el-button>Default</el-button>
    <el-button type="primary">Primary</el-button>
    <el-button type="success">Success</el-button>
    <el-button type="info">Info</el-button>
    <el-button type="warning">Warning</el-button>
    <el-button type="danger">Danger</el-button>
  </el-row>
  <hr>
  {{ indexImages }}
  <hr>
  {{categoryBeanList}}
  <hr>
  {{recommendProducts}}
  <hr>
  {{recommendCategories}}
  <hr>
</template>

<script>
import axios from "axios";
import {getCookieValue} from "../assets/js/cookie_utils.js";

export default {
  name: "elementPlusTest",
  data() {
    return {
      username: "您未登录",
      userImg: "",
      isLogin: false,
      indexImages: [],
      categoryBeanList: [],
      recommendProducts: [],
      recommendCategories: [],
      keyword: ""
    }
  },
  //渲染html前调用
  created() {
    const token = getCookieValue("token");
    if (token !== null && token !== "") {
      this.isLogin = true;
      this.username = getCookieValue("username");
      this.userImg = '/src/assets/images/' + getCookieValue("userImg");
    }
    let baseUrl = import.meta.env.VITE_API_DOMAIN
    //轮播图
    const url = baseUrl + "/index/img-list"
    axios.get(url).then((res) => {
      const vo = res.data;
      this.indexImages = vo.data;
    })
    //商品列表
    const categoryUrl = baseUrl + "/index/category-list";
    axios({
      method: "get",
      url: categoryUrl,
    }).then((res) => {
      const vo = res.data;
      this.categoryBeanList = vo.data
    })
    // 商品推荐
    const categoryRecommends = baseUrl + "/index/category-recommends"
    axios({
      method: "get",
      url: categoryRecommends,
    }).then((res) => {
      this.recommendProducts = res.data.data
    })
    //分类商品推荐
    const categoryProductsRecommend = baseUrl + "/index/category-recommends"
    axios({
      method: "get",
      url: categoryProductsRecommend,
    }).then((res) => {
      const vo = res.data;
      this.recommendCategories = vo.data
      console.log(this.recommendCategories)
    })
  },
}

</script>

<style scoped>

</style>