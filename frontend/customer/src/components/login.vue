<template>
  <div id="login">
    <h2>用户登录</h2>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="phone"></mt-field>
    <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>
    <br/>
    <mt-button @click.native="forgetPassword" style="right: 60px">忘记密码</mt-button>
    <mt-button @click.native="login">登录</mt-button>
    <mt-button @click.native="toRegister" style="left:60px;">注册</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';

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
        const res = await this.$httpPost('/auth/login.json', {
          phone: this.phone,
          password: this.password
        }, {emulateJSON: true});
        if (res.resCode === '000000') {
          localStorage.setItem("sessionId", res.data.sessionId);
          Toast({
            message: '登录成功',
            position: 'middle',
            duration: 1000
          });
        } else {
          Toast({
            message: res.resDes,
            position: 'middle',
            duration: 1000
          });
        }
      }
      /*logout: function () {
        this.$http.post("http://localhost:8083/customer/auth/logout.json",
          {sessionId: localStorage.getItem("sessionId")},
          {emulateJSON: true}).then(
          function (res) {
            var resp = res.body;
            localStorage.clear();
            Toast({
              message: resp.resDes,
              position: 'middle',
              duration: 5000
            });
          }
        );
      }*/
    }
  }
</script>
