<template>
  <div v-if="isAuthRoute" class="auth-container">
    <router-view />
  </div>
  <el-container v-else class="layout-container">
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <div class="sidebar-header">
        <span v-if="!isCollapse">12306</span>
      </div>
      <el-menu
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        router
      >
        <el-menu-item index="/query">
          <el-icon><Search /></el-icon>
          <template #title>车票查询</template>
        </el-menu-item>
        <el-menu-item index="/orders">
          <el-icon><Tickets /></el-icon>
          <template #title>订单查询</template>
        </el-menu-item>
        <el-menu-item index="/info">
          <el-icon><User /></el-icon>
          <template #title>用户信息</template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header>
        <div class="header-content">
          <div class="left-panel">
            <el-icon @click="toggleSidebar" class="collapse-icon">
              <Expand v-if="isCollapse" />
              <Fold v-else />
            </el-icon>
            <span>欢迎使用 12306 购票平台</span>
          </div>
          <div class="right-panel">
            <div v-if="currentUser" class="user-avatar">
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  <el-avatar :icon="UserFilled" />
                  <span class="username">{{ currentUser.username }}</span>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
            <div v-else>
              <el-button type="primary" @click="loginDialogVisible = true">登录</el-button>
              <el-button @click="registerDialogVisible = true">注册</el-button>
            </div>
          </div>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>

  <el-dialog v-model="loginDialogVisible" title="用户登录" width="400px" :close-on-click-modal="false">
    <Login @login-success="handleLoginSuccess" />
  </el-dialog>

  <el-dialog v-model="registerDialogVisible" title="用户注册" width="400px" :close-on-click-modal="false">
    <Register @register-success="handleRegisterSuccess" />
  </el-dialog>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {
  Search,
  User,
  Expand,
  Fold,
  UserFilled,
  Tickets
} from '@element-plus/icons-vue';
import Login from './components/Login.vue';
import Register from './components/Register.vue';
import apiClient from './api';

const isCollapse = ref(false);
const route = useRoute();
const router = useRouter();
const loginDialogVisible = ref(false);
const registerDialogVisible = ref(false);
const currentUser = ref(null);

// 不再需要独立布局
const isAuthRoute = computed(() => false);

const toggleSidebar = () => {
  isCollapse.value = !isCollapse.value;
};

const handleLoginSuccess = async () => {
  loginDialogVisible.value = false;
  await fetchUserInfo();
};

const handleRegisterSuccess = () => {
  registerDialogVisible.value = false;
  alert('注册成功，请登录！');
  loginDialogVisible.value = true;
};

const fetchUserInfo = async () => {
  const token = localStorage.getItem('jwt-token');
  if (token) {
    try {
      const response = await apiClient.get('/user-service/info');
      currentUser.value = response.data;
    } catch (error) {
      console.error("获取用户信息失败:", error);
      // token 无效或过期，清除它
      localStorage.removeItem('jwt-token');
    }
  }
};

const handleCommand = (command) => {
  if (command === 'logout') {
    localStorage.removeItem('jwt-token');
    currentUser.value = null;
    router.push('/query'); // 退出后跳转到查询页
  }
};

// 组件挂载时，尝试获取用户信息
onMounted(() => {
  fetchUserInfo();
});
</script>

<style>
html, body, #app {
  height: 100%;
  margin: 0;
  padding: 0;
}

.layout-container, .auth-container {
  height: 100%;
}

.el-aside {
  background-color: #545c64;
  transition: width 0.3s;
}

.sidebar-header {
  color: #fff;
  text-align: center;
  padding: 20px;
  font-size: 18px;
  white-space: nowrap;
}

.el-menu {
  border-right: none;
  background-color: #545c64;
}

.el-menu-item, .el-sub-menu__title {
  color: #fff !important;
}

.el-menu-item:hover, .el-sub-menu__title:hover {
  background-color: #434a50 !important;
}

.el-menu-item.is-active {
  color: #ffd04b !important;
  background-color: #434a50 !important;
}

.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
}

.left-panel, .right-panel {
  display: flex;
  align-items: center;
}

.right-panel {
  height: 100%;
}

.collapse-icon {
  font-size: 24px;
  cursor: pointer;
  margin-right: 20px;
}

.el-main {
  background-color: #e9eef3;
  padding: 20px;
}

.user-avatar {
  display: flex;
  align-items: center;
  height: 100%;
}

.user-avatar .el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
  outline: none; /* 移除焦点时的黑边 */
}

.user-avatar .username {
  margin-left: 10px;
  color: #333;
}
</style>
