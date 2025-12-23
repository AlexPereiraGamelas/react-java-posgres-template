import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'
import checker from 'vite-plugin-checker';
import path from 'path'

export default defineConfig({
    base: "/",
    plugins: [
        react(),
        checker({typescript: true})
    ],
    resolve: {
        alias: {
            "src": path.resolve(__dirname, 'src')
        }
    },
    server: {
        port: 5173,
        hmr: true,
        proxy: {
            '/api': {
                target: 'http://localhost:9090',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            },
        },
    },
})