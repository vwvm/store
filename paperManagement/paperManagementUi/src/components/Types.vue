<template>
  <div class="types">
    <div class="top">
      <div class="title">{{ types.categoryName }}</div>
      <ul class="types-nav">
        <li :class="index === activeIndex?'active':''" v-for="(item, index) in types.productCategoryBeans"
            @click="clickType(index,item)">
          {{ item.categoryName }}
        </li>
      </ul>
    </div>
    <div class="img-box">
      <div class="box-header-img">
        <slot/>
      </div>
      <div class="box-content">
        <template v-for="(item,index) in productList">
          <Item :item="item" v-if="index<7"></Item>
          <div class="more" v-if="index === 7">
            <a href="">浏览更多<el-icon size="18px"><DArrowRight/></el-icon></a>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script setup>
import {DArrowRight} from "@element-plus/icons-vue";
import {computed, onMounted, ref} from "vue";
import api from "@/api/api.js";
//定义高亮索引
const activeIndex = ref(0);
const props = defineProps(['types'])
const productList = ref()
const types = computed(() => {
  if (props) {
    return props.types
  }
  return []
})
const productTypeList = async (type) => {
  productList.value = await api.__product.getProductSearchType({'type': type.categoryId, "limit": 7})
}

const clickType = (index, type) => {
  activeIndex.value = index;
  productTypeList(type)
}

onMounted(() => {
  productTypeList(types.value)
})

</script>

<style scoped lang="scss">
.types {
  //height: 672px;
  margin: 0 auto;

  .top {
    height: 58px;
    display: flex;
    align-items: center;
    margin: 20px;
    justify-content: space-between;

    .title {
      font-size: 22px;
      color: #333;
      font-weight: 200;

    }
  }

  .img-box {
    height: 614px;
    display: flex;

    .box-header-img {
      width: 234px;
    }

    .box-content {
      flex: 1;

      .more {
        width: 234px;
        height: 300px;
        background-color: white;
        float: left;
        margin: 5px 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        border: 2px solid red;

        &:hover {
          transform: translate3d(0, -2px, 0);
          box-shadow: 0 15px 30px rgb(0 0 0 / 10%);
        }
        a {
          font-size: 18px;
          color: #333;
          display: flex;
          align-items: center;
          &:hover {
            color: #ff6700;
          }
        }
      }
    }
  }

  .types-nav {
    display: flex;
    font-size: 16px;

    li {
      margin: 10px 20px;
      cursor: pointer;

    }
  }
}

.active {
  color: #ff6700;
  border-bottom: 2px solid #ff6700;
}
</style>