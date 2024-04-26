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
  <div>
    <form method="post" action="" id="form1">
      <h3>登录系统</h3>
      <ul>
        <li>
          <input
              v-model="userName"
              name="txtUName"
              type="text"
              id="txtUName"
              placeholder="用户名"
          />
        </li>
        <li>
          <input
              v-model="password"
              name="txtPassword"
              type="password"
              id="txtPassword"
              placeholder="密码"
          />
        </li>
        <li>
          <input
              type="button"
              name="btnLogin"
              value="登录"
              id="btnLogin"
              @click="loginUser"
          />
        </li>
      </ul>
    </form>
  </div>
  <div>
    <p>还没有注册账号？</p>
    <router-link :to="{name: 'register'}">立即注册</router-link>
  </div>
</template>

<style scoped>

</style>