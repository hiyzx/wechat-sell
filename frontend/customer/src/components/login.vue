<template>
  <div id="login">
    <input placeholder="手机号" v-model="phone"><br/>
    <input type="password" placeholder="密码" v-model="password"><br/>
    <input type="button" @click="login" value="登录"><br/>
    <input type="button" @click="logout" value="退出">
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
          {emulateJSON:true}).then(
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
