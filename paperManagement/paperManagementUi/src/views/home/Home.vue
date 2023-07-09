<template>
    <el-row>
        <el-col :span="20" :offset="2">
            <!-- 设置学校背景图  -->
            <el-image style="width: 100%" :src="imgUrl" :fit="'contain'"/>
            <!--  主体  -->
            <el-row :gutter="20">
                <el-col :span="14">

                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span>通知公告</span>
                            </div>
                        </template>
                        <div v-for="o in 4" :key="o" class="text item announcement" style="">
                            <span>
                                 {{ 'List item ' + o }}
                            </span>
                        </div>
                        <el-button class="button" text>
                            加载更多
                        </el-button>
                    </el-card>
                    <!--<div style="width: 100%; height: 100%; border: red 1px solid"/>-->
                </el-col>
                <el-col :span="10">
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span>文件下载</span>
                            </div>
                        </template>
                        <div v-for="o in 11" :key="o" class="text item file-download" style="">
                            <span>
                                {{ 'List item ' + o + 1 + "ddddd" }}
                            </span>
                        </div>
                        <el-button class="button" text>
                            加载更多
                        </el-button>
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>

<script setup>
import {ref} from "vue";

const imgUrl = ref("/src/assets/school/lyun.png")
</script>


<style scoped lang="scss">
.flex-grow {
  flex-grow: 1;
}

.content {
  width: 80vw;
  margin: 20px auto;
  //border-bottom: 1px solid #b0b0b0;
  .el-menu--horizontal > .el-menu-item {
    border-bottom: none;
    text-decoration: none;
  }
}

:deep(.pr) {

}

</style>
