import {$get, $post} from "@/utils/request";

export const getLogin = async (param) => {
    return await $get("/users/login", param)

}

export const getRegister = async (param) => {
    return await $post("/users/register", param)
}