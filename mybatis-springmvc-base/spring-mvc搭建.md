mybatis
druid
spring-mvc
log4j2
swagger2：{ip}:{port}/{project-name}/swagger-ui.html
    引用pom，增加配置类，增加xml bean注入




1、引入pom
2、在基本项目结构下
    src/main/java、src/main/resources、
    添加一个src/main/webapp
    在webappz中添加 WEB-INF,web.xml,此时会提示配置为 web 项目
3、web.xml配置
    resources增加spring基础配置applicationContext.xml     mvc-servlet.xml
    
    
