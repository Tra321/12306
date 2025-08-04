<template>
  <div class="query-container">
    <h2>车票查询</h2>
    <form @submit.prevent="handleQuery">
      <div class="form-group">
        <label for="fromStation">出发地:</label>
        <input type="text" id="fromStation" v-model="query.from" required />
      </div>
      <div class="form-group">
        <label for="toStation">目的地:</label>
        <input type="text" id="toStation" v-model="query.to" required />
      </div>
      <button type="submit">查询</button>
    </form>

    <div v-if="error" class="message error">{{ error }}</div>

    <TicketResult :tickets="tickets" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import apiClient from '../api';
import TicketResult from './TicketResult.vue';

const query = ref({
  from: '北京',
  to: '上海'
});
const tickets = ref([]);
const error = ref('');

const handleQuery = async () => {
  error.value = '';
  tickets.value = [];
  try {
    const response = await apiClient.get('/ticket-service/query', {
      params: query.value
    });
    tickets.value = response.data;
    if (tickets.value.length === 0) {
      error.value = '未查询到相关车次。';
    }
  } catch (err) {
    console.error('查询失败:', err);
    error.value = '查询失败，请稍后再试。';
  }
};
</script>

<style scoped>
.query-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.form-group {
  display: inline-block;
  margin: 0 10px;
}
.message {
  margin-top: 15px;
}
.error {
  color: red;
}
</style>
