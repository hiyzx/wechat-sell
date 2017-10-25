<template>
  <div id="login">
    <mt-header fixed title="用户登录" style="font-size: 22px;"></mt-header>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="phone" style="margin-top: 90px;">
    </mt-field>
    <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password">
    </mt-field>
    <br/>

    <mt-button @click.native="forgetPassword" size="small" style="left: 120px" type="default">忘记密码</mt-button>
    <br/><br/>
    <mt-button @click.native="login" size="large" type="primary" style="margin-top: 140px;">登录</mt-button>
    <br/>
    <mt-button @click.native="toRegister" size="large" type="danger">注册</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {Header} from 'mint-ui';

  export default {
    name: 'login',
    data() {
      return {
        phone: this.$route.query.phone,
        password: ''
      }
    },
    methods: {
      toRegister() {
        this.$router.push({path: '/code', query: {type: 1}});
      },
      forgetPassword() {
        this.$router.push({path: '/code', query: {type: 2}});
      },
      async login() {
        const self = this;
        if (this.phone === '' || this.phone === undefined) {
          Toast({
            message: '手机号不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if (this.password === '' || this.password === undefined) {
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        const res = await this.$httpPost('/auth/login.json', {
          phone: this.phone,
          password: this.password
        }, {emulateJSON: true});
        if (res.resCode === '000000') {
          localStorage.setItem("sessionId", res.data.cookieValue);
          localStorage.setItem("userInfo", JSON.stringify(res.data.user))
          Toast({
            message: '登录成功',
            position: 'middle',
            duration: 1000
          });
          window.setTimeout(function () {
            self.$router.push({path: 'personal'})
          }, 1000)
        } else {
          Toast({
            message: res.resDes,
            position: 'middle',
            duration: 1000
          });
        }
      }
    }
  }
</script>
