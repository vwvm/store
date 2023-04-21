import {$get, $post} from "@/utils/request";

const BaseUrl = "/department"

export const getDepartmentList = async (param) => {
    const data = await $get(BaseUrl + "/getDepartmentList", param)
    return data;

}

export const getRegister = async (param) => {
    return await $post("/user/register", param)
}
