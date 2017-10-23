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

    <mt-tabbar v-model="bottom_selected" fixed>
      <mt-tab-item id="外卖">
        <img slot="icon" src="../../static/img/store.png">
        外卖
      </mt-tab-item>
      <mt-tab-item id="订单">
        <img slot="icon" src="../../static/img/order.png">
        订单
      </mt-tab-item>
      <mt-tab-item id="我的">
        <img slot="icon" src="../../static/img/my.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {Field} from 'mint-ui';
  import {Header} from 'mint-ui';


  export default {
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
        const self = this;
        let notLogin = false;
        if (self.sessionId === '' || self.sessionId === undefined) {
          notLogin = true;
        } else {
          const res = await this.$httpPost("/auth/heartBeat.json", {
            sessionId: self.sessionId
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
        const self = this;
        const res = await this.$httpPost("/auth/logout.json", {
          sessionId: localStorage.getItem('sessionId')
        }, {credentials: true, emulateJSON: true});
        localStorage.clear();
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
