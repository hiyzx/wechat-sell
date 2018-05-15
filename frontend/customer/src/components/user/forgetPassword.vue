<template>
  <div id="code">
    <mt-header fixed title="重置密码" style="font-size: 24px;">
      <router-link to="/" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="phone" readonly="readonly"></mt-field>
    <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password1"></mt-field>
    <mt-field label="密码" placeholder="请确认密码" type="password" v-model="password2"></mt-field>
    <mt-button @click.native="restPassword" size="large" type="primary" style="margin-top: 200px;">重置密码</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {MessageBox} from 'mint-ui';
  import md5 from 'js-md5';

  export default {
    name: 'code',
    data() {
      return {
        phone: this.$route.query.phone,
        password1: '',
        password2: ''
      }
    },
    methods: {
      async restPassword() {
        if (this.phone === '' || this.phone === undefined) {
          Toast({
            message: '手机号不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if (this.password1 === '' || this.password1 === undefined) {
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if (this.password2 === '' || this.password2 === undefined) {
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const res = await this.$httpPost('/auth/restPassword.json', {
          phone: this.phone,
          password1: this.password1,
          password2: this.password2,
          timestamp : timestamp,
          authorization : authorization
        }, {emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '重置成功',
            position: 'middle',
            duration: 1000
          });
          window.setTimeout(function () {
            self.$router.push({path: '/login', query: {phone: this.phone}})
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
