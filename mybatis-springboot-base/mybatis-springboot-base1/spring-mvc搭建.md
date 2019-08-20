一、
mybatis
druid
spring
log4j2
swagger2：{ip}:{port}/{project-name}/swagger-ui.html
    引用pom，增加配置类，增加xml bean注入

二、运行方式
方式一、
    spring-boot-starter-tomcat 改为compile，直接运行main即可
方式二
    以spring-boot：run（maven方式）方式启动工程 （IDEA工具）
    Run–》Edit Configurations–》+—》选择Maven–》配置命令为spring-boot:run
    右上角选择启动方式
    
原因，因为我们直接运行application类，而我们的servlet-api等依赖tomcat的库的scope为provide，所以我们的tomcat的scope需要改为compile 
    以spring-boot：run方式启动相当于直接部署工程到tomcat中，所以tomcat的scope为provide也不会影响

三、
druid
    http://localhost:8080/druid/login.html
swagger
    http://localhost:8080/swagger-ui.html
    权限：方式一 环境权限配置 @Profile({"dev","test"})
