import {$get} from "@/utils/request";

export const carouselList = async () => {
    const {data} = await $get("/index/img-list")
    return data
}