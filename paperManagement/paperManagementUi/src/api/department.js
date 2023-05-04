import {$get, $post} from "@/utils/request";



const BaseUrl = "/department"

export const getDepartmentList = async (param) => {
    return await $get(BaseUrl + "/getDepartmentList", param);

}

export const getRegister = async (param) => {
    return await $post("/user/register", param)
}
