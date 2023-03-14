<template>
  <Header></Header>
  <div class="content">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
        router
    >
      <el-menu-item index="0">
        <el-image style="width: 200px" src="src/assets/vwvm_w.png"/>
      </el-menu-item>
      <div class="flex-grow"/>
      <el-menu-item index="/home">首页</el-menu-item>
      <el-menu-item index="/goods">全部商品</el-menu-item>
      <el-menu-item index="/detail" v-if="this.$route.path === '/detail'">商品详细</el-menu-item>
      <el-menu-item index="/about">关于我们</el-menu-item>
      <div class="flex-grow"/>
    </el-menu>
    <br/>
    <div class="detail-page">
      <div class="detail-page-header">
        <div class="name">
          {{ productOne.productName }}
        </div>
        <ul>
          <li>概述</li>
          <li>参数</li>
          <li>用户评价</li>
        </ul>
      </div>
      <div class="detail-page-content">
        <div class="swiper">
          <Carousel :list="productImg" :height="'500px'"></Carousel>
          <img>
        </div>
        <div class="item">
          <h1 class="productName">{{ productOne.productName }}</h1>
          <p class="productContent">{{ productOne.content }}</p>
          <p class="productZY">自营</p>
          <div class="productPrice">
            <span>{{ productSpecOne.sellPrice }} 元</span>
            <span class="del"></span>
          </div>
          <div class="productList">
            <span class="list-productName">{{ productOne.productName }}</span>
            <span class="list-productPrice">
              <span>{{ productSpecOne.sellPrice }} 元</span><span class="list-product-del">{{ productSpecOne.originalPrice }} 元 </span>
            </span>
            <p class="price-sum"> 总计</p>
          </div>
          <div class="button">
            <el-button class="shop-cart">加入购物车</el-button>
            <el-button class="like">喜欢</el-button>
          </div>
          <ul class="pro-policy">
            <li>
              <el-icon>
                <CircleCheck/>
              </el-icon>
              7天无理由退货
            </li>
            <li>
              <el-icon>
                <CircleCheck/>
              </el-icon>
              7天价格保护
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <MyFooter></MyFooter>
</template>

<script setup>
import {ref, onMounted} from "vue";
import Header from "@/components/Header.vue";
import MyFooter from "@/components/MyFooter.vue";
import {useRoute} from "vue-router";
import api from "@/api/api.js";
import Carousel from "@/components/Carousel.vue";
import {CircleCheck} from '@element-plus/icons-vue'
import {getProductSpecOne} from "@/api/product.js";

const activeIndex = ref('/detail')
const route = useRoute()
const productOne = ref({
  "id": 2, "productId": "10", "productName": "商品10", "categoryId": 10, "rootCategoryId": 1,
  "soldNum": 127, "productStatus": 1, "content": "商品说明", "creationTime": "2021-04-26T11:11:11",
  "creationUserId": null, "updateTime": "2021-04-26T11:11:11", "updateUserId": null, "deleteFlag": false
})
const productSpecOne = ref({
  "id": 10, "productSpecificationId": "4", "productId": "1",
  "productSpecificationName": "奥利奥分享装", "productSpecificationImg": "ala_1.png",
  "untitled": {
    "口味": [
      "草莓",
      "巧克力",
      "牛奶",
      "原味"
    ]
  }, "originalPrice": 10, "sellPrice": 8, "discounts": 0.8, "stock": 137, "status": 1,
  "creationTime": "2021-04-27T11:38:55", "creationUserId": null,
  "updateTime": "2021-04-27T11:39:00", "updateUserId": null, "deleteFlag": false
})
// 商品图片数组
const productImg = ref([
  {id: 1, imgUrl: '/project/productSpecificationImg/img.png', imgBgColor: 'green', productId: '101', categoryId: null},
  {id: 2, imgUrl: '/project/productSpecificationImg/img_1.png', imgBgColor: 'green', productId: '101', categoryId: null},
  {id: 3, imgUrl: '/project/productSpecificationImg/img_2.png', imgBgColor: 'green', productId: '101', categoryId: null}
])

const loadProduct = async () => {
  // 获取传递的id
  const productId = route.query.id
  const productSpecOne = await api.__product.getProductSpecOne({productId})
  const productOne = await api.__product.getProductOne({productId})
  console.log("productSpecOne", productSpecOne)
  console.log("productOne", productOne)
}
const handleSelect = (key, keyPath) => {
  sessionStorage.setItem("menuActive", key);
}
onMounted(() => {
  loadProduct()
})

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

.detail-page {
  background-color: white;

  .detail-page-header {
    height: 44px;
    box-shadow: 0 5px 5px rgb(0 0 0 /7%);
    display: flex;
    justify-content: space-between;

    .name {
      font-size: 18px;
      font-weight: 400;
      color: #212121;
    }

    ul {
      display: flex;

      li {
        font-size: 14px;
        color: #616161;
        margin: 0 14px;
        cursor: pointer;

        &:hover {
          color: #ff6700;
        }
      }
    }
  }

  .detail-page-content {
    display: flex;
    padding-top: 30px;

    .swiper {
      width: 560px;
      height: 650px;
    }

    .item {
      margin-left: 25px;
      width: 640px;

      .productName {
        height: 30px;
        line-height: 30px;
        font-size: 24px;
        font-weight: 400;
        color: #212121;
      }

      .productContent {
        color: #b0b0b0;
        padding-top: 10px;
      }

      .productZY {
        color: #ff6700;
        padding-top: 10px;
      }

      .productPrice {
        display: block;
        font-size: 18px;
        color: #ff6700;
        border-bottom: 1px solid #e0e0e0;
        padding: 25px 0 25px;

        .del {
          font-size: 14px;
          margin-left: 10px;
          color: #b0b0b0;
          text-decoration: line-through;
        }
      }

      .productList {
        background: #f9f9fa;
        padding: 30px 60px;
        margin: 50px 0 50px 0;

        .list-productName {
          line-height: 30px;
          color: #616161;
        }

        .list-productPrice {
          float: right;

          .list-product-del {
            margin-left: 10px;
            color: #b0b0b0;
            text-decoration: line-through;
          }
        }

        .price-sum {
          color: #ff6700;
          font-size: 24px;
          padding-top: 20px;
        }
      }
    }

    .button {
      height: 55px;
      margin: 10px 0 20px 0;
      display: flex;

      .shop-cart {
        height: 55px;
        font-size: 16px;
        color: #fff;
        border: none;
        text-align: center;
        width: 340px;
        background-color: #ff6700;
      }

      .like {
        height: 55px;
        width: 260px;
        font-size: 16px;
        color: #fff;
        border: none;
        text-align: center;
        margin-left: 40px;
        background-color: #b0b0b0;
      }
    }

    .pro-policy {
      display: flex;

      li {
        margin-right: 20px;
        color: #b0b0b0;
      }
    }

  }
}
</style>