<template>
  <div>
    <div style="width: 100%; height: 70px; background: #0c80ba;">
      <table>
        <tr>
          <td></td>
          <td>
            <ul>
              <li><router-link to="/">首页</router-link> </li>
              <li><router-link to="/about">关于</router-link> </li>
              <li><router-link to="/router3">router3</router-link> </li>
              <li><router-link to="/router3/about"> 嵌套路由</router-link> </li>
              <li><router-link to="/router4/100"> 路由传参</router-link> </li>
              <li><router-link to="/login">登录</router-link> </li>
              <li><router-link to="/elementPlusTest">element-plus-test</router-link> </li>
              <li><router-link to="/register">register</router-link></li>
              <li><router-link to="/exampleBlock">exampleBlock</router-link></li>
              <li><router-link to="/introduction">introduction</router-link></li>
            </ul>
            <button type="button" @click="test">打开关于</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <div style="width: 100%; height: 700px;">
    <router-view></router-view>
  </div>

</template>

<script>
import router from "../router/index.js";

export default {
  name: "head-router",
  components: {},

  methods:{
    test:function (){
      router.push("/about")
    }
  }
}
</script>

<style scoped>

</style>