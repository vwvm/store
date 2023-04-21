import {$delete, $get, $post, $put} from "@/utils/request";

export const deleteUserByUsername = async (param) => {
    const data = await $delete("/user/userByUsername", param)
    return data
}

export const getUserByUsername = async (param) => {
    const data = await $get("/user/userByUsername", param)
    console.log("123123", data)
    return data
}

export const putUser = async (param) => {

    const {data} = await $put("/user/user", param)
    return data
}

export const postUser = async (param) => {

    const {data} = await $post("/user/user", param)
    return data
}


export const postLogin = async (param) => {
    return await $post("/user/login", param)

}

export const getRegister = async (param) => {
    return await $post("/user/register", param)
}
export const getUserList = async (param) => {

    const data = await $get("/user/getUserList", param)
    return data
}


