import {$get, $post} from "@/utils/request";

export const getLogin = async (param) => {
    return await $get("/user/login", param)

}

export const getUserById = async (param) => {
    const {data} = await $get("/user/getUserById", param)
    return data
}

export const postLogin = async (param) => {
    return await $post("/user/login", param)

}

export const getRegister = async (param) => {
    return await $post("/user/register", param)
}
export const getUserList = async (param) => {
    return await $get("/user/getUserList", param)
}
