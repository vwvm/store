import {defineStore} from 'pinia'

// 创建useMenuActive管理首页菜单索引
export const useMenuActive = defineStore('menuActive', {
    // 定义状态
    state: () => {
        return {
            activeIndex: sessionStorage.getItem("menuActive") || '/home'
        }
    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setActiveIndex(value) {
            sessionStorage.setItem("menuActive", value);
            this.activeIndex = value
        },

    }
})

// 创建useMenuActive管理首页菜单索引
export const useUser = defineStore('user', {
    // 定义状态
    state: () => {
        return {
            user: {
                userid:'',
                username:'',

            }
        }
    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setUser(value) {
            this.user = value;
        },

    }
})
