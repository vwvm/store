import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'

const routes = [
    {
        path: "/",
        redirect: "/home",
    },
    {
        path: "/home",
        component: () => import("@/views/Home.vue"),
        name: "home",
        children: [
            {
                path: "users/userList",
                component: () => import("@/views/users/UserList.vue"),
            },
            {
                path: "rights/rightList",
                component: () => import("@/views/right/RightList.vue"),
            },
            {
                path: "rights/roleList",
                component: () => import("@/views/right/RoleLIst.vue"),
            },
            {
                path: "goods/categories",
                component: () => import("@/views/goods/Categories.vue")
            }
        ]
    },
    {
        path: "/login",
        component: () => import("@/views/Login.vue"),
        name: "login"
    },
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: import("@/views/404.vue") },
]

const router = createRouter({
    // mode:history,
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
    if (to.path === "/login") return next()
    const token = window.sessionStorage.getItem("token")
    if (token) return next()
    return next("/login")

})
router.beforeResolve((to, from, next) => {
    next()
})

export default router;
