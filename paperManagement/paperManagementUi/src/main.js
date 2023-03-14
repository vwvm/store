import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn' //中文
// import './assets/main.css'
import api from "@/api";


const app = createApp(App)


app.config.globalProperties.$api = api;
app.use(ElementPlus,{locale})
app.use(router)
// app.use(ElementPlus)
app.mount('#app')
