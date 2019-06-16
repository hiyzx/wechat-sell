# takeaway
spring-boot + spring-cloud 开发的系统。

### 模块介绍:
``` lua
takeaway
├── api
|    ├── parent -- 父类pom.xml及各版本对应的sql语句。
|    ├── common-conf -- 公共配置、工具类等。
|    ├── admin-server -- 可视化监控服务。
|    ├── tx-manager-server -- 分布式事务处理服务。
|    ├── config-server -- 配置服务。
|    ├── eureka-server -- 注册中心服务。
|    ├── zuul-server -- 网关服务
|    └── micro-server-provider -- 各个服务提供者
|           ├── message-server -- 短信服务。
|           ├── order-server -- 订单服务。
|           ├── product-server -- 商品服务
|           └── user-server -- 用户服务
├── frontend
    ├── customer -- 前端h5页面(使用vue)
    └── admin -- 后端pc页面(未开发)
```
![image](https://raw.githubusercontent.com/hiyzx/takeaway/spring-cloud-branch/api/parent/doc/%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)
#### 开发环境
- Jdk1.8
- Mysql5.7
- Redis
- ActiveMQ

### 启动应用

1. 后端
   1. 新建数据库takeaway,执行parent下sql语句
   2. 修改application-dev.yml下各个开发工具的用户名和密码
   3. maven编译安装parent下各模块
   4. 依次启动上图的所有服务
2. 前端
    1. 修改http.js中接口地址
    2. 使用命令行,进入customer目录
    3. 执行vue命令:npm install / npm run dev

### 在线小工具

- [在线Cron表达式生成器](http://cron.qqe2.com/ "在线Cron表达式生成器")

- [在线工具箱](http://tool.lu/ "在线工具 - 程序员的工具箱")

- [json在线解析](http://www.json.cn/) 
    