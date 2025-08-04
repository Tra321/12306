import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      // 代理 user-service
      '/api/user-service': {
        target: 'http://localhost:8001',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/user-service/, '/api/user-service')
      },
      // 代理 ticket-service
      '/api/ticket-service': {
        target: 'http://localhost:8002',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/ticket-service/, '/api/ticket-service')
      }
    }
  }
})
