import {createRouter, createWebHistory} from 'vue-router'
import Nprogress from 'nprogress'
import 'nprogress/nprogress.css'

const routes = [

    {
        path: '/',
        redirect: '/home',
    },
    {
        path: "/index",
        name: "Index",
        meta: {
            title: "首页"
        },
        redirect: "/home",
        component: () => import("@/views/home/Index.vue"),
        children: [
            {
                path: '/home',
                name: "Home",
                component: () => import("@/views/home/Home.vue"),
            },
            {
                path: "/admin",
                name: "Admin",
                meta: {title: "教师"},
                component:()=>import("@/views/admin/AFrame.vue"),
                // redirect: "/AUser",
                children: [
                    {
                        path: "/adminControl",
                        name: "AdminControl",
                        meta: {title: "管理员管理"},
                        component:()=>import("@/views/admin/adminControl/AdminControl.vue"),
                    },
                    {
                        path: "/studentControl",
                        name: "StudentControl",
                        meta: {title: "学生管理"},
                        component:()=>import("@/views/admin/studentControl/StudentControl.vue"),
                    },
                    {
                        path: "/teacherControl",
                        name: "TeacherControl",
                        meta: {title: "教师管理"},
                        component:()=>import("@/views/admin/teacherControl/TeacherControl.vue"),
                    },
                    {
                        path: "/userControl",
                        name: "UserControl",
                        meta: {title: "用户管理"},
                        component:()=>import("@/views/admin/userControl/UserControl.vue"),
                    },

                ]
            },
            {
                path: "/teacher",
                name: "Teacher",
                meta: {title: "教师"},
                component:()=>import("@/views/teacher/TFrame.vue"),
                redirect: "/TUser",
                children: [
                    {
                        path: "/TUser",
                        name: "TUser",
                        meta: {title: "教师"},
                        component:()=>import("@/views/teacher/tUser/TUser.vue"),
                    },
                    {
                        path: "/tProject",
                        name: "TProject",
                        meta: {title: "课题申报"},
                        component:()=>import("@/views/teacher/tProject/TProject.vue"),
                    },
                    {
                        path: "/tVerifyProject",
                        name: "TVerifyProject",
                        meta: {title: "确认选题"},
                        component:()=>import("@/views/teacher/tVerifyProject/TVerifyProject.vue"),
                    },
                    {
                        path: "/TProcessManager",
                        name: "TProcessManager",
                        meta: {title: "教师"},
                        component:()=>import("@/views/teacher/tProcessManager/TProcessManager.vue"),
                    },
                    {
                        path: "/TScore",
                        name: "TScore",
                        meta: {title: "教师"},
                        component:()=>import("@/views/teacher/tScore/TScore.vue"),
                    },
                    {
                        path: "/TLibrary",
                        name: "TLibrary",
                        meta: {title: "教师"},
                        component:()=>import("@/views/teacher/tLibrary/TLibrary.vue"),
                    },
                    {
                        path: "/TDownload",
                        name: "TDownload",
                        meta: {title: "教师"},
                        component:()=>import("@/views/teacher/tDownload/TDownload.vue"),
                    },
                ]
            },
            {
                path: "/frame",
                name: "Frame",
                meta: {title: "框架"},
                component:()=>import("@/views/frame/Frame.vue")

            },
        ]
    },
    {
        path: "/captcha",
        name: "Captcha",
        meta: {
            title: "验证码"
        },
        component: () => import("@/components/captcha/captcha.vue")
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