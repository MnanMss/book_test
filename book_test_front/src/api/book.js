import request from '../until/request.js'

export function searchBook(params) {
    return request.post('/front/book/page', params)
}

export function borrowBook(params) {
    return request.post('/front/book/borrow', params)
}

export function returnBook(params) {
    return request.post('/front/book/returnBook', params)
}

