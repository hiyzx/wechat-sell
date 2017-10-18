<template>
    <div id="code">
        <h2>验证码</h2>
        <mt-field label="手机号" placeholder="请输入手机号" v-model="phone"></mt-field>
        <mt-field label="验证码" placeholder="请输入右图验证码" v-model="captcha">
         <img src="http://localhost:8083/customer/auth/captcha" height="40px" width="70px"></mt-field><br/>
        <mt-button @click.native="login">获取短信验证码</mt-button>
    </div>
</template>

<script>
    import {Toast} from 'mint-ui';
    import {Field} from 'mint-ui';

    export default {
        name: 'code',
        data() {
            return {
                phone: '',
                captcha: ''
            }
        },
        methods: {
            login: function () {
                this.$http.post("http://localhost:8083/customer/auth/sendMsg.json",
                    {phone: this.phone,type:1, userInputCaptcha: this.captcha},
                    {emulateJSON: true, credentials: true}).then(
                    function (res) {
                        var resp = res.body;
                        if (resp.resCode === '000000') {
                            Toast({
                                message: '发送成功',
                                position: 'middle',
                                duration: 2000
                            });
                        } else {
                            Toast({
                                message: resp.resDes,
                                position: 'middle',
                                duration: 2000
                            });
                        }
                    }
                );
            }
        }
    }
</script>
