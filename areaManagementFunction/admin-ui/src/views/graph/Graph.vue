<template>
  <div>
      <ul>
          <li>
              销售状况
              {{ value }}
          </li>
          <li>
              进货状况
              {{ value }}
          </li>
          <li>
              库存状况
              {{ value }}
          </li>
          <li>
              资金状况
              {{ value }}
          </li>
          <li>
              来往状况
              {{ value }}
          </li>
      </ul>
  </div>

</template>

<script setup>
import {ref} from "vue";
import axios from "axios";
import api from "@/api/index.js";
const value = ref(100);

const {data: res} = await api.right.getVaews()

</script>

<style scoped lang="scss">

ul{
  display: flex;
}


</style>