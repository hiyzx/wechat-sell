import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import code from '@/components/code'
import register from '@/components/register'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'login',
            component: login
        },
        {
            path: '/code',
            name: 'code',
            component: code
        },
        {
            path: '/register',
            name: 'register',
            component: register
        }
    ]
})