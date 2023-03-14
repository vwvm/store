<template>
  <!--轮播图 -->
  <div class="block text-center">
    <el-carousel :height="props.height+''" :width="props.width" class="swiper">
      <el-carousel-item v-for="item in props.list" :key="item">
        <img :src="minioBaseUrl + item.imgUrl" :alt="item.id">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
// 定义组件属性
const props = defineProps(['list', 'width', 'height'])
// 轮播图数据
import {onMounted, ref} from "vue";
import api from "@/api/api.js";
import {minioBaseUrl} from "@/config/index.js";
const carouselList = ref([{id: 1, imgUrl: '/one/bg1.png', imgBgColor: 'green', productId: '101', categoryId: null}])
const loadCarouselList = async () => {
  carouselList.value = await api.__home.carouselList()
}
onMounted(() => {
  loadCarouselList()
})

</script>
<style scoped lang="scss">
.swiper {
  margin: 0 auto;

  img {
    object-fit: cover;
    width: 100%;
    height: 100%;
    display: inline-block;
    border: none;
    border-radius: 10px;
  }
}
</style>