<template>
    <el-button type="primary" @click="opMinio()">minio管理</el-button>
</template>
<script setup>
const opMinio = ()=>{
    window.open('http://localhost:49090')
}

</script>
