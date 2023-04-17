import {$get, $post} from "@/utils/request";


export const getCaptcha = async () => {
    const {data} = await $get("/captcha")
    return data
}

export const validateCaptcha = async (params) => {
    const {data} = await $post("/validateCaptcha", params)
    return data
}
