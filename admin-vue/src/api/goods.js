import request from "@/utils/request";

export function getMenus() {
    return request({
        url: "menus",
    })
}

/**
 * 请求权限
 * @param type list返回列表，tree返回树形结构
 * @returns {*}
 */
export function getRight(type) {
    return request({
        url: `/rights/` + type,
    })
}


export function getRoleList() {
    return request({
        url: `/roles`,
    })
}

export function deleteRoleRight(roleId, rightId) {
    return request({
        url: `/roles/${roleId}/rights/${rightId}`,
        method: "delete",
    })
}

/**
 * 分配权限请求接口
 * @param roleId 角色id
 * @param data 权限列表{""""}
 * @returns {*}
 */
export function postPermission(roleId, data) {
    return request({
        url: `roles/${roleId}/rights`,
        method: "post",
        data
    })
}
