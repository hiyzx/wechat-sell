<!--suppress ALL -->
<template>
  <div>
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
  </div>
</template>
<script>
  import {Tabbar, TabItem} from 'mint-ui';
  import md5 from 'js-md5';

  export default {
    name: 'category-product',
    mounted() {
      this.listCategory();
    },
    data() {
      return {
        top_selected: "1",
        category_selected: '',
        categories: [],
        productInfos: []
      };
    },
    methods: {
      changeSelectCategory(categoryId) {
        this.category_selected = categoryId;
        this.getProductInfos();
      },
      async listCategory() {
        const timestamp = Date.parse(new Date());
        const authorization = md5(timestamp + "");
        const res = await this.$httpGet("/product/listCategory.json", {
          timestamp : timestamp,
          authorization : authorization
        }, {credentials: true, emulateJSON: true});
        this.categories = res.data;
        this.category_selected = this.categories[0].id;
        this.getProductInfos();
  },
  async getProductInfos() {
    const timestamp = Date.parse(new Date());
    const authorization = md5(timestamp + "");
    console.log("123")
    const res = await this.$httpGet("/product/listByCategory.json", {
      categoryId: this.category_selected,
      timestamp : timestamp,
      authorization : authorization
    }, {credentials: true, emulateJSON: true});
    this.productInfos = res.data;
  }
  }
  };
</script>
<style>
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
