<template>
  <div>
    <h2>我的订单</h2>
    <el-table :data="orders" style="width: 100%">
      <el-table-column prop="orderId" label="订单号"></el-table-column>
      <el-table-column prop="trainNumber" label="车次"></el-table-column>
      <el-table-column prop="departure" label="出发地"></el-table-column>
      <el-table-column prop="destination" label="目的地"></el-table-column>
      <el-table-column prop="departureTime" label="出发时间"></el-table-column>
      <el-table-column prop="passengerName" label="乘客姓名"></el-table-column>
      <el-table-column prop="seatInfo" label="座位信息"></el-table-column>
      <el-table-column prop="status" label="订单状态"></el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import apiClient from '../api';

const orders = ref([]);

const fetchOrders = async () => {
  try {
    const response = await apiClient.get('/order-service/orders');
    orders.value = response.data;
  } catch (error) {
    console.error("获取订单列表失败:", error);
  }
};

onMounted(() => {
  fetchOrders();
});
</script>

<style scoped>
h2 {
  margin-bottom: 20px;
}
</style>
