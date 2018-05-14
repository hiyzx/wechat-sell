<template>
  <div class="mains">
    <div style="overflow: hidden;margin-bottom: 10px;display: flex;">
      <div class="product_img">
        <img :src="store.avatar"/>
      </div>
      <div class="product_main">
        <div>
          <div class="brand">品牌</div>
          <div>{{store.name}}</div>
        </div>
        <div>
          {{store.deliveryStr}}
        </div>
        <div>
          <div class="reduce">减</div>
          <div>在线支付满28减5</div>
        </div>
      </div>
    </div>
    <div class="notice">
      <div class="notice_title">公告</div>
      <div class="notice_content">{{store.shortIntro}}</div>
      <div class="notice_back"></div>
    </div>
  </div>
</template>
<script>
  import md5 from 'js-md5';

  export default {
    name: 'page-header',
    mounted() {
      this.getStoreInfo();
    },
    data() {
      return {
        store: ''
      };
    },
    methods: {
      async getStoreInfo() {
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const res = await this.$httpGet("/store/info.json", {
          timestamp : timestamp,
          authorization : authorization
        }, {credentials: true, emulateJSON: true});
        this.store = res.data;
      }
    }
  };
</script>
<style type="text/css">
  * {
    margin: 0;
    padding: 0;
  }

  .mains {
    padding-top: 10px;
    background: gray;
    font-size: 14px;
    position: relative;
  }

  .product_main {
    text-align: left;
    padding-left: 16px;
    box-sizing: border-box;
    color: #fff;
    float: left;
    width: 80%;
  }

  .product_img {
    margin-left: 15px;
    width: 60px;
    float: left;
  }

  .product_img img {
    width: 100%;
    vertical-align: top;
  }

  .reduce {
    float: left;
    background: #fff;
    color: red;
    margin-right: 10px;
    line-height: 16px;
  }

  .brand {
    float: left;
    color: #fff;
    background: red;
    margin-right: 10px;
    font-size: 12px;
  }

  .notice {
    position: relative;
    padding-left: 16px;
    text-align: left;
    padding-bottom: 5px;
  }

  .notice div {
    font-size: 12px;
    display: inline-block;
    color: #fff;
  }

  .notice .notice_title {
    margin-left: 20px;
    color: #000;
    background: #fff;
  }

  .notice .notice_content {
    margin-left: 10px;
  }

  .notice_back {
    position: absolute;
    left: 0;
    width: 100%;
    height: 100%;
    background: #000;
    opacity: 0.5;
    z-index: -1000;
  }
</style>
