<template>
  <div>
    <mt-tabbar v-model="bottom_selected_value" fixed>
      <mt-tab-item id="外卖">
        <img slot="icon" src="../../../static/img/store.png">
        外卖
      </mt-tab-item>
      <mt-tab-item id="订单">
        <img slot="icon" src="../../../static/img/order.png">
        订单
      </mt-tab-item>
      <mt-tab-item id="我的">
        <img slot="icon" src="../../../static/img/my.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>
<script>
  import {Tabbar, TabItem} from 'mint-ui';

  export default {
    name: 'page-bottom',
    data() {
      return {
        bottom_selected_value: '外卖'
      };
    },
    mounted() {
      this.bottom_selected_value = this.$route.query.selected
    },
    watch: {
      bottom_selected_value: function () {
        if ('外卖' === this.bottom_selected_value) {
          this.$router.push({path: '/', query: {selected: '外卖'}});
        }
        if ('订单' === this.bottom_selected_value) {
          this.$router.push({path: '/myOrder', query: {selected: '订单'}});
        }
        if ('我的' === this.bottom_selected_value) {
          this.$router.push({path: '/personal', query: {selected: '我的'}})
        }
      }
    }
  };
</script>
