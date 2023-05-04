<template>
    <el-progress :percentage="percentage"></el-progress>
    <h1>{{ title }}</h1>
    <el-button :disabled="isDisabled" @click="getProgress()">下载文件1</el-button>
    <a href="http://localhost:11014/minio/download?fileName=0123456+郑锦鸿-基于SpringBoot+++Vue的毕业论文管理系统的设计与实现-第一稿(1)+1682427233081.docx"
       download="0123456+郑锦鸿-基于SpringBoot+++Vue的毕业论文管理系统的设计与实现-第一稿(1)+1682427233081.docx">
        下载文件123123</a>
</template>

<script setup>

import {ref, defineProps} from "vue";
import axios from "axios";
import {useUser} from "@/store/index.js";

const props = defineProps([
    "fileName"
]);

const title = ref("请点击按钮进行下载。")
const percentage = ref(0) //进度条的占比
const isDisabled = ref(false)  //默认可以点击
const fileName = ref("0123456+郑锦鸿-基于SpringBoot + Vue的毕业论文管理系统的设计与实现-第一稿(1)+1682427233081.docx")

const getFile2 = ()=> {
    axios({

    })
}

function getProgress() {
    //进度条恢复为 0
    percentage.value = 0;
    //按钮置灰 不可点击
    isDisabled.value = true;
    //发起请求
    axios({
        //下载文件的远端地址。
        url: "http://localhost:11014/minio/download",
        headers: {
            token: useUser().getToken //获取token数据
        },
        //请求方式 get post。
        method: "get",
        //设置返回数据类型为base64的数据，这样才可以被new Blob() 解析。
        responseType: "blob",
        //请求参数 get-->params   post-->data 。
        params: {
            fileName: props.fileName
        },
        //xml返回数据的钩子函数，可以用来获取数据的进度 具体可查看axios的官网
        onDownloadProgress: (progressEvent) => {
            //progressEvent.loaded 下载文件的当前大小
            //progressEvent.total  下载文件的总大小 如果后端没有返回 请让他加上！
            let progressBar = Math.round(progressEvent.loaded / progressEvent.total * 100);
            //接收进度为99%的时候需要留一点前端编译的时间
            if (progressBar >= 99) {
                percentage.value = 99;
                title.value = '下载完成，文件正在编译。';
            } else {
                percentage.value = progressBar;
                title.value = '正在下载，请稍等。';
            }
        }
    }).then(res => {
        //当前定义res.message 出现的时候说明后端文件返回出现了问题 不然应该直接是个文件流
        if (res.message) {
            title.value = '下载失败'
            isDisabled.value = false
            return
        }
        //blob返回的是一个 base64 格式的文件流
        let blob = new Blob([res])
        //编译文件

        let Url = window.URL.createObjectURL(blob);
        let link = document.createElement("a");

        link.style.display = "none";
        link.href = Url;
        link.setAttribute("download", props.fileName);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(Url);

        //编译文件完成后，进度条展示为100%100
        percentage.value = 100;
        //下载完成 可以重新点击按钮下载
        isDisabled.value = false;
    })
}
</script>

<style scoped>

</style>
