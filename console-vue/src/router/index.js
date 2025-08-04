import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue';
import UserInfo from '../components/UserInfo.vue';
import TicketQuery from '../components/TicketQuery.vue';
import OrderList from '../components/OrderList.vue';

// 定义路由
const routes = [
  {
    path: '/',
    redirect: '/query' // 根路径重定向到查询页
  },
  {
    path: '/info',
    name: 'UserInfo',
    component: UserInfo
  },
  {
    path: '/query',
    name: 'TicketQuery',
    component: TicketQuery
  },
  {
    path: '/orders',
    name: 'OrderList',
    component: OrderList
  }
];

// 创建 router 实例
const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
