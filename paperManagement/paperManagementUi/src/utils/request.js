import axios from "axios";
import {serverBaseUrl} from "@/config/index.js";

const request = axios.create({
    baseURL : serverBaseUrl,
    timeout: 5000
})

export const $get = async (url, params) => {
    let {data} = await request.get(url, {params})
    console.log(data)
    return data;
}
export const $post = async (url, params) => {
    let {data} = await request.post(url, params)
    console.log(data)
    return data;
}
export const $put = async (url, params) => {
    let {data} = await request.put(url, params)
    console.log(data)
    return data;
}
export const $delete = async (url, params) => {
    let {data} = await request.delete(url, {params})
    console.log(data)
    return data;
}


// 添加请求拦截器
request.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    if(!config.headers['token']){
        config.headers['token'] =  localStorage.getItem("token");
    }
    return config
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
request.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response;
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

export default request;
