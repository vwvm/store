<template>
    <vue-office-docx
        :src="docx"
        style="height: 100vh;"
        @rendered="renderedHandler"
        @error="errorHandler"
    />
</template>

<script>
//引入VueOfficeDocx组件
import VueOfficeDocx from '@vue-office/docx'
//引入相关样式
import '@vue-office/docx/lib/index.css'

export default {
    components: {
        VueOfficeDocx
    },
    data() {
        return {
            docx: 'http://localhost:49090/browser/paper/0123456+郑锦鸿-基于SpringBoot + Vue的毕业论文管理系统的设计与实现-第一稿(1)+1682427233081.docx' //设置文档网络地址，可以是相对地址
        }
    },
    methods: {
        renderedHandler() {
            console.log("渲染完成")
        },
        errorHandler() {
            console.log("渲染失败")
        }
    }
}
</script>
