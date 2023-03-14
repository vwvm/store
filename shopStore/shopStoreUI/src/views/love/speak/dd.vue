<template>
  <div ref="hello">小猪课堂</div>
</template>
<script setup lang="ts">
import { onMounted, ref } from "vue";
const hello = ref<any>(null);
onMounted(() => {
  console.log(hello.value); // <div>小猪课堂</div>
});
</script>