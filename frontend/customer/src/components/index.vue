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
    name: 'index',
  };
</script>
