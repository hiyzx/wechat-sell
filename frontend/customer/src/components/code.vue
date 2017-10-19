<template>
  <div id="code">
    <h2>验证码</h2>
    <mt-field label="图形验证码" placeholder="请输入右图验证码" v-model="captcha">
      <img :src="imgCode" height="40px" width="70px" @click="reloadImg"></mt-field>
    <br/>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="phone">
      <mt-button @click.native="sendMsg">获取短信</mt-button>
    </mt-field>
    <br/>
    <mt-field label="手机验证码" placeholder="请输入手机验证码" v-model="code"></mt-field>
    <br/>
    <mt-button @click.native="verify">校验短信验证码</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';

  export default {
    name: 'code',
    data() {
      return {
        phone: '',
        captcha: '',
        code: '',
        imgCode: this.$captcha,
        type: this.$route.query.type
      }
    },
    methods: {
      reloadImg() {
        this.imgCode = this.$captcha + new Date() * 1
      },
      async sendMsg() {
        const res = await this.$httpPost("/auth/sendMsg.json", {
          userInputCaptcha: this.captcha,
          phone: this.phone,
          type: this.type
        }, {credentials: true, emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '发送成功',
            position: 'middle',
            duration: 2000
          });
        } else {
          Toast({
            message: res.resDes,
            position: 'middle',
            duration: 2000
          });
          this.reloadImg();
        }
      },
      async verify() {
        const res = await this.$httpPost("/auth/verify.json",
          {
            phone: this.phone,
            code: this.code,
            type: this.type
          }, {emulateJSON: true, credentials: true});
        if (res.resCode === '000000') {
          if (this.type === '1') {
            this.$router.push({path: '/register', query: {phone: this.phone}})
          } else {
            this.$router.push({path: '/forgetPassword', query: {phone: this.phone}})
          }
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
