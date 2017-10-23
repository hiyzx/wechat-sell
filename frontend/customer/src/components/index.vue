<template>
  <div id="index">
    <mt-header fixed title="首页" style="font-size: 22px;"></mt-header>

    <div class="mains">
      <div style="overflow: hidden;margin-bottom: 10px;">
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

    <mt-navbar v-model="top_selected">
      <mt-tab-item id="1">商品</mt-tab-item>
      <mt-tab-item id="2">商家</mt-tab-item>
    </mt-navbar>

    <mt-tab-container v-model="top_selected" style="overflow: scroll;height: 600px">
      <mt-tab-container-item id="1">
        <div class="category_1">
          <div :class="{category:true, click:category.id == category_selected}" v-for="category in categories"
               @click="changeSelectCategory(category.id)">
            {{category.name}}
          </div>
        </div>
        <div>
          <div class="category_product" v-for="productInfo in productInfos">
            <div class="product_img">
              <img :src="productInfo.icon"/>
            </div>
            <div class="product_info_1">
              <div>
                <div class="product">{{productInfo.name}}</div>
              </div>
              <div class="sell">
                月售:{{productInfo.sellCount}}  好评:{{productInfo.commentCount}}
              </div>
              <div class="price">
                ￥{{productInfo.price}}
              </div>
              <div class="add">
                +
              </div>
            </div>
          </div>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">

      </mt-tab-container-item>
    </mt-tab-container>

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
  import {Tabbar, TabItem} from 'mint-ui';

  export default {
    name: 'page-tabbar',
    mounted() {
      this.getStoreInfo();
      this.listCategory();
    },
    data() {
      return {
        top_selected: "1",
        bottom_selected: '外卖',
        category_selected: '',
        store: '',
        categories: [],
        productInfos: []
      };
    },
    methods: {
      changeSelectCategory(categoryId) {
        this.category_selected = categoryId;
        this.getProductInfos();
      },
      async getStoreInfo() {
        const res = await this.$httpGet("/store/info.json", {}, {credentials: true, emulateJSON: true});
        this.store = res.data;
      },
      async listCategory() {
        const res = await this.$httpGet("/product/listCategory.json", {}, {credentials: true, emulateJSON: true});
        this.categories = res.data;
        this.category_selected = this.categories[0].id
        this.getProductInfos();
      },
      async getProductInfos() {
        const res = await this.$httpGet("/product/listByCategory.json", {
          categoryId: this.category_selected
        }, {credentials: true, emulateJSON: true});
        this.productInfos = res.data;
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

  .category_1 {
    float: left;
  }

  .category {
    margin-top: 30px;
    text-align: left;
    margin-left: 20px;
    border-right: 1px solid #000;
    width: 60px;
  }

  .category_product {
    margin-top: 30px;
    text-align: left;
    margin-left: 100px;
  }

  .product_info_1 {
    float: left;
  }

  .product {
    padding-left: 15px;
    font-weight: bold;
  }

  .sell {
    padding-top: 10px;
    padding-left: 15px;
    font-size: small;
  }

  .price {
    margin-top: 12px;
    line-height: 24px;
    padding-left: 12px;
    color: red;
    display: inline-block;
  }

  .add {
    font-size: 25px;
    margin-top: 12px;
    line-height: 24px;
    margin-left: 90px;
    float: right;
    border-radius: 15px;
    color: white;
    background: deepskyblue;
    padding: 4px;
  }

  .click {
    background: skyblue;
  }
</style>
