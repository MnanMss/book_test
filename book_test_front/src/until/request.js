import axios from 'axios'
import {ElMessage} from 'element-plus'
import {getToken, removeToken, removeUid, removeUserName} from "@/until/auth.js";
import router from "@/router/index.js";

axios.defaults.baseURL = "http://127.0.0.1:8080/api"
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.request.use(config => {
    config.headers['Authorization'] = getToken()
    return config
}, error => {
    console.log(error)
    Promise.reject(error)
})

axios.interceptors.response.use(res => {
    if (typeof res.data !== 'object') {
        ElMessage.error('服务端异常！')
        return Promise.reject(res)
    }
    if (res.data.code !== "00000") {
        if (res.data.message) {
            ElMessage.error(res.data.message)
            return;
        }
        // 登录已过期
        if (res.data.code == 'A0230') {
            removeToken()
            removeUserName()
            removeUid()
            router.push({path: '/login'})
        }

        return Promise.reject(res.data)
    } else if (res.data.code === "00000") {
        ElMessage.success("操作成功")
    }
    return res.data
}, error => {
    ElMessage.error('网络异常！')
    console.log(error)
    Promise.reject(error)
})

export default axios