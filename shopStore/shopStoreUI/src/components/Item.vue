<template>
  <div class="items" :key="item.id" @click="$router.push('/detail?id='+ item.productId)">
    <img class="product-img" :src="minioBaseUrl + '/project/img1.png'" alt="">
    <div class="name">{{ item.productName }}</div>
    <div class="product-content"> {{ item.content.substring(0, 6) }}</div>
    <div class="price">
      <span class="price-now">￥2000元</span>
      <span class="selling-price">￥2500元</span>
    </div>
    <el-popconfirm title="确定删除吗" confirm-button-text="确定"
                   cancel-button-text="取消"
                   @confirm="confirmDel(item.id)"
                   v-if="close"
    >
      <template #reference>
        <div class="close">
          <el-icon>
            <Close/>
          </el-icon>
        </div>
      </template>
    </el-popconfirm>
  </div>
  <slot/>
</template>
<script setup>
import {minioBaseUrl} from "@/config/index.js";
import {Close} from "@element-plus/icons-vue";

const {item, close} = defineProps({
  item: {
    type: Object
  },
  close: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['selectDel'])
const confirmDel = (id) => {
  emit('selectDel', id)
}
</script>
<script>
export default {
  name: "Item"
}
</script>

<style scoped lang="scss">
.items {
  width: 234px;
  height: 300px;
  background-color: white;
  float: left;
  margin: 5px 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  &:hover {
    transform: translate3d(0, -2px, 0);
    box-shadow: 0 15px 30px rgb(0 0 0 / 10%);

    .close {
      z-index: 2;
    }
  }

  .product-img {
    width: 160px;
    height: 160px;
  }

  .name {
    font-size: 18px;
    color: #333;
  }

  .product-content {
    font-size: 12px;
    color: #b0b0b0;
    margin: 10px 0;
  }

  .selling-price {
    font-size: 14px;
    color: #b0b0b0;
    text-decoration: line-through;
    margin-left: 6px;
  }

  .price-now {
    font-size: 14px;
    color: #ff6700;
  }

  .close {
    position: absolute;
    z-index: -1;
    right: 10px;
    top: 10px;
    color: #ff6700;
  }
}
</style>