<template>
  <!--顶部导航条 -->
  <el-header>
    <el-row>
      <el-col :span="6">
        <div class="menu-hd" v-if="isLogin">
          {{ username }},欢迎您
        </div>
        <div class="menu-hd" v-else>
          亲，请<a href="login.html" target="_top" class="h">登录</a>
          <a href="#" target="_top">免费注册</a>
        </div>
      </el-col>
      <el-col :span="12"></el-col>
      <el-col :span="6">

        <a href="/index.html" target="_top" class="h">商城首页</a>

        <a href="user-center.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>

        <a id="mc-menu-hd" href="shopcart.html" target="_top">
          <i class="am-icon-shopping-cart  am-icon-fw"></i>

          <router-link to="/shopcart">购物车</router-link>

          <strong id="J_MiniCartNum" class="h"></strong>
        </a>

        <a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>

      </el-col>
    </el-row>
  </el-header>
</template>

<script>
export default {
  name: "elHeader"
}
</script>

<style scoped>

</style>