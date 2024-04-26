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