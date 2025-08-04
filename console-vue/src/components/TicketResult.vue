<template>
  <div class="result-container" v-if="tickets.length > 0">
    <h3>查询结果</h3>
    <table>
      <thead>
        <tr>
          <th>车次</th>
          <th>出发站</th>
          <th>到达站</th>
          <th>出发时间</th>
          <th>到达时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="ticket in tickets" :key="ticket.id">
          <td>{{ ticket.trainNumber }}</td>
          <td>{{ ticket.startStation }}</td>
          <td>{{ ticket.endStation }}</td>
          <td>{{ ticket.departureTime }}</td>
          <td>{{ ticket.arrivalTime }}</td>
          <td><button>预订</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
// 在 <script setup> 中，defineProps 是一个编译器宏，无需导入
// 使用 defineProps 接收父组件传递过来的 tickets 数组
defineProps({
  tickets: {
    type: Array,
    required: true
  }
});
</script>

<style scoped>
.result-container {
  margin-top: 20px;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}
th {
  background-color: #f2f2f2;
}
</style>
