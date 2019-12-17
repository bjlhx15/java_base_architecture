package com.github.bjlhx15.mybatis.springboot.base;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class MybatisPlusGenerator {

    // 生成的分层代码
    enum LayerType {
        //实体，
        entity, mapper, mapperxml, service, web,
    }

    public static void main(String[] args) {
        //代码生成路径位置w
        final String projectPath = System.getProperty("user.dir") + "/springboot-base/mp-mybatis-springboot-base1";


        String[] tableNames = new String[]{"account_balance"};
        generator(projectPath, "com.github.bjlhx15.mybatis.springboot",
                "base",
//                new LayerType[]{LayerType.entity,LayerType.mapperxml},
                null,
                tableNames);
        System.out.println("==========ok=========");
    }


    /**
     * 逆向工程 生成分层代码，默认覆盖模式
     *
     * @param projectPath 项目路径 src/main/java 之前的 /user/lihongxu/gitcode
     * @param basePackage 包名 src/main/java 之后的 com.github.bjlhx15
     * @param moduleName  模块名 com.github.bjlhx15 之后的 com.github.bjlhx15.account
     * @param layerTypes  要生成的分层名 null 全部，指定的话只生成指定的包
     * @param tableNames  要生成的表名
     */
    private static void generator(String projectPath, String basePackage, String moduleName,
                                  LayerType[] layerTypes, String[] tableNames) {
        if (layerTypes == null) {
            layerTypes = LayerType.values();
        }
        for (LayerType layerType : layerTypes) {
            // 全局配置
            GlobalConfig gc = getGlobalConfig(projectPath);
            // 数据源配置
            DataSourceConfig dsc = getDataSourceConfig();
            // 包配置
            PackageConfig pc = getPackageConfig(basePackage, moduleName);
            // xml配置
            InjectionConfig cfg =new InjectionConfig(){
                @Override
                public void initMap() {

                }
            };
            if (layerType.equals(LayerType.mapperxml)) {
                // 自定义配置
                cfg = getInjectionConfig(projectPath, moduleName);
            }
            // 策略配置**（个性化定制）**
            StrategyConfig strategy = getStrategyConfig(tableNames, pc);

            //代码生成器
            new AutoGenerator()
                    .setGlobalConfig(gc)//全局
                    .setDataSource(dsc)//数据源
                    .setPackageInfo(pc)//包配置
                    .setCfg(cfg)//自定义 xml
                    //.setTemplate(new TemplateConfig().setXml(null))//自定义 xml 此处设置为null，就不会再java下创建xml的文件夹了
                    .setTemplate(getTemplateConfig(layerType))
                    .setStrategy(strategy) //个性化策略
                    .setTemplateEngine(new FreemarkerTemplateEngine())//模板引擎
                    .execute();//执行
        }
    }


    // 策略配置**（个性化定制）**
    private static StrategyConfig getStrategyConfig(String[] tableNames, PackageConfig pc) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略,该处下划线转驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略,该处下划线转驼峰命名
                .setInclude(tableNames) //被扫描的表名 需要包含的表名，允许正则表达式（与exclude二选一配置）

                //.setSuperEntityClass("com.baomidou.ant.common.BaseEntity")//实体类自动继承Entity,不需要也可以
                .setEntityLombokModel(false)//【实体】是否为lombok模型（默认 false）
                //.setSuperEntityColumns("id")// 写于父类中的公共字段

                .setRestControllerStyle(true)//生成 @RestController 控制器
                //控制层自动继承父类BaseController,不需要也可以
//                .setSuperControllerClass("com.baomidou.ant.common.BaseController")
                .setControllerMappingHyphenStyle(true)

                .setTablePrefix(pc.getModuleName() + "_"); //根据表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
        return strategy;
    }

    //xml 定制化
    private static InjectionConfig getInjectionConfig(String projectPath, String moduleName) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //xml 配置
        List<FileOutConfig> focList = new ArrayList();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称================================模块名（自己设置）
                return projectPath + "/src/main/resources/mapper/" + moduleName
                        + "/autoxml/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    // 数据源配置
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3358/test?useUnicode=true&useSSL=false&characterEncoding=utf8")
                // dsc.setSchemaName("public");
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("123456");
        return dsc;
    }

    // 包配置
    private static PackageConfig getPackageConfig(String basePackage, String moduleName) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName)//自定义模块名 account
                .setParent(basePackage)//《====包名（自己手动设置）com.lihongxu.test 与 module组合
                .setEntity("entity.auto")//Entity包名
                .setMapper("mapper.auto")//mapper包名
                .setService("service")
                .setController("controller");
        return pc;
    }

    // 全局配置
    private static GlobalConfig getGlobalConfig(String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java")//生成文件的输出目录
                .setFileOverride(true)// 是否覆盖已有文件 默认false
                .setAuthor("lihongxu")// 作者名
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)//开启 BaseResultMap 默认false
                .setBaseColumnList(true)//开启 baseColumnList 默认false
                .setActiveRecord(false)//开启 ActiveRecord 模式 默认false
                .setServiceName("I%sService") //service接口 命名方式 例如：%sBusiness 生成 UserBusiness
                .setOpen(false)//是否打开输出目录 默认true
                .setSwagger2(true); //实体属性 Swagger2 注解
        return gc;
    }

    //生成分层代码
    private static TemplateConfig getTemplateConfig(LayerType layerType) {
        TemplateConfig templateConfig = new TemplateConfig();
        switch (layerType) {
            case entity:
                templateConfig.setEntity(new TemplateConfig().getEntity(false))
                        .setMapper(null)
                        .setXml(null)
                        .setService(null)
                        .setServiceImpl(null)
                        .setController(null);
                break;
            case mapper:
                templateConfig.setEntity(null)
                        .setMapper(new TemplateConfig().getMapper())
                        .setXml(null)
                        .setService(null)
                        .setServiceImpl(null)
                        .setController(null);
                break;
            case mapperxml:
                templateConfig.setEntity(null)
                        .setMapper(null)
                        .setXml(null)
                        .setService(null)
                        .setServiceImpl(null)
                        .setController(null);
                break;
            case service:
                templateConfig.setEntity(null)
                        .setMapper(null)
                        .setXml(null)
                        .setService(new TemplateConfig().getService())
                        .setServiceImpl(new TemplateConfig().getServiceImpl())
                        .setController(null);
                break;
            case web:
                templateConfig.setEntity(null)
                        .setMapper(null)
                        .setXml(null)
                        .setService(null)
                        .setServiceImpl(null)
                        .setController(new TemplateConfig().getController());
                break;
            default:
                throw new IllegalArgumentException("参数匹配错误，请检查");
        }
        return templateConfig;
    }
}
