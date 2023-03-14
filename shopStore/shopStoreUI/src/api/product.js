import {$get} from "@/utils/request";

export const productCategory = async () => {
    const {data} = await $get("/product/productCategory")
    return data
}

export const getProduct = async () => {
    const {data} = await $get("/product/product")
    return data
}

export const getProductSearch = async (param) => {
    const {data} = await $get("/product/productSearch", param)
    return data
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