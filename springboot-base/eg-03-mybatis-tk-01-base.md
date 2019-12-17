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
mybatis.type-aliases-package=com.github.bjlhx15.mybatis.springboot.base.model
mybatis.mapper-locations=classpath*:/mapper/**/*.xml
mybatis.config-location=classpath:/mybatis.xml
```
    0.5、
### 1、pom
```xml
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.1.5</version>
        </dependency>
```
### 2、逆向工程
参看：tk-mybatis-springboot-base1
2.1、pom插件
```xml
    <!-- 命令：mvn mybatis-generator:generate -->
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.6</version>
        <configuration>
            <!-- 配置实际调用地址-->
            <configurationFile>src/main/resources/mybatis_generatorConfig/generatorConfig-base.xml
            </configurationFile>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.38</version>
            </dependency>
            <dependency>
                <groupId>tk.mybatis</groupId>
                <artifactId>mapper</artifactId>
                <version>4.0.0</version>
            </dependency>
        </dependencies>
    </plugin>
```
2.2、生成配置文件
    generatorConfig-base.xml
2.3、逆向命令
    mvn mybatis-generator:generate
### 3、分页配置
pom
```xml
        <!--        分页插件 即配置-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.12</version>
        </dependency>
```    
3.2、分页配置启用
```properties
# 可以忽略
######### 分页插件 ##########
pagehelper.helper-dialect=mysql
pagehelper.params=count=countSql
pagehelper.reasonable=true
pagehelper.support-methods-arguments=true
```

