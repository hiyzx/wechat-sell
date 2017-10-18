<template>
    <div id="code">
        <h2>验证码</h2>
        <mt-field label="图形验证码" placeholder="请输入右图验证码" v-model="captcha">
            <img :src="imgCode" height="40px" width="70px" @click="reloadImg"></mt-field><br/>
        <mt-field label="手机号" placeholder="请输入手机号" v-model="phone">
        <mt-button @click.native="sendMsg">获取短信</mt-button></mt-field>
        <mt-field label="手机验证码" placeholder="请输入手机验证码" v-model="code"></mt-field>
        <mt-button @click.native="verify">校验短信验证码</mt-button>
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
                captcha: '',
                code: '',
                imgCode:'http://localhost:8083/customer/auth/captcha'
            }
        },
        methods: {
            reloadImg(){
                this.imgCode = 'http://localhost:8083/customer/auth/captcha?t'+new Date()*1
            },
            sendMsg: function () {
                this.$http.post("http://localhost:8083/customer/auth/sendMsg.json",
                    {userInputCaptcha: this.captcha,
                     phone: this.phone,
                     type:1},
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
                            this.reloadImg();
                        }
                    }
                );
            },
            verify: function () {
                const self = this;
                this.$http.post("http://localhost:8083/customer/auth/verify.json",
                    {phone: this.phone,
                     code: this.code,
                     type:1},
                    {emulateJSON: true, credentials: true}).then(
                    function (res) {
                        var resp = res.body;
                        if (resp.resCode === '000000') {
                            self.$router.push({path:'/register',query: {phone: self.phone}})
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
