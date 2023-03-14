<template id="app">
  <el-container class="container">
    <el-header>
      <el-menu
          class="el-menu-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          mode="horizontal"
          :ellipsis="false"
      >
        <el-menu-item index="0">区域管理功能</el-menu-item>
        <div class="flex-grow"/>
        <el-menu-item index="2" @click="logoOut">>退出</el-menu-item>

      </el-menu>
    </el-header>

    <el-container style="height: 100%">
      <el-aside width="200px">
        <el-menu
            router
            unique-opened
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            :default-active=activePath
            text-color="#fff"
        >
          <el-menu-item :index="menu.path" v-for="menu in menuList" :key="menu.id" style="justify-content: center; padding: 0">
            {{ menu.authName }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view :key="activePath"></router-view>
        </el-main>
        <el-footer>

        </el-footer>
      </el-container>

    </el-container>
  </el-container>
</template>
<script setup>
import {ElMessage} from "element-plus";
</script>
<script>
import api from "@/api";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router";

export default {
  loginUsername: "Home",

  data() {
    return {
      menuList: [],
      activePath: "",
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }

  },
  methods: {
    async getMenuList() {
      const {data: res} = await api.right.getMenus()
      if (res.status === 200) {
        this.menuList = res.data;
      } else {
        const msg = res.msg
        ElMessage({
          message: '请求菜单失败:' + msg,
          type: 'error',
        })
        await router.push("/login")
      }

    },

    saveActivePath(path) {
      this.activePath = path;
      window.sessionStorage.setItem("activePath", path);
    },

    logoOut() {
      ElMessageBox.confirm(
          '这个操作将会退出是否继续?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            ElMessage({
              type: 'success',
              message: '退出成功',
            })
            window.sessionStorage.removeItem("token")
            router.push("/login")
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '已取消退出',
            })
          })

    }

  },

  mounted() {
    this.getMenuList()
    this.activePath = window.sessionStorage.getItem("activePath")
  }

}
</script>

<style scoped>
.el-header {
  background: #545c64;
//display: flex; //justify-content: space-between;
}

.el-aside {
  background-color: #545c64;
}




.flex-grow {
  flex-grow: 1;
}
#app{
  height: 100%;
}

</style>
