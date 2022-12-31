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
