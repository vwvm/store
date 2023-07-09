import axios from "axios";
import {serverBaseUrl} from "@/config/index.js";

const request = axios.create({
    baseURL : serverBaseUrl,
    timeout: 5000,
    responseType: "blob",
})

export const get_file = async (url, params) => {
    let {data} = await request.get(url, {params})
    const blob = new Blob([data])

    const fileName = params.fileName;
    if ('download' in document.createElement('a')) { // 非IE下载
        const elink = document.createElement('a')
        elink.download = fileName
        elink.style.display = 'none'
        elink.href = URL.createObjectURL(blob)
        document.body.appendChild(elink)
        elink.click()
        URL.revokeObjectURL(elink.href) // 释放URL 对象
        document.body.removeChild(elink)
    } else { // IE10+下载

        navigator.msSaveBlob(blob, fileName)
    }


    console.log(data)
    return data;
}

