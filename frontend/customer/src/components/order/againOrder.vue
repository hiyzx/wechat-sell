<template>
  <div>
    <mt-header fixed title="下单详情" style="font-size: 22px;"></mt-header>

    <mt-field label="姓名" placeholder="请输入姓名" v-model="orderVo.buyerName"></mt-field>
    <br/>
    <mt-field label="手机号" placeholder="请输入手机号" v-model="orderVo.buyerPhone"></mt-field>
    <br/>
    <mt-field label="地址" placeholder="请输入地址" v-model="orderVo.buyerAddress"></mt-field>

    <div v-for="detail in orderVo.orderDetailVos">
      {{detail.productName}} *
      <label>
        <input type="number" min="0" v-model="detail.productQuantity">
      </label>
    </div>

    <mt-field label="总金额" placeholder="0" v-model="orderVo.totalAmount"></mt-field>

    <mt-button @click.native="order" size="large" type="primary" style="margin-top: 100px">确定</mt-button>
  </div>
</template>
<script>
  import pageBottom from './../common/bottom'
  import {Toast} from 'mint-ui';
  import {Loadmore} from 'mint-ui';
  import md5 from 'js-md5';

  export default {
    components: {pageBottom},
    data() {
      return {
        sessionId: '',
        orderId: '',
        orderVo: {},
        orderDto: {}
      };
    },
    mounted() {
      this.sessionId = localStorage.getItem('sessionId');
      this.orderId = this.$route.query.orderId;
      this.getOrder();
    },
    methods: {
      async getOrder() {
        const self = this;
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const res = await this.$httpGet('/order/get.json', {
            sessionId: this.sessionId,
            orderId: this.orderId,
            timestamp : timestamp,
            authorization : authorization
          }, {emulateJSON: true});
        if (res.resCode === '000000') {
          self.orderVo = res.data;
        }
        if (res.resCode === '403') {
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
      async order() {
        const self = this;
        const details = this.orderVo.orderDetailVos;
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        let detailDtos = [];
        for (let i = 0; i < details.length; i++) {
          let detail = details[i];
          let detailDto = {};
          detailDto.productId = detail.productId;
          detailDto.count = detail.productQuantity;
          detailDtos.push(detailDto);
        }
        console.log(this.orderDto)
        const res = await this.$httpPost('/order/add.json?sessionId=' + this.sessionId, {
          buyerName: this.orderVo.buyerName,
          buyerPhone: this.orderVo.buyerPhone,
          buyerAddress: this.orderVo.buyerAddress,
          timestamp : timestamp,
          authorization : authorization,
          orderDetailDtos: detailDtos
        }, {credentials: true});
        if (res.resCode === '000000') {
          self.$router.push({path: '/payment', query: {orderId: res.data}})
        } else if (res.resCode === '403') {
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
