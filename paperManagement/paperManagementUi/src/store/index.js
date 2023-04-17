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
    state: () => ({
        user: {
            id: '',
            username: '',
            token: '',
            isTrue: "",
            userTelephone: '',
            role: [],
        }

    }),
    getters: {
        getUsername() {
            return this.user.username
        },
        getId() {
            return this.user.id
        },
        getUserTelephone() {
            return this.user.userTelephone
        },
        getToken() {
            return this.user.token
        },

    },
    // 操作状态的方法
    actions: {
        // 设置索引
        setUser(value) {
            this.user = value;
        },
        // 清空
        empty() {
            this.user = {
                id: '',
                username: '',
                token: '',
                isTrue: "",
                userTelephone: '',
                role: [],
            }
        }
    }
})
