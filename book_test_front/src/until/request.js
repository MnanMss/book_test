import axios from 'axios'
import {ElMessage} from 'element-plus'

axios.defaults.baseURL = "http://127.0.0.1:8080/api"
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.response.use(res => {
    if (typeof res.data !== 'object') {
        ElMessage.error('服务端异常！')
        return Promise.reject(res)
    }
    if (res.data.code !== "1") {
        if (res.data.message) {
            ElMessage.error(res.data.message)
            return;
        }
    } else if (res.data.code === "1") {
        ElMessage.success("操作成功")
    }
    return res.data
}, error => {
    ElMessage.error('网络异常！')
    console.log(error)
    Promise.reject(error)
})

export default axios