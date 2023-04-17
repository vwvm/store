import {createApp} from 'vue'
import App from './App.vue'
import router from "./router/index.js";
import { createPinia } from 'pinia'
// 全局注册组件
import MyHeader from "@/components/Header.vue";
import MyFooter from "@/components/MyFooter.vue";
// 全局注册css
import 'element-plus/theme-chalk/el-loading.css';
import 'element-plus/theme-chalk/el-message.css';

window.router=router

let app = createApp(App)
app.use(router)
app.use(createPinia())
app.component('MyHeader', MyHeader)
app.component("MyFooter", MyFooter)
app.mount('#app')
