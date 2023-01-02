import request from "@/utils/request";


/**
 * 时间折线图请求接口
 * @returns {*}
 */
export function getReport() {
    return request({
        url: `/reports/type/1`,
    })
}
