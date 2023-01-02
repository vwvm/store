import {createApp} from 'vue'
// import './style.css'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import './assets/main.css'
import api from "@/api";


const app = createApp(App)

app.config.globalProperties.$api = api;
app.use(router)
app.use(ElementPlus)
app.mount('#app')
