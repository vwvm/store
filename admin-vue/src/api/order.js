import request from "@/utils/request";


/**
 * 订单列表请求接口
 * @returns {*}
 */
export function getOrders(params={}) {
    return request({
        url: `/orders`,
        params
    })
}
