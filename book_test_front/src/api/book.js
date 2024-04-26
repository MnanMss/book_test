import request from '../until/request.js'

export function searchBook(params) {
    return request.post('/book/page', params)
}

export function borrowBook(params) {
    return request.post('/book/borrow', params)
}

export function returnBook(params) {
    return request.post('/book/returnBook', params)
}

