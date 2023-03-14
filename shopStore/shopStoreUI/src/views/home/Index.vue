<template>
  <MyHeader/>
  <div class="common-layout">
    <div class="content">
      <el-menu
          :default-active="activeIndex+''"
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
        <el-menu-item index="/detail" v-if="this.$route.path === '/detail'">全部商品</el-menu-item>
        <el-menu-item index="/about">关于我们</el-menu-item>
        <div class="flex-grow"/>
        <el-menu-item index="/home">
          <div>
            <el-input
                v-model="productSearchText"
                placeholder="请输入搜索内容"
            >
              <template #append>
                <el-button :icon="Search" @click="selectProductSearch"/>
              </template>
            </el-input>
          </div>
        </el-menu-item>
      </el-menu>
      <br/>
      <router-view/>
    </div>
  </div>
  <MyFooter/>
</template>

<script setup>
import {ref, onMounted, computed, watch} from "vue";
import {Search} from "@element-plus/icons-vue";
import MyHeader from "@/components/Header.vue";
import MyFooter from "@/components/MyFooter.vue";
import api from "@/api/api.js";
import {useMenuActive} from '@/store';
import {useRoute} from "vue-router";
import {ElMessage} from "element-plus";
// 菜单高亮索引
const menuActive = useMenuActive()
const activeIndex = ref(menuActive.activeIndex)

const productSearchText = ref('')
const productSearch = ref()
// 商品类别信息
// 商品数据
const product = ref([{
  "id": 2, "productId": "10", "productName": "商品10", "categoryId": 10, "rootCategoryId": 1,
  "soldNum": 127, "productStatus": 1, "content": "商品说明", "creationTime": "2021-04-26T11:11:11",
  "creationUserId": null, "updateTime": "2021-04-26T11:11:11", "updateUserId": null, "deleteFlag": false
},])

// 菜单切换回调,在存储中设置值
const handleSelect = (key, keyPath) => {
  useMenuActive().setActiveIndex(key)
}
const route = useRoute()
watch(()=>route.path,(val)=>{
  menuActive.setActiveIndex(val)
}, {
  //立刻执行一次
  immediate: true
})
const selectProductSearch = async () => {
  productSearch.value = await api.__product.getProductSearch({"str": productSearchText.value})
}

onMounted(() => {
  if (sessionStorage.getItem("menuActive")){
    activeIndex.value = sessionStorage.getItem("menuActive")
  }
})

</script>

<script>
export default {
  name: "Index"
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