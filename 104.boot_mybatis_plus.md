### 0、开发规则
    0.1、生成的xml位于resources中
    0.2、扩展代码编写：
        生成的所有文件位于对应层的auto下
        entity下 auto自动生成代码 如User，扩展的位于auto目录同层图 entity下的UserVo
        mapper下 auto自动生成代码 如UserMapper,扩展的位于auto目录同层图 mapper 下的 UserExtMapper
        mapper.xml resources/mapper/autoxml, autoxml自动生成代码 如UserMapper.xml,扩展的位于autoxml目录同层图 mapper.xml 下的 UserExtMapper.xml
    0.3、配置扫描MapperScan
    0.4、配置扫描xml：可以在application.properties
```properties
mybatis.type-aliases-package=com.github.bjlhx15.mybatis.springboot.base.entity
mybatis.mapper-locations=classpath*:/mapper/**/*.xml
mybatis.config-location=classpath:/mybatis.xml
```
    0.5、
### 1、pom
```xml
        <!--   mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.1.2</version>
        </dependency>
        <!--   mybatis 帮助类型转换 如LocalDatetime-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-typehandlers-jsr310</artifactId>
            <version>1.0.1</version>
        </dependency>
```
### 2、逆向工程
参看：mp-mybatis-springboot-base1
2.1、pom插件
```xml
        <!--   mybatis-plus 代码生成器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.1.2</version>
        </dependency>
        <!--   mybatis-plus 代码生成器 使用-->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.29</version>
        </dependency>
```
2.2、逆向代码生成：MybatisPlusGenerator
        说明：默认生成controller、service、mapper、entity
        以及swagger注解，所以需要添加swagger的pom
### 3、分页配置 配置类
```java
@Configuration
@EnableTransactionManagement
//mapper
@MapperScan({
        "com.github.bjlhx15.mybatis.springboot.base.mapper"
})//将项目中对应的mapper类的路径加进来
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        return new PaginationInterceptor().setDialectType("mysql");
    }
}
```

