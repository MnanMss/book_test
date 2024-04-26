<script>
import {reactive, toRefs} from "vue";
import {ElMessage} from "element-plus";
import {login} from "@/api/user.js";
import {setUid, setUserName} from "@/until/auth.js";
import router from "@/router/index.js";

export default {
  name: "login",
  setup() {
    const state = reactive({
      userName: "",
      password: ""
    });
    const loginUser = async () => {
      if (!state.userName) {
        ElMessage.error("用户名不能为空！");
        return;
      }
      if (!state.password) {
        ElMessage.error("密码不能为空！");
        return;
      }
      const {data} = await login(state);
      setUid(data.uid)
      setUserName(data.userName)

      router.push({"path": "/table"})
    };
    return {
      ...toRefs(state),
      loginUser
    }
  }
}
</script>

<template>
  <div class="login-container">
    <form method="post" action="" id="form1">
      <h3 class="login-title">登录系统</h3>
      <ul class="login-form">
        <li>
          <label for="txtUName" class="label">用户名</label>
          <input
              v-model="userName"
              name="txtUName"
              type="text"
              id="txtUName"
              placeholder="用户名"
              class="input-field"
          />
        </li>
        <li>
          <label for="txtPassword" class="label">密码</label>
          <input
              v-model="password"
              name="txtPassword"
              type="password"
              id="txtPassword"
              placeholder="密码"
              class="input-field"
          />
        </li>
        <li>
          <input
              type="button"
              name="btnLogin"
              value="登录"
              id="btnLogin"
              @click="loginUser"
              class="login-button"
          />
        </li>
      </ul>
    </form>
  </div>
  <div class="register-link">
    <p>还没有注册账号？</p>
    <router-link :to="{name: 'register'}" class="link">立即注册</router-link>
  </div>
</template>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  list-style: none;
  padding: 0;
}

.login-form li {
  margin-bottom: 15px;
}

.label {
  display: block;
  margin-bottom: 5px;
}

.input-field {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.login-button:hover {
  background-color: #45a049;
}

.register-link {
  margin-top: 20px;
  text-align: center;
}

.link {
  color: #4CAF50;
  text-decoration: none;
  font-weight: bold;
}

.link:hover {
  text-decoration: underline;
}
</style>