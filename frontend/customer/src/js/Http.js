function Http(Vue) {
    if (!Http.installed) {
        //const Host = 'http://192.168.0.118:8083/customer'
        const Host = 'http://www.hiyzx.cn:8083/customer'

        function serialize(query) {
            let urlText = '';
            if (query && typeof query === 'object') {
                Object.keys(query).forEach((item) => {
                    if (query[item] && query[item] !== 'undefined') {
                        urlText += `&${item}=${query[item]}`
                    }
                })
            }
            return urlText.replace(/^&/, '?') // 把第一个& 换成 ？
        }

        function httpGet(url, data) {
            url = Host + url + serialize(data)
            return Vue.http.get(url).then(res => {
                return res.data ? res.data : null
            })
        }

        function httpPost(url, data, options) {
          url = Host + url;
            return Vue.http.post(url, data, Object.assign({}, { credentials: true }, options))
                .then(res => {
                  console.log(res.data)
                    return res.data ? res.data : null
                })
        }

        Vue.prototype.$httpGet = httpGet
        Vue.prototype.$httpPost = httpPost
        Vue.prototype.$captcha = Host + '/auth/captcha?t='
    }
}
if (typeof window !== 'undefined' && window.Vue) {
    window.Vue.use(Http)
}
export default Http
