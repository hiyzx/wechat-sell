<template>
  <div id="login">
      <h2>用户登录</h2>
    <input placeholder="手机号" v-model="phone"><br/><br/>
    <input type="password" placeholder="密码" v-model="password"><br/><br/>
    <input type="button" @click="login" value="登录"><br/>
  </div>
</template>

<script>
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
              localStorage.setItem("sessionId",resp.data.sessionId)
              alert("登录成功");
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
