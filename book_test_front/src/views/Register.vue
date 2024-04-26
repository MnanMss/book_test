<script>
import {reactive, toRefs} from "vue";
import {ElMessage} from "element-plus";
import {setUid, setUserName} from "@/until/auth.js";
import router from "@/router/index.js";

export default {
  name: "register",
  setup() {
    const state = reactive({
      userName: "",
      password: ""
    })

    const registerUser = async () => {
      if (!state.userName) {
        ElMessage.error("用户名不能为空！");
        return;
      }
      if (!state.password) {
        ElMessage.error("密码不能为空！");
        return;
      }
      const {data} = await register(state);

      setUid(data.uid);
      setUserName(state.userName);
      router.push({path: "/login"});
    };

    return {
      ...toRefs(state),
      registerUser
    }
  }
}
</script>

<template>
  <div class="main box_center">
    <form method="post" action="./register.html" id="form2">
      <h3>账号注册</h3>
      <ul class="log_list">
        <li><span id="LabErr"></span></li>
        <li>
          <input
              v-model="userName"
              name="txtUName"
              type="text"
              id="txtUName"
              class="s_input icon_name"
              placeholder="请输入您的用户名"
          />
        </li>
        <li>
          <input
              v-model="password"
              name="txtPassword"
              type="password"
              id="txtPassword"
              class="s_input icon_key"
              placeholder="请输入密码：6-20位字母/数字"
          />
        </li>
        <li>
          <input
              type="button"
              name="btnRegister"
              value="注册"
              id="btnRegister"
              class="btn_red"
              @click="registerUser"
          />
        </li>
      </ul>
    </form>
    <div>
      <p>已有账号？</p>
      <router-link :to="{ name: 'login' }"
      >立即登录
      </router-link
      >
    </div>
  </div>
</template>

<style scoped>

</style>