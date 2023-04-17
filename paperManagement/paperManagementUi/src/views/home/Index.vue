<template>
    <MyHeader/>
    <router-view></router-view>
    <MyFooter/>
</template>

<script setup>
import {ref, onMounted, computed, watch} from "vue";
import MyHeader from "@/components/Header.vue";
import MyFooter from "@/components/MyFooter.vue";
import {useMenuActive} from '@/store';
import {useRoute} from "vue-router";
// 菜单高亮索引
const menuActive = useMenuActive()
const activeIndex = ref(menuActive.activeIndex)




const route = useRoute()
watch(() => route.path, (val) => {
    menuActive.setActiveIndex(val)
}, {
    //立刻执行一次
    immediate: true
})


onMounted(() => {
    if (sessionStorage.getItem("menuActive")) {
        activeIndex.value = sessionStorage.getItem("menuActive")
    }
})

</script>

<style scoped lang="scss" src="./index.scss"></style>