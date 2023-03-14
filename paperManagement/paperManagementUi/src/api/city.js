import request from "@/utils/request";

export function cityDelete(params) {
    return request({
        url: "/city",
        method: "delete",
        params
    })
}


export function list(params) {
    return request({
        url: "/city",
        params
    })
}

export function add(data) {
    return request({
        url: "/city",
        method: "post",
        data
    })
}
export function edit(data) {
    return request({
        url: "/city",
        method: "put",
        data
    })
}

