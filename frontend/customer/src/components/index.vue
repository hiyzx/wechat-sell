<template>
  <div id="index">
    <mt-header fixed title="首页" style="font-size: 22px;"></mt-header>

    <page-header></page-header>
    <category-product></category-product>
    <page-bottom></page-bottom>

  </div>
</template>
<script>
  import {Tabbar, TabItem} from 'mint-ui';
  import pageHeader from './product/header'
  import CategoryProduct from './product/categoryProduct'
  import pageBottom from './common/bottom'

  export default {
    components: {pageHeader, CategoryProduct, pageBottom},
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
        this.category_selected = this.categories[0].id;
        this.getProductInfos();
      },
      async getProductInfos() {
        const res = await this.$httpGet("/product/listByCategory.json", {
          categoryId: this.category_selected
        }, {credentials: true, emulateJSON: true});
        this.productInfos = res.data;
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
    overflow: hidden;
    position: relative;
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
    border-radius: 15px;
    color: white;
    background: deepskyblue;
    padding: 6px;
    position: absolute;
    bottom: 0;
    right: 24px;
    line-height: 14px;
  }

  .click {
    background: skyblue;
  }
</style>
