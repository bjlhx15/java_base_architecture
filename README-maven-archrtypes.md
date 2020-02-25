# 自定义模块化生成框架

### 功能
生成满足我们项目需求的工程模板，提高开发效率，同时可以统一团队内的项目结构风格

maven-archetypes-webapp 默认模式，常用

maven-archetypes-webapp-cache 使用redis缓存，没被包装的，需要编写service 代码添加spring cache注解

maven-archetypes-webapp-custom-cache 包装了缓存，如redis等，此时直接使用会报错，需要自定义。此时也会有好处没有破坏service自动生成的代码


### 开发环境  
- maven3.2.3
- jdk1.8
- IntelliJ IDEA 2018
- mac OS

### archetype技术选型
- DB  
MySQL
- ORM  
MyBatisPlus
- Framework  
SpringBoot

### DONE
- [x] SpringBoot、MyBatisPlus框架的整合
- [x] 统一异常的处理
- [x] 统一结果封装
- [x] log4j日志的配置
- [x] MybatisPlus
  - [x] 代码生成器 
  - [x] 分页
  - [x] 逻辑删除
  
### TODO
- [ ] 工具类的整合
- [ ] 常用中间件的封装

### Quick Start【快速使用】
1. 框架包
- 方式一、直接下载pom，安装至本地工厂,或者使用下述第1、2步骤
```xml
<dependency>
    <groupId>com.github.bjlhx15</groupId>
    <version>0.0.1-SNAPSHOT</version>
    <artifactId>maven-archetypes-webapp</artifactId>
</dependency>
```
- 方式二、下载源码编译

``` bash
git clone https://github.com/bjlhx15/java_base_architecture.git
#打开工程，将其安装到本地仓库  
mvn clean install
```
2. 使用自定义archetype初始化项目

```bash
mvn archetype:generate \
-DgroupId=com.aaa.test -DartifactId=test-cache-demo -Dversion=1.0.0-SNAPSHOT \
-DarchetypeGroupId=com.github.bjlhx15 -DarchetypeArtifactId=maven-archetypes-webapp-cache -DarchetypeVersion=0.0.1-SNAPSHOT \
-X -DarchetypeCatalog=local -DinteractiveMode=false
```
  参数说明  
  `-DgroupId`组ID，默认项目的包名的组ID相同  
  `DartifactId`：项目唯一标识符，即项目名称  
  `-DarchetypeGroupId`：框架的组ID，值不需要进行修改  
  `-DarchetypeArtifactId`：框架的artifactId，值不需要进行改变

3. 配置逆向代码工具
配置项目XX-generator的resources为资源文件

配置类中：MybatisPlusGenerator  数据库连接等配置

注，如果service定制化，需要在resources的service中配置

4. web配置

修改resource文件夹下的配置文件

5. 启动项目  
`web`模块下的`MainApplication`类为启动类，运行该类即可启动
