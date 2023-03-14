import {createRouter, createWebHistory} from 'vue-router'

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
                path: "city",
                component: () => import("@/views/city/CityList.vue"),
            },
            {
                path: "cityAdd",
                component: () => import("@/views/city/CityAdd.vue"),
            },
            {
                path: "cityEdit",
                component: () => import("@/views/city/CityEdit.vue"),
            },
            {
                path: "region",
                component: () => import("@/views/region/RegionList.vue"),
            },
            {
                path: "regionAdd",
                component: () => import("@/views/region/RegionAdd.vue"),
            },
            {
                path: "regionEdit",
                component: () => import("@/views/region/RegionEdit.vue"),
            },
            {
                path: "regionShow",
                component: () => import("@/views/region/RegionShow.vue"),
            }
        ]
    },
    {
        path: "/login",
        component: () => import("@/views/Login.vue"),
        name: "login"
    },
    {path: '/:pathMatch(.*)*', name: 'NotFound', component: import("@/views/404.vue")},
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
