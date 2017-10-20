# takeaway
write a complete system for learning  spring boot?

### Environment

| env   | version |    
| :---:  | :---:  | 
| jdk   | 1.8    | 
| MySQL | 5.7.19 | 
| redis | 2.8    | 

### Q&A
Q1 How to start?
1. This is a front-ent separation project.
2. This is a maven polymerization project.
   api has four module that include parent , customer, admin and common
   1. parent -> sql
   2. customer -> controller and service
   3. admin -> controller and service
   4. common -> common code and mapper , po 
3. frontend has two module that include customer and admin.

Q2 What technologies are use in the project?  
1. spring-boot + mybatis + maven

Q3 How to run the project?   
1. init the sql that under the dir parent/sql/takeaway.sql
2. install the parent pom
3. run the main method of com.zero.customer.CustomerApplication
4. Open the chrome,and enter localhost:8081/customer/swagger-ui.html

Q4 How to package the project to war if you use spring-boot?
1. pom.xml
    1. set the property packaging war 
    2. remove tomcat jar whick in the spring-boot-starter-web
    3. add class SpringBootStartApplication ,implements SpringBootServletInitializer 
        and override the method configure;(notice:the param builder.sources would quote your application.java)
    4. use command clean package