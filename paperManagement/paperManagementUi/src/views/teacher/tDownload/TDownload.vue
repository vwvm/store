<template>
    <el-card>
        <el-menu bordered :data-source="downData" class="a-list">
            <el-menu-item slot="renderItem" slot-scope="item, index">

            </el-menu-item>
            <div slot="header">
                <h3>文件下载</h3>
            </div>
            <el-divider/>
            <el-table :data="downData" border style="width: 100%">
                <el-table-column prop="dataDescribe" label="标签"/>
                <el-table-column prop="dataName" label="文件名称"/>
                <el-table-column prop="updateTime" label="更新时间"/>
                <el-table-column prop="dataDescribe" label="操作" width="180" v-slot="slotProps">
                    <el-button type="primary" :icon="Download" @click="downloadFile(slotProps.row)"/>
                </el-table-column>
            </el-table>
            <br/>
            <el-pagination
                    v-model:current-page="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[5, 10, 15, 20]"
                    :small="small"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="getDownList()"
                    @current-change="getDownList()"
            />
        </el-menu>
    </el-card>

</template>

<script setup>
import {onMounted, ref} from "vue";
import api from "@/api/api.js";
import {Edit, Download} from "@element-plus/icons-vue";
import {minioBaseUrl} from "@/config/index.js"
import DownloadComponent from "@/components/load/DownloadComponent.vue";
import {get_file} from "@/utils/minioRequest.js";

// 当前页
const currentPage = ref(1)
// 页码大小
const pageSize = ref(5)
// 总数量
const total = ref(1)

const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 传递给下载组件
const fileName = ref("0123456+郑锦鸿-基于SpringBoot + Vue的毕业论文管理系统的设计与实现-第一稿(1)+127233081.docx")

const downData = ref([{
    "id": 5,
    "dataName": "毕业论文(设计)指导过程记录表",
    "dataDescribe": "范化要求",
    "dataUrl": "2023届毕业论文(设计）指导手册（印刷稿）.docx",
    "creationTime": "2023-04-03T11:59:31",
    "updateTime": "2023-04-03T11:59:31",
    "updateUserId": null,
    "deleteFlag": false
}])

const downloadFile = (slotProps) => {
    get_file("/minio/download", {fileName: slotProps.dataUrl})
}


// 获取文件下载列表
const getDownList = async () => {
    const page = {pageNum: currentPage.value, pageSize: pageSize.value};
    const {data, msg} = await api.__download.getDownloadList(page);
    total.value = Number.parseInt(msg);
    downData.value = data
};


onMounted(() => {
    getDownList()
})

</script>

<style scoped lang="scss" src="./TDownload.scss">

</style>
