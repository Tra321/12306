import axios from 'axios';

// 创建 axios 实例
const apiClient = axios.create({
  baseURL: '/api', // 基础 URL，所有请求都会带上 /api 前缀
  timeout: 10000, // 请求超时时间
});

// 请求拦截器
apiClient.interceptors.request.use(
  (config) => {
    // 从 localStorage 获取 token
    const token = localStorage.getItem('jwt-token');
    if (token) {
      // 如果 token 存在，则添加到请求头中
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 响应拦截器
apiClient.interceptors.response.use(
  (response) => {
    // 对响应数据做点什么
    return response;
  },
  (error) => {
    // 对响应错误做点什么
    if (error.response && error.response.status === 401) {
      // 如果是 401 Unauthorized 错误（通常是 token 过期或无效）
      // 清除本地存储的 token
      localStorage.removeItem('jwt-token');
      // 跳转到登录页面
      // 注意：直接使用 window.location 会刷新页面，体验更好的是使用 router
      // 但在 axios 拦截器中注入 router 比较复杂，这里为了简化先用此方法
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default apiClient;
