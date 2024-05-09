<script>
import {onMounted, reactive, toRefs} from "vue";
import {register} from "@/api/user.js";
import {ElMessage} from "element-plus";
import {setUid, setUserName} from "@/until/auth.js";
import router from "@/router/index.js";
import {getImgVerifyCode} from "@/api/resource.js";

export default {
  name: "register",
  setup() {
    const state = reactive({
      userName: "",
      password: "",
      imgVerifyCode: "",
      valCode: ""
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
    onMounted(async () => {
      loadImgVerifyCode();
    });
    const loadImgVerifyCode = async () => {
      const {data} = await getImgVerifyCode();
      state.imgVerifyCode = "data:image/jpeg;base64," + data.img;
      state.sessionId = data.sessionId;
    };

    return {
      ...toRefs(state),
      registerUser,
      loadImgVerifyCode
    }
  }
}
</script>

<template>
  <div class="main box_center">
    <form method="post" action="./register.html" id="form2">
      <h3 class="register-title">账号注册</h3>
      <ul class="log_list">
        <li><span id="LabErr" class="error-message"></span></li>
        <li>
          <label for="txtUName" class="input-label">用户名</label>
          <input
              v-model="userName"
              name="txtUName"
              type="text"
              id="txtUName"
              class="s_input"
              placeholder="请输入您的用户名"
          />
        </li>
        <li>
          <label for="txtPassword" class="input-label">密码</label>
          <input
              v-model="password"
              name="txtPassword"
              type="password"
              id="txtPassword"
              class="s_input"
              placeholder="请输入密码：6-20位字母/数字"
          />
        </li>
        <li>
          <label for="txtCaptcha" class="input-label">验证码</label>
          <input
              v-model="valCode"
              name="txtCaptcha"
              type="text"
              id="txtCaptcha"
              class="s_input"
              placeholder="请输入验证码"
          />
          <img
              style="cursor: pointer"
              class="code_pic"
              :src="imgVerifyCode"
              id="chkd"
              @click="loadImgVerifyCode"
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
    <div class="login-link">
      <p>已有账号？</p>
      <router-link :to="{ name: 'login' }" class="link">立即登录</router-link>
    </div>
  </div>
</template>


<style scoped>
.main {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.register-title {
  text-align: center;
  margin-bottom: 20px;
}

.log_list {
  list-style: none;
  padding: 0;
}

.log_list li {
  margin-bottom: 15px;
}

.input-label {
  display: block;
  margin-bottom: 5px;
}

.s_input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.btn_red {
  width: 100%;
  padding: 10px;
  background-color: #ff5733;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn_red:hover {
  background-color: #ff441f;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-bottom: 5px;
}

.login-link {
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