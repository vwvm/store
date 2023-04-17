<template>
    <el-image :src="captchaImg" @click="getCaptcha()"></el-image>
</template>

<script setup>
import api from "@/api/api.js";
import {ref, onMounted} from "vue"

let captcha = ref("");
const captchaImg = ref("");
const getCaptcha = async () => {
    captcha.value = await api.__captcha.getCaptcha();
    captchaImg.value = captcha.value.captchaImg
    console.log(captcha.value)
}
onMounted(() => {
    getCaptcha()
})
</script>

<style scoped lang="scss">

</style>