<template>
    <!--{{ data.otherParam }}-->
    <el-upload
            ref="exampleUploadRef"
            :file-list="data.fileList"
            :action="data.postUrl"
            :limit="1"
            list-type="text"
            accept=".docx"
            class="avatar-uploader"
            :before-remove="beforeRemove"
            :before-upload="beforeUpload"
            :on-remove="handleRemove"
            :name="data.fileName"
            :data="data.otherParam"
            :headers="data.headers"
            :on-preview="handlePreview"
            :on-exceed="handleExceed"
            :multiple="false"
            :on-success="uploadSuccess"
            :on-error="errorMessage"
            :on-change="handleChange"
    >
        <el-button type="primary">
            {{ message? message: '点击上传文件' }}
        </el-button>

        <template #tip>
            <div class="el-upload__tip text-red">只能上传docx文件</div>
        </template>
    </el-upload>
</template>
<script setup>
import {useUser} from "@/store/index.js";
import {onMounted, reactive, defineProps, toRefs} from "vue";
import {ElMessage} from "element-plus";

// 用户id  ， 编号1 ，编号2
const props = defineProps([
    "otherParam", "message"
])
const {otherParam, message} = toRefs(props);
/*
*
const otherParam = reactive({
    user_id: useUser().getId,
    data_index1: 2,
    data_index2: 1,
    data_name: "",
    data_describe: "",
})
* */
const data = reactive({
    //用于指定上传文件的字段名，也就是在后端接收上传文件时需要使用的字段名。在前端发送上传请求时，请求头中会包含该字段名和对应的文件数据，后端就可以通过这个字段名获取到上传的文件数据
    fileName: "multipartFile",
    headers: {
        token: useUser().getToken //获取token数据
    },
    otherParam,
    fileList: [],
    postUrl: ""
})
const beforeRemove = (file, uploadFiles) => {
    if (this.status === 'view') {
        ElMessage({
            message: "不允许删除！",
            type: "error",
            offset: 60
        });
        return false
    } else {
        return true
    }
};
//文件移除操作
const handleRemove = (file, fileList) => {
    data.fileList = fileList//或者data.fileList = []; //清空fileList所有数组
};
//上传文件失败
const errorMessage = (response) => {
    return ElMessage({
        message: "文件上传失败，请联系管理员",
        type: "error",
        offset: 60
    });
};
//上传文件成功
const uploadSuccess = (response, file, fileList) => {
    console.log("response", response)
    data.fileList.push({//得到的文件数据转换成 name url方式
        name: file.fileOrigName,
        url: file.filePreviewUrl
    });
};
//文件超出个数限制时的钩子
const handleExceed = (files, fileList) => {
};
//点击文件列表中已上传的文件时的钩子,进行下载
const handlePreview = (file) => {
    console.log("file", file);
    const link = document.createElement("a");    //定义一个a标签
    link.download = file.name; //下载后的文件名称
    link.href = file.url; //需要生成一个 URL 来实现下载
    link.click(); //模拟在按钮上实现一次鼠标点击
    window.URL.revokeObjectURL(link.href);
};
const handleChange = (uploadFile, uploadFiles) => {
};
//判断上传之前文件的格式
const beforeUpload = (file) => {
    const isLt20M = file.size / 1024 / 1024 < 20;
    if (!isLt20M) {
        ElMessage({
            message: "上传大小不能超过 20MB!",
            type: "error",
            offset: 60
        });
    }
    const fileType = file.name.substring(file.name.lastIndexOf(".") + 1);
    const isFile = fileType === "pdf" || fileType === "docx";

    if (!isFile) {
        ElMessage({
            message: "请选择正确的文件格式",
            type: "error",
            offset: 60
        });
        return false;
    }
    return isLt20M && isFile;
};

onMounted(async () => {
    data.postUrl = "http://localhost:11014/minio/upload";
});
</script>

<style lang="scss" scoped src="./Upload.scss">

</style>
