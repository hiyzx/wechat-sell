<template>
  <div>
    <mt-header fixed title="订单列表" style="font-size: 22px;"></mt-header>
    <div class="main">
      <div class="main_content">
        <div class="head_img">
          <img src="http://114.67.147.157/file/image/store.jpg"/>
        </div>
        <div>
          <div class="information">
            <div class="information_data">
              <div id="">
                都骄傲手动驾驶都骄傲手动驾驶(龙科店)
              </div>
              <div>
                2017-10-23 11:22
              </div>
            </div>
            <div class="dstate">
              订单已经完成
            </div>
          </div>
          <div>
            <div class="details">点击都奥死军大搜军军军军多多多多多多多多多多多多多多多多多多多多多多多多多多</div>
          </div>
          <div>
            $13
          </div>
        </div>
      </div>
      <div class="main_button">
        <div class="button_one">再来一单</div>
        <div class="button_two">评价得10金币</div>
      </div>
    </div>

    <page-bottom></page-bottom>
  </div>
</template>
<script>
  import pageBottom from './../common/bottom'

  export default {
    components: {pageBottom}
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
    min-width: 14%;
    max-width: 14%;
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
