<template>
  <div class="register-container">
    <h2>用户注册</h2>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="formData.username" required />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="formData.password" required />
      </div>
      <button type="submit">注册</button>
    </form>
    <p v-if="message" :class="['message', messageType]">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import apiClient from '../api'; // 导入我们配置好的 apiClient

const emit = defineEmits(['registerSuccess']); // defineEmits 是编译器宏，无需导入
// 使用 ref 创建响应式数据
const formData = ref({
  username: '',
  password: ''
});

const message = ref('');
const messageType = ref('error'); // 'success' or 'error'

// 注册处理函数
const handleRegister = async () => {
  message.value = ''; // 清空旧消息
  try {
    // 使用 apiClient 发送请求，注意 URL 中不再需要 /api 前缀
    await apiClient.post('/user-service/register', formData.value);
    
    // 触发注册成功事件
    emit('registerSuccess');

  } catch (error) {
    console.error('注册失败:', error);
    messageType.value = 'error';
    if (error.response && error.response.status === 409) {
      // 如果是 409 Conflict 错误，说明用户名已存在
      // error.response.data 就是后端返回的错误信息字符串
      message.value = error.response.data;
    } else {
      // 其他错误，显示通用信息
      message.value = '注册失败，请稍后再试。';
    }
  }
};
</script>

<style scoped>
.register-container {
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
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
button:hover {
  background-color: #0056b3;
}
.message {
  margin-top: 15px;
}
.success {
  color: green;
}
.error {
  color: red;
}
</style>
