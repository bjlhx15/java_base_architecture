# 自定义模块化生成框架

主要是：使用默认redis缓存，在service 层，直接spring cache注解，

注意：默认实体是继承父类的，但实际继承时，和缓存不会使用父类属性，故mybatis生成时，不要继承父类

## 使用

```bash
mvn archetype:generate \
-DgroupId=com.aaa.test -DartifactId=test-custom-cache-demo -Dversion=1.0.0-SNAPSHOT \
-DarchetypeGroupId=com.github.bjlhx15 -DarchetypeArtifactId=maven-archetypes-webapp-cache -DarchetypeVersion=0.0.1 \
-X -DarchetypeCatalog=local -DinteractiveMode=false
```
