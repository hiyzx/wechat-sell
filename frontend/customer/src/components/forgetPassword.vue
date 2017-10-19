<template>
  <div id="code">
    <h2>重置密码</h2>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="phone" readonly="readonly"></mt-field>
    <br/>
    <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password1"></mt-field>
    <br/>
    <mt-field label="密码" placeholder="请确认密码" type="password" v-model="password2"></mt-field>
    <br/>
    <mt-button @click.native="restPassword">重置密码</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {MessageBox} from 'mint-ui';

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
        if(this.phone === '' || this.phone === undefined){
          Toast({
            message: '手机号不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if(this.password1 === '' || this.password1 === undefined){
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if(this.password2 === '' || this.password2 === undefined){
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        const res = await this.$httpPost('/auth/restPassword.json', {
          phone: this.phone,
          password1: this.password1,
          password2: this.password2
        }, {emulateJSON: true});
        if (res.resCode === '000000') {
          MessageBox.alert('重置成功').then(action => {
            this.$router.push({path: '/', query: {phone: this.phone}})
          });
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
