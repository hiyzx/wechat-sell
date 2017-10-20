<template>
  <div id="code">
    <mt-header fixed title="个人中心" style="font-size: 24px;">
      <router-link to="/" slot="right">
        <mt-button @click="logout" style="font-size: 18px">退出</mt-button>
      </router-link>
    </mt-header>
    <mt-button size="small" type="primary" style="margin-left: 200px" @click="check">签到</mt-button>
    <mt-field label="姓名" v-model="name" style="margin-top: 100px"></mt-field>
    <mt-field label="手机号" v-model="phone" readonly="readonly"></mt-field>
    <mt-field label="年龄" v-model="age"></mt-field>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {Header} from 'mint-ui';

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));
  export default {
    name: 'code',
    data() {
      return {
        name: userInfo.name,
        phone: userInfo.phone,
        age: userInfo.age,
        events: [{
          date: '2017/10/15',
          title: 'eat',
          desc: 'longlonglong description'
        }]
      }
    },
    methods: {
      async check() {
        const self = this
        const res = await this.$httpPost("/user/check.json", {
          sessionId: localStorage.getItem('sessionId')
        }, {credentials: true, emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '签到成功',
            position: 'middle',
            duration: 2000
          });
        } else {
          if(res.resCode === '403'){
            Toast({
              message: '未登录',
              position: 'middle',
              duration: 1000
            });
            window.setTimeout(function () {
              self.$router.push({path: '/'})
            }, 1000)
          }else {
            Toast({
              message: res.resDes,
              position: 'middle',
              duration: 2000
            });
          }
        }
      },
      async logout() {
        const self = this;
        const res = await this.$httpPost("/auth/logout.json", {
          sessionId: localStorage.getItem('sessionId')
        }, {credentials: true, emulateJSON: true});

        if (res.resCode === '000000') {
          Toast({
            message: '退出成功',
            position: 'middle',
            duration: 2000
          });
        } else {
          Toast({
            message: res.resDes,
            position: 'middle',
            duration: 2000
          });
        }
      }
    }
  }
</script>
