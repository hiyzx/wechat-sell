<template>
  <div>
    <mt-header fixed title="支付" style="font-size: 22px;"></mt-header>

    <mt-button @click.native="payOrder" type="primary" style="margin-top: 140px;">支付</mt-button>
    <mt-button @click.native="cancelOrder" type="danger">取消</mt-button>
  </div>
</template>
<script>

  import {Toast} from 'mint-ui';

  export default {
    data() {
      return {
        sessionId: '',
        orderId: ''
      };
    },
    mounted() {
      this.sessionId = localStorage.getItem('sessionId');
      this.orderId = this.$route.query.orderId;
    },
    methods: {
      async payOrder() {
        const self = this;
        const res = await
          this.$httpPost('/order/pay.json', {
            sessionId: this.sessionId,
            orderId: this.orderId
          }, {emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '支付成功',
            position: 'middle',
            duration: 1000
          });
          window.setTimeout(function () {
            self.$router.push({path: '/'})
          }, 1000)
        }else if (res.resCode === '403') {
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
            duration: 1000
          });
        }
      },
      async cancelOrder() {
        const self = this;
        const res = await
          this.$httpPost('/order/cancel.json', {
            sessionId: this.sessionId,
            orderId: this.orderId
          }, {emulateJSON: true});
        if (res.resCode === '000000') {
          Toast({
            message: '取消成功',
            position: 'middle',
            duration: 1000
          });
          window.setTimeout(function () {
            self.$router.push({path: '/'})
          }, 1000)
        }else if (res.resCode === '403') {
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
            duration: 1000
          });
        }
      }
    }
  }
</script>
