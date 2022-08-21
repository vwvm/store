import {createRouter, createWebHistory,} from 'vue-router'

// 1. 定义路由组件.
// 也可以从其他文件导入
const Home = { template: '<div>Home4</div>' }
const About = { template: '<div>About</div>' }
import HeadRouter from "../view/HeadRouter.vue";
import router3 from "../view/router3.vue";
import router4 from "../view/router4.vue";
import login from "../view/login.vue";
import elementPlusTest from "../view/elementPlusTest.vue";
import register from "../view/register.vue";
// 2. 定义一些路由
// 每个路由都需要映射到一个组件。
// 我们后面再讨论嵌套路由。
const routes = [
    { path: '/', component: HeadRouter },
    { path: '/about', component: About },
    { path: '/router', component: HeadRouter},
    {
        path: '/router3',
        component: router3,
        children:[
            { path: 'about', component: About },
        ]
    },
    { path: '/router4/:id', component: router4, props: true},
    { path: '/login', component: login},
    { path: '/elementPlusTest', component: elementPlusTest},
    { path: '/register', component: register},
]

// 3. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，
    // createWebHashHistory（） 我们在这里使用 hash 模式。
    // createWebHistory() 为 history实现
    history: createWebHistory(),
    routes, // `routes: routes` 的缩写
})

export default router