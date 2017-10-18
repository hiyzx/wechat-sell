<template>
    <div id="register">
        <h2>注册</h2>
        <mt-field label="年龄" placeholder="请输入年龄" v-model="age" type="number" min="18"></mt-field>
        <br/>
        <mt-field label="姓名" placeholder="请输入姓名" v-model="username"></mt-field>
        <mt-field label="密码" placeholder="请输入密码" v-model="password"></mt-field>
        <mt-button @click.native="register">注册</mt-button>
    </div>
</template>

<script>
    import {Toast} from 'mint-ui';
    import {Field} from 'mint-ui';

    export default {
        name: 'register',
        data() {
            return {
                age: '',
                username: '',
                password: '',
                phone: this.$route.query.phone
            }
        },
        methods: {
            register: function () {
                this.$http.post("http://localhost:8083/customer/auth/register.json",
                    {
                        age: this.age,
                        phone: this.phone,
                        name: this.username,
                        password: this.password
                    },
                    {credentials: true}).then(
                    function (res) {
                        var resp = res.body;
                        if (resp.resCode === '000000') {
                            Toast({
                                message: '注册成功!',
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
