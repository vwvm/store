import {$get, $post, $put} from "@/utils/request";


const BaseUrl = "/project";

export const getProjectList = async (param) => {
    return await $get("/project/getProjectList", param)
}

export const postProject = async (param) => {
    const {data} = await $post("/project/postProject", param)
    return data
}
export const putProject = async (param) => {
    const {data} = await $put("/project/putProject", param)
    return data
}

export const selectedProject = async (param) => {
    return await $get("/project/selectedProject", param)
}

// 按类别请求
export const getProductSearchType = async (param) => {
    const {data} = await $get("/product/productSearchType", param)
    return data
}

// 按内容，类别，页码请求
export const getProductSearchPage = async (param) => {
    const {data} = await $get("/product/productSearchPage", param)
    return data
}

// 获取单个商品详细信息
export const getProductSpecOne = async (param) => {
    const {data} = await $get("/product/productSpecificationId", param)
    return data
}

// 获取单个商品信息
export const getProductOne = async (param) => {
    const {data} = await $get("/product/productId", param)
    return data
}
