import request from "@/utils/request.js";

export function getMenus(params = {}) {
    return request({
        url: "/user/menus",
    })
}
