import {$get, $post, $put} from "@/utils/request";

const baseUrl = "/download";

export const getDownloadList = async (param) => {
    const data = await $get("/download/dataDownloadList", param)
    return data;
}

export const getRegister = async (param) => {
    return await $get(baseUrl + "/register", param)
}

export const getAuditMark = async (param) => {
    return await $get("/download/getAuditMark", param)
}
export const getAuditMarkList = async (param) => {
    return await $get("/download/getAuditMarkList", param)
}

export const getDownloadListByTeacher = async (param) => {
    const data = await $get("/download/getDownloadListByTeacher", param)
    return data;
}
export const getDownloadFileName = async (param) => {
    const data = await $get("/download/getDownloadFileName", param)
    return data;
}

export const putAuditMark = async (param) => {
    const data = await $put("/download/putAuditMark", param)
    return data;
}
