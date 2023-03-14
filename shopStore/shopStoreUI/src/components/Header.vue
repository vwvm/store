<template>
  <div class="header">
    <div class="content">
      <ul>
        <li @click="showLogin">登录</li>
        <li>|</li>
        <li @click="showRegister">注册</li>
        <li @click="openOrder">我的订单</li>
        <li @click="openCollect">我的收藏</li>
        <li @click="openShoppingCart" class="shopping-cart">
          <el-icon>
            <ShoppingCart/>
          </el-icon>
          <span>购物车(0)</span>
        </li>
      </ul>
    </div>
  </div>
  <Register ref="showRegisterRef"/>
  <Login ref="showLoginRef"/>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import ShoppingCart from "@/views/shoppingCart/ShoppingCart.vue";
import Register from "@/components/Register.vue";
import Test from "@/views/test/Test.vue";
import Login from "@/components/Login.vue";
const router = useRouter()

// 绑定是否显示注册登录Ref
const showRegisterRef = ref(null)
const showLoginRef = ref(null)
// 是否显示注册
const showRegister = () => {
  showRegisterRef.value.showRegister = true
}
const showLogin = () => {
  showLoginRef.value.showLogin = true
}

const openOrder = () => {
  router.push('/order')
}
const openCollect = () => {
  router.push('/collect')
}
const openShoppingCart = () => {
  router.push('/shoppingCart')
}
</script>

<script>
export default {
  name: "Header"
}
</script>

<style scoped lang="scss">
.header {
  height: 40px;
  background-color: #3b3b3b;

  .content {
    width: 80vw;
    margin: 0 auto;
    color: #B0B0B0;
    display: flex;
    justify-content: flex-end;

    ul {
      display: flex;
      height: 40px;
      align-items: center;

      li {
        cursor: pointer;
        margin: 0 5px;
      }

      li:hover {
        color: white;
      }

      .shopping-cart {
        height: 40px;
        background-color: #424242;
        margin-left: 20px;
        padding: 0 10px;
        display: flex;
        justify-content: center;
        align-items: center;

        span {
          margin-left: 3px;
        }
      }

      .shopping-cart:hover {
        color: #ff6700;
        background-color: #FFFFFF;
      }
    }
  }
}

</style>