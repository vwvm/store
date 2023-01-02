<template>

  <div class="common-layout">
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
          <el-menu-item index="0">vwvm-store</el-menu-item>
          <div class="flex-grow"/>
          <el-sub-menu index="2">
            <template #title><img src="@/assets/images/logo1.png" class="user-img" alt=""></template>
            <el-menu-item index="2-1">设置</el-menu-item>
            <el-menu-item index="2-2">个人中心</el-menu-item>
            <el-menu-item index="2-3" @click="logoOut">退出</el-menu-item>

          </el-sub-menu>
        </el-menu>
      </el-header>

      <el-container>
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
            <el-sub-menu :index="menu.path" v-for="menu in menuList" :key="menu.id">
              <template #title>
                <span>{{ menu.authName }}</span>
              </template>
              <el-menu-item @click="saveActivePath(m.path)" :index="'/home'+m.path" v-for="m in menu.children">{{
                  m.authName
                }}
              </el-menu-item>
            </el-sub-menu>

          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view :key="activePath"></router-view>
          </el-main>
          <el-footer>
<!--            <router-link to="/home/users/userList"> 用户管理</router-link>-->
          </el-footer>
        </el-container>

      </el-container>
    </el-container>
  </div>
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
      if (res.meta.status === 200) {
        this.menuList = res.data;
      } else {
        const msg = res.meta.msg
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

.el-container {
  width: 100%;
  height: 100%;
}

.common-layout {
  width: 100%;
  height: 100%;
}

.user-img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.flex-grow {
  flex-grow: 1;
}

</style>
