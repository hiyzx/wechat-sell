import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/user/login'
import code from '@/components/user/code'
import register from '@/components/user/register'
import forgetPassword from '@/components/user/forgetPassword'
import personal from '@/components/user/personal'
import myOrder from '@/components/order/myOrder'
import index from '@/components/index'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
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
    },
    {
      path: '/forgetPassword',
      name: 'forgetPassword',
      component: forgetPassword
    },
    {
      path: '/personal',
      name: 'personal',
      component: personal
    },
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/myOrder',
      name: 'myOrder',
      component: myOrder
    }
  ]
})
