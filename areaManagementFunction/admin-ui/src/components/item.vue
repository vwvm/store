<template>
  <div class="items" :key="item.id">
    <img class="product-img" :src="minioBaseUrl + '/project/img1.png'" alt="">
    <div class="name">{{ item.productName }}</div>
    <div class="product-content"> {{ item.content.substring(0, 6) }}</div>
    <div class="price">
      <span class="selling-price">2500</span>
      <span class="price-now">2000</span>
    </div>
  </div>
</template>

<script setup>
minioBaseUrl
</script>

<script>
export default {
  name: "item"
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

  .product-img {
    width: 160px;
    height: 160px;

  }
}
</style>