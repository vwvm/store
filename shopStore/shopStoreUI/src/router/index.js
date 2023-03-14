import {createRouter, createWebHistory} from 'vue-router'
import Nprogress from 'nprogress'
import 'nprogress/nprogress.css'

const routes = [

    {
        path: '/',
        redirect: '/home',
    },
    {
        path: "/home",
        name: "Index",
        meta: {
            title: "首页"
        },
        component: () => import("@/views/home/Index.vue"),
        children: [
            {
                path: '/home',
                component: () => import("@/views/home/Home.vue"),
            },
            {
                path: "/goods",
                name: "Goods",
                meta: {
                    title: "商品"
                },
                component: () => import("@/views/goods/Goods.vue")
            },
            {
                path: "/detail",
                name: "Detail",
                meta: {
                    title: "商品详情"
                },
                component: () => import("@/views/detail/Detail.vue")
            },
        ]
    },
    {
        path: '/love',
        name: 'Love',
        meta: {
            title: "我们的小家"
        },
        component: () => import("@/views/love/index.vue"),
        children: [
            {
                path: '/baikuai',
                name: 'baikuai',
                meta: {
                    title: "给小公主做的第一个小游戏"
                },
                component: () => import("@/views/love/baikuai.vue")
            },
            {
                path: '/xiangce',
                name: 'Xiangce',
                meta: {
                    title: "我们的相册"
                },
                component: () => import("@/views/love/xiangce.vue")
            },
            {
                path: '/speak',
                name: 'Speak',
                meta: {
                    title: "说的话"
                },
                component: () => import("@/views/love/speak/Speak.vue")
            },

        ]
    },
    {
        path: '/showLove2',
        name: 'ShowLove2',
        meta: {
            title: "我们的相册"
        },
        component: () => import("@/views/love/speak/Love.vue")
    },
    {
        path: '/loveYou',
        name: 'ShowLove',
        meta: {
            title: "I LOVE YOU"
        },
        component: () => import("@/views/love/speak/Love2.vue")
    },

    {
        path: "/shoppingCart",
        name: "ShoppingCart",
        meta: {
            title: "购物车"
        },
        component: () => import("@/views/shoppingCart/ShoppingCart.vue")
    },
    {
        path: "/detail",
        name: "Detail",
        meta: {
            title: "商品详情"
        },
        component: () => import("@/views/detail/Detail.vue")
    },
    {
        path: "/order",
        name: "Order",
        meta: {
            title: "订单"
        },
        component: () => import("@/views/order/Order.vue")
    },
    {
        path: "/collect",
        name: "Collect",
        meta: {
            title: "收藏"
        },
        component: () => import("@/views/collect/Collect.vue")
    },
    {
        path: "/about",
        name: "About",
        meta: {
            title: "收藏"
        },
        component: () => import("@/views/about/About.vue")
    },
    {
        path: "/test",
        name: "Test",
        meta: {
            title: "收藏"
        },
        component: () => import("@/views/test/Test.vue")
    },
    {
        path: '/:pathMatch(.*)',
        name: "Error",
        meta: {
            title: "错误"
        },
        component: () => import("@/views/error/error_400/404.vue")
    }
];

const router = createRouter({
    routes, history: createWebHistory()
})

router.beforeEach((to, from, next) => {
    Nprogress.start()
    next()
})
router.afterEach((to, from) => {
    Nprogress.done()
    document.title = to.meta.title
})

export default router;