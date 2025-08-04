<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="formData.username" required />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="formData.password" required />
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="message" class="message error">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import apiClient from '../api'; // 导入我们配置好的 apiClient

const emit = defineEmits(['loginSuccess']); // defineEmits 是编译器宏，无需导入
const formData = ref({
  username: '',
  password: ''
});

const message = ref('');

const handleLogin = async () => {
  message.value = '';
  try {
    // 使用 apiClient 发送请求，注意 URL 中不再需要 /api 前缀
    const response = await apiClient.post('/user-service/login', formData.value);
    const token = response.data;
    
    // 登录成功，将 token 存储到 localStorage
    localStorage.setItem('jwt-token', token);
    
    // 触发登录成功事件
    emit('loginSuccess');
    
  } catch (error) {
    console.error('登录失败:', error);
    if (error.response && error.response.status === 401) {
      message.value = error.response.data; // "用户名或密码错误"
    } else {
      message.value = '登录失败，请稍后再试。';
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.form-group {
  margin-bottom: 15px;
}
label {
  display: block;
  margin-bottom: 5px;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #218838;
}
.message {
  margin-top: 15px;
}
.error {
  color: red;
}
</style>
