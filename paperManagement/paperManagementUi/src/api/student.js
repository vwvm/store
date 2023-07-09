import {$get, $post, $put} from "@/utils/request";


export const getStudent = async (param) => {
    const {data} = await $get( "/student/student", param)
    return data
}
export const putStudent = async (param) => {
    const {data} = await $put("/student/student", param)
    return data
}

export const getStudentListByTeacher = async (param) => {
    const {data} = await $get("/student/getStudentListByTeacher", param)
    return data
}

export const getStudentList = async (param) => {

    const data = await $get("/student/getStudentList", param)
    return data
}

export const getNotStudentList = async (param) => {

    const data = await $get("/student/getNotStudentList", param)
    return data
}

