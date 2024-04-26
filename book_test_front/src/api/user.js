import request from '../until/request.js'

export function register(params) {
    return request.post("/user/register", params);
}

export function login(params) {
    return request.post("/user/login", params);
}