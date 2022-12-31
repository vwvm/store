import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver()],
        }),
    ],
    resolve: {
        // 起个别名，在引用资源时，可以用‘@/资源路径’直接访问
        alias: {
            'vue': 'vue/dist/vue.esm-bundler.js',
        }
    },
    server: {
        host: '0.0.0.0',
        port: 9091,
        open: false,
        proxy: {
            '^api': {
                target: "http://127.0.0.1:8080",
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            }
        }
    },
    build:{
        chunkSizeWarningLimit:1500,
        minify: false,
    },
    base:'./'

})
