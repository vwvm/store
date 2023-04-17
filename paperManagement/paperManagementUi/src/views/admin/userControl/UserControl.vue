<template>
    <el-card>
        <el-menu bordered :data-source="downData" class="a-list">
            <el-menu-item slot="renderItem" slot-scope="item, index">

            </el-menu-item>
            <div slot="header">
                <h3>用户管理</h3>
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
                    v-model:current-page="page.currentPage"
                    v-model:page-size="page.pageSize"
                    :page-sizes="[5, 10, 15, 20]"
                    :small="small"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="getUserList()"
                    @current-change="getUserList()"
            />
        </el-menu>
    </el-card>
</template>

<script setup>
import {onMounted, ref, reactive} from "vue";
import * as userApi from "@/api/user.js";
import {Edit, Download} from "@element-plus/icons-vue";

const total = ref(1)

const small = ref(false)
const background = ref(false)
const disabled = ref(false)

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

const page = reactive({
    currentPage: 1,
    pageSize: 5,
})


const getUserList = () => {
    const value = userApi.getUserList(page)
    console.log(value)
}

// 获取文件下载列表;


onMounted(() => {
    getUserList()
})

</script>

<style scoped lang="scss" src="./UserControl.scss">

</style>
