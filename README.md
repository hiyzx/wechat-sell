# takeaway
基于spring-boot开发的系统，分为用户端和管理端。 
### 模块介绍:
``` lua
takeaway
├── api
|    ├── parent -- 父类pom.xml及各版本对应的sql语句。
|    ├── common-conf -- 公共配置、工具类等。
|    ├── user-service -- 集成Mybatis框架公共模块,提供用户相关服务。
|    ├── product-service -- 集成Mybatis框架公共模块，提供产品相关服务。
|    ├── customer -- 用户相关controller和service,使用json web token+注解做权限校验,swagger在线文档等
|    └── admin -- 后台管理相关controller和service
├── frontend
    ├── customer -- 前端h5页面(使用vue)
    └── admin -- 后端pc页面(未开发)
```

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
   4. 分别执行CustomerApplication.java和AdminApplication.java下的main方法
   5. 使用HealthCheckController下的接口校验版本及第三方服务正常与否
2. 前端
    1. 修改http.js中接口地址
    2. 使用命令行,进入customer目录
    3. 执行vue命令:npm install / npm run dev

### 在线小工具

- [在线Cron表达式生成器](http://cron.qqe2.com/ "在线Cron表达式生成器")

- [在线工具箱](http://tool.lu/ "在线工具 - 程序员的工具箱")

- [json在线解析](http://www.json.cn/) 
    

#### spring-boot生成war包
1. 设置pom的属性为war 
2. 移除spring-boot-starter-web中的内置tomcat
3. 添加实现了SpringBootServletInitializer的类ServletInitializer并且
override方法configure;
4. 使用maven的打包命令