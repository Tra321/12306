<template>
  <div class="user-info-container">
    <h2>用户信息</h2>
    <div v-if="userInfo" class="user-details">
      <p><strong>ID:</strong> {{ userInfo.id }}</p>
      <p><strong>用户名:</strong> {{ userInfo.username }}</p>
      <p><strong>真实姓名:</strong> {{ userInfo.realName }}</p>
      <p><strong>手机号:</strong> {{ userInfo.phone }}</p>
      <p><strong>邮箱:</strong> {{ userInfo.mail }}</p>
    </div>
    <p v-if="error" class="message error">{{ error }}</p>
    <button @click="logout">退出登录</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import apiClient from '../api';
import { useRouter } from 'vue-router';

const userInfo = ref(null);
const error = ref('');
const router = useRouter();

// onMounted 是一个生命周期钩子，在组件挂载后执行
onMounted(async () => {
  try {
    const response = await apiClient.get('/user-service/info');
    userInfo.value = response.data;
  } catch (err) {
    console.error('获取用户信息失败:', err);
    error.value = '获取用户信息失败，请检查您是否已登录。';
  }
});

const logout = () => {
  // 清除 token
  localStorage.removeItem('jwt-token');
  // 跳转到登录页
  router.push('/login');
};
</script>

<style scoped>
.user-info-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: left;
}
.user-details p {
  margin: 10px 0;
}
.message {
  margin-top: 15px;
}
.error {
  color: red;
}
button {
  margin-top: 20px;
  padding: 10px 15px;
  background-color: #dc3545;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #c82333;
}
</style>
