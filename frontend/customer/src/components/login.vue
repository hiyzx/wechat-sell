<template>
  <div id="login">
      <h2>用户登录</h2>
      <mt-field label="手机号" placeholder="请输入手机号" v-model="phone"></mt-field>
      <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>
      <mt-button @click.native="login">登录</mt-button>
  </div>
</template>

<script>
    import { Toast } from 'mint-ui';
    import { Field } from 'mint-ui';

  export default {
    name: 'login',
    data() {
      return {
        phone: '',
        password: ''
      }
    },
    methods: {
      login:function () {
        this.$http.post("http://localhost:8083/customer/auth/login.json",
          {phone:this.phone,password:this.password},
          {emulateJSON:true,  credentials: true }).then(
          function (res) {
            var resp = res.body;
            if(resp.resCode === '000000'){
              localStorage.setItem("sessionId",resp.data.sessionId);
                Toast({
                    message: '登录成功',
                    position: 'middle',
                    duration: 5000
                });
            }else {
              alert(resp.resDes)
            }
          }
        );
      },
      logout:function () {
        this.$http.post("http://localhost:8083/customer/auth/logout.json",
          {sessionId:localStorage.getItem("sessionId")},
          {emulateJSON:true}).then(
          function (res) {
            var resp = res.body;
            localStorage.clear();
            alert("退出成功");
          }
        );
      }
    }
  }
</script>
