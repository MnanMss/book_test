const TokenKey = 'Authorization'

const userNameKey = 'userName'

const uidKey = 'uid'

export const setUserName = (name) => {
    return localStorage.setItem(userNameKey, name)
}

export const getUserName = () => {
    return localStorage.getItem(userNameKey)
}

export const setUid = (uid) => {
    return localStorage.setItem(uidKey, uid)
}

export const getUId = () => {
    return localStorage.getItem(uidKey)
}

export const setToken = (token) => {
    return localStorage.setItem(TokenKey, token)
}

export const getToken = () => {
    return localStorage.getItem(TokenKey)
}

export const removeToken = () => {
    return localStorage.removeItem(TokenKey)
}

export const removeUserName = () => {
    return localStorage.removeItem(userNameKey)
}

export const removeUid = () => {
    return localStorage.removeItem(uidKey)
}