import {createRouter, createWebHistory} from 'vue-router'
import Nprogress from 'nprogress'
import 'nprogress/nprogress.css'

const routes = [

    {
        path: '/',
        redirect: '/home',
    },
    {
        path: '/home',
        name: "Home",
        component: () => import("@/views/home/Home.vue"),
    },
    {
        path: "/admin",
        name: "Admin",
        meta: {title: "管理员"},
        component: () => import("@/views/admin/AFrame.vue"),
        // redirect: "/AUser",
        children: [
            {
                path: "/adminControl",
                name: "AdminControl",
                meta: {title: "管理员管理"},
                component: () => import("@/views/admin/adminControl/AdminControl.vue"),
            },
            {
                path: "/studentControl",
                name: "StudentControl",
                meta: {title: "学生管理"},
                component: () => import("@/views/admin/studentControl/StudentControl.vue"),
            },
            {
                path: "/teacherControl",
                name: "TeacherControl",
                meta: {title: "教师管理"},
                component: () => import("@/views/admin/teacherControl/TeacherControl.vue"),
            },
            {
                path: "/userControl",
                name: "UserControl",
                meta: {title: "用户管理"},
                component: () => import("@/views/admin/userControl/UserControl.vue"),
            },
            {
                path: "/download",
                name: "UserControl1",
                meta: {title: "用户管理"},
                component: () => import("@/views/admin/downloadControl/DownloadControl.vue"),
            },
            {
                path: "/downloadControl",
                name: "downloadControl",
                meta: {title: "用户管理"},
                component: () => import("@/views/admin/downloadControl/downloadControl.vue"),
            },

        ]
    },
    {
        path: "/teacher",
        name: "Teacher",
        component: () => import("@/views/teacher/TFrame.vue"),
        children: [
            {
                path: "",
                meta: {title: "教师"},
                redirect: "/tUser",
            },
            {
                path: "/tUser",
                name: "TUser",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tUser/TUser.vue"),
            },
            {
                path: "/tProject",
                name: "TProject",
                meta: {title: "课题管理"},
                component: () => import("@/views/teacher/tProject/TProject.vue"),
            },
            {
                path: "/tVerifyProject",
                name: "TVerifyProject",
                meta: {title: "确认选题"},
                component: () => import("@/views/teacher/tVerifyProject/TVerifyProject.vue"),
            },
            {
                path: "/tSubmitManuscript",
                name: "TSubmitManuscript",
                meta: {title: "过程文档管理"},
                component: () => import("@/views/teacher/tSubmitManuscript/TSubmitManuscript.vue"),
            },
            {
                path: "/tCommitTermination",
                name: "TCommitTermination",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tCommitTermination/TCommitTermination.vue"),
            },
            {
                path: "/tStudentManage",
                name: "TStudentManage",
                meta: {title: "学生管理"},
                component: () => import("@/views/teacher/tStudentManage/TStudentManage.vue"),
            },
            {
                path: "/tLibrary",
                name: "TLibrary",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tLibrary/TLibrary.vue"),
            },
            {
                path: "/tDownload",
                name: "TDownload",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tDownload/TDownload.vue"),
            },
        ]
    },
    {
        path: "/student",
        name: "Student",
        meta: {title: "学生"},
        component: () => import("@/views/student/SFrame.vue"),
        redirect: "/sUser",
        children: [
            {
                path: "/sUser",
                name: "SUser",
                meta: {title: "教师"},
                component: () => import("@/views/student/sUser/SUser.vue"),
            },
            {
                path: "/sProject",
                name: "SProject",
                meta: {title: "课题申报"},
                component: () => import("@/views/student/sProject/SProject.vue"),
            },
            {
                path: "/sOpeningReport",
                name: "SOpeningReport",
                meta: {title: "确认选题"},
                component: () => import("@/views/student/sOpeningReport/SOpeningReport.vue"),
            },
            {
                path: "/sSubmitManuscript",
                name: "SSubmitManuscript",
                meta: {title: "提交过程稿件"},
                component: () => import("@/views/student/sSubmitManuscript/SSubmitManuscript.vue"),
            },
            {
                path: "/sCommitTermination",
                name: "SCommitTermination",
                meta: {title: "教师"},
                component: () => import("@/views/student/sCommitTermination/SCommitTermination.vue"),
            },
            {
                path: "/sLibrary",
                name: "SLibrary",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tLibrary/TLibrary.vue"),
            },
            {
                path: "/sDownload",
                name: "SDownload",
                meta: {title: "教师"},
                component: () => import("@/views/teacher/tDownload/TDownload.vue"),
            },
        ]
    },
    {
        path: "/test",
        name: "Test",
        meta: {title: "测试"},
        component: () => import("@/views/test/Test.vue"),
        children: [{
            path: "number1",
            name: "Number1",
            component: () => import("@/views/test/number/Number1.vue")
        }]
    },
    {
        path: "/frame",
        name: "Frame",
        meta: {title: "框架"},
        component: () => import("@/views/frame/Frame.vue")

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
