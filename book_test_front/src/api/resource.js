import request from '../until/request.js'

export function getImgVerifyCode() {
    return request.get('/resource/img_verify_code');
}
