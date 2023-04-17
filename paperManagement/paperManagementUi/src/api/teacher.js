import {$get, $post} from "@/utils/request";

export const getTeacher = async (param) => {
    const {data} = await $get("/teacher/getTeacher", param)
    return data
}