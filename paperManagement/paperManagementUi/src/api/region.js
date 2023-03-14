import request from "@/utils/request";

export function regionDelete(params) {
    return request({
        url: "/region",
        method: "delete",
        params
    })
}


export function list(params) {
    return request({
        url: "/region",
        params
    })
}

export function add(data) {
    return request({
        url: "/region",
        method: "post",
        data
    })
}
export function edit(data) {
    return request({
        url: "/region",
        method: "put",
        data
    })
}

