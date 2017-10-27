<template>
  <div>
    <mt-header fixed title="订单列表" style="font-size: 22px;"></mt-header>

    <div style="overflow: scroll;">
      <mt-loadmore :top-method="loadTop" :bottom-method="loadBottom" :bottom-all-loaded="allLoaded" ref="loadmore">

        <div v-if="pageInfo.total == 0">
          暂无订单!!!
        </div>
        <div v-for="myOrder in orderList">
          <div class="main">
            <div class="main_content">
              <div class="head_img">
                <img :src="myOrder.productImage"/>
              </div>
              <div style="width: 100%;text-align: left;margin-left: 30px">
                <div class="information">
                  <div class="information_data">
                    <div id="">
                      {{myOrder.productName}} 等商品
                    </div>
                    <div style="font-size: 15px;margin-top: 5px">
                      {{myOrder.orderTime}}
                    </div>
                  </div>
                  <div class="dstate">
                    {{myOrder.orderStatusDisplay}}
                  </div>
                </div>
                <div>
                  {{myOrder.totalCount}}件商品  总金额:￥{{myOrder.totalAmount}}
                </div>
              </div>
            </div>
            <div class="main_button">
              <div class="button_one">再来一单</div>
            </div>
          </div>
        </div>

      </mt-loadmore>
    </div>

    <page-bottom></page-bottom>
  </div>
</template>
<script>
  import pageBottom from './../common/bottom'
  import {Toast} from 'mint-ui';
  import {Loadmore} from 'mint-ui';

  export default {
    components: {pageBottom},
    data() {
      return {
        allLoaded: false,
        sessionId: '',
        page: 1,
        pageSize: 4,
        pageInfo: {},
        orderList: {}
      };
    },
    mounted() {
      this.sessionId = localStorage.getItem('sessionId');
      this.listMyOrders();
    },
    methods: {
      loadTop() {
        this.page = this.page - 1;
        this.listMyOrders();
        this.$refs.loadmore.onTopLoaded();
      },
      loadBottom() {
        this.page = this.page + 1;
        this.listMyOrders();
        this.$refs.loadmore.onBottomLoaded();
      },
      async listMyOrders() {
        const self = this;
        const res = await
          this.$httpPost('/order/list.json', {
            sessionId: this.sessionId,
            page: this.page,
            pageSize: this.pageSize
          }, {emulateJSON: true});
        if (res.resCode === '000000') {
          self.pageInfo = res.data;
          self.orderList = res.data.list;
          self.allLoaded = !res.data.hasNextPage;
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
      }
    }
  }
</script>
<style type="text/css">
  * {
    margin: 0;
    padding: 0;
  }

  .main {
    padding: 10px;
    border-bottom: 1px solid #ccc;
    overflow: hidden;
  }

  .main_content {
    display: flex;
  }

  .head_img {
    min-width: 16%;
    max-width: 16%;
  }

  .head_img img {
    width: 100%;
    vertical-align: top;
  }

  .details {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    color: rgba(51, 51, 51, 0.81);
    overflow: hidden;
  }

  .dstate {
    float: right;
  }

  .information_data > div {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
  }

  .information > div {
    display: inline-block;
  }

  .information_data {
    width: 60%;
  }

  .main_button {
    float: right;
  }

  .main_button > div {
    display: inline-block;
  }

  .button_one {
    border: 1px solid blue;
    color: blue;
  }

  .button_two {
    border: 1px solid red;
    color: red;
  }
</style>
