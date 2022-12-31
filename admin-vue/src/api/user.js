import request from "@/utils/request";

export function login(params = {}) {
    return request({
        url: "/login",
        method: "post",
        params
    })
}

export function getUsers(params = {}) {
    return request({
        url: "/users",
        method: "get",
        params
    })
}

export function changeState(params = {}) {
    return request({
        url: `/users/${params.id}/state/${params.mg_state}`,
        method: "put",
    })
}

export function addUser(data = {}) {
    return request(
        {
        url: `/users`,
        method: "post",
        data
    })
}

export function editUser(data = {}) {
    return request(
        {
            url: `/users/${data.id}`,
            method: "put",
            data
        })
}

export function deleteUser(data = {}) {
    return request(
        {
            url: `/users/${data.id}`,
            method: "delete",
        })
}
