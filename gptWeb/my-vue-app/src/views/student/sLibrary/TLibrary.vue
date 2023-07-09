<template>
    <el-card title="选题列表">

        <el-menu
                rowKey="id"
                :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1}"
                :dataSource="paperlib"
                class="card-list"
        >
            <el-menu-item slot="renderItem" slot-scope="item">
                <template v-if="!item || item.id === undefined">
                    <el-button class="new-btn" type="dashed">
                        <el-icon type="plus" />新增选题
                    </el-button>
                </template>
                <template v-else>
                    <el-card :hoverable="true">
                        <!--<el-card-meta>-->
                        <!--    <a slot="title">{{ item.name }}</a>-->
                        <!--    &lt;!&ndash;<el-avatar-->
                        <!--        class="card-avatar"-->
                        <!--        slot="avatar"-->
                        <!--        :src="item.avatar"-->
                        <!--        size="large"-->
                        <!--    />&ndash;&gt;-->
                        <!--    <div class="meta-content" slot="description">{{ item.info }}</div>-->
                        <!--</el-card-meta>-->
                        <template class="ant-card-actions" slot="actions">
                            <a>编辑</a>
                            <a @click="delete(item.id)">删除</a>
                        </template>
                    </el-card>
                </template>
            </el-menu-item>
        </el-menu>
    </el-card>
</template>
<script setup>
import {ref, reactive} from "vue";
const paperlib = reactive([]);

</script>

<style scoped lang="scss" src="./TLibrary.scss">

</style>