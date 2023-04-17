<template>
  <el-button>
    <router-link to="/baikuai">白块小游戏</router-link>
  </el-button>
  <el-button>
    <router-link to="/xiangce">我们的相册</router-link>
  </el-button>
  <el-button>
    <router-link to="/speak">说的话</router-link>
  </el-button>
  <el-button>
    <router-link to="/showLove">表白</router-link>
  </el-button>
  <router-view/>
</template>

<script>
export default {
  name: "index"
}
</script>

<style scoped>

</style>