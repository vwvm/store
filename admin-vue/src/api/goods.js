import request from "@/utils/request";


/**
 * 商品分类请求接口
 * @returns {*}
 */
export function getCategories() {
    return request({
        url: `/categories`,
    })
}

/**
 * 商品列表请求接口
 * @returns {*}
 */
export function getGoods(params={}) {
    return request({
        url: `/goods`,
        params
    })
}
