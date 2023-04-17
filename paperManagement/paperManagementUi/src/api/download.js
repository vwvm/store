import {$get, $post} from "@/utils/request";

export const getDownloadList = async (param) => {
    const data = await $get("/dataDownload/dataDownloadList", param)
    return data;

}

export const getRegister = async (param) => {
    return await $post("/user/register", param)
}
