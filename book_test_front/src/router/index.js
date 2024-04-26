import {createRouter, createWebHashHistory} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/views/Login.vue'),
        },
        {
            path: '/register',
            name: 'register',
            component: () => import('@/views/Register.vue'),
        },
        {
            path: '/table',
            name: 'table',
            component: () => import('@/views/Table.vue'),
        }
    ]
})

// 解决 vue 中路由跳转时，总是从新页面中间开始显示
router.afterEach((to, from) => {
    window.scrollTo(0, 0);
});
export default router