<template>
  <div id="personal">
    <mt-header fixed title="个人中心" style="font-size: 24px;">
      <router-link to="/" slot="right">
        <mt-button @click="logout" style="font-size: 18px">退出</mt-button>
      </router-link>
    </mt-header>
    <mt-button size="small" type="primary" style="margin-left: 200px" @click="check">签到</mt-button>
    <mt-field label="姓名" v-model="name"></mt-field>
    <mt-field label="手机号" v-model="phone" readonly="readonly"></mt-field>
    <mt-field label="年龄" v-model="age"></mt-field>

    <page-bottom></page-bottom>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {Header} from 'mint-ui';
  import pageBottom from './../common/bottom'
  import md5 from 'js-md5';


  export default {
    components: {pageBottom},
    name: 'personal',
    data() {
      return {
        name: '',
        phone: '',
        age: '',
        sessionId: '',
        bottom_selected: '我的'
      }
    },
      mounted(){
        this.sessionId = localStorage.getItem('sessionId');
        this.heartBeat();
        const userInfo = JSON.parse(localStorage.getItem('userInfo'));
        this.name = userInfo.name;
        this.phone = userInfo.phone;
        this.age = userInfo.age;
      },
    methods: {
      async heartBeat() {
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const self = this;
        let notLogin = false;
        if (self.sessionId === '' || self.sessionId === undefined) {
          notLogin = true;
        } else {
          const res = await this.$httpPost("/auth/heartBeat.json", {
            sessionId: self.sessionId,
            timestamp : timestamp,
            authorization : authorization
          }, {credentials: true, emulateJSON: true});
          if (res.resCode === '403') {
            notLogin = true;
          }
        }
        if (notLogin) {
          Toast({
            message: '未登录',
            position: 'middle',
            duration: 1000
          });
          window.setTimeout(function () {
            self.$router.push({path: '/login'})
          }, 1000)
        }
      },

      async check() {
        const self = this;
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const res = await this.$httpPost("/user/check.json", {
          sessionId: localStorage.getItem('sessionId'),
          timestamp : timestamp,
          authorization : authorization
        }, {credentials: true, emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '签到成功',
            position: 'middle',
            duration: 2000
          });
        } else {
          if (res.resCode === '403') {
            Toast({
              message: '未登录',
              position: 'middle',
              duration: 1000
            });
            window.setTimeout(function () {
              self.$router.push({path: '/login'})
            }, 1000)
          } else {
            Toast({
              message: res.resDes,
              position: 'middle',
              duration: 2000
            });
          }
        }
      },
      async logout() {
        localStorage.clear();
      }
    },
    watch: {
      bottom_selected: function () {
        if ('外卖' === this.bottom_selected) {
          this.$router.push({path: '/'});
        } else if ('订单' === this.bottom_selected) {
          this.$router.push({path: '/'});
        } else {
          this.$router.push({path: '/personal'})
        }
      }
    }
  }
</script>
