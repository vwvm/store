import {$get, $post} from "@/utils/request";

const BaseUrl = "/teacher"

export const getTeacher = async (param) => {
    const {data} = await $get(BaseUrl + "/getTeacherByUserId", param)
    return data
}

export const getTeacherList = async (param) => {
    return await $get(BaseUrl + "/getTeacherList", param);
}
