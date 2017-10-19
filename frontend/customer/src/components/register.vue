<template>
  <div id="register">
    <mt-header fixed title="用户注册" style="font-size: 24px;">
      <router-link to="/" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header>
    <mt-field label="年龄" placeholder="请输入年龄" v-model="age" type="number" min="18"></mt-field>
    <br/>
    <mt-field label="姓名" placeholder="请输入姓名" v-model="username"></mt-field><br/>
    <mt-field label="密码" placeholder="请输入密码" v-model="password"></mt-field>
    <mt-button @click.native="register" size="large" type="primary" style="margin-top: 200px;">注册</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';

  export default {
    name: 'register',
    data() {
      return {
        age: '',
        username: '',
        password: '',
        phone: this.$route.query.phone
      }
    },
    methods: {
      async register() {
        if(this.age < 18){
          Toast({
            message: '年龄不能小于18',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if(this.username === '' || this.username === undefined){
          Toast({
            message: '名字不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if(this.password === undefined || this.password.length < 6){
          Toast({
            message: '密码不能小于6位',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        const res = await this.$httpPost('/auth/register.json', {
          age: this.age,
          phone: this.phone,
          name: this.username,
          password: this.password
        }, {credentials: true});
        if (resp.resCode === '000000') {
          Toast({
            message: '注册成功!',
            position: 'middle',
            duration: 2000
          });
        } else {
          Toast({
            message: resp.resDes,
            position: 'middle',
            duration: 2000
          });
        }
      }
    }
  }
</script>
