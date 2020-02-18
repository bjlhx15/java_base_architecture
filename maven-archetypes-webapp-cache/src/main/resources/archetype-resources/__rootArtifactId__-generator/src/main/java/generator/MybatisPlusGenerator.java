package ${package}.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.util.ArrayList;
import java.util.List;

public class MybatisPlusGenerator {

    // region 待修改配置
    // 数据库信息 jdbc:mysql://localhost:3358/test?useUnicode=true&useSSL=false&characterEncoding=utf8
    private static final String DATABASE_DRIVER_NAME = "com.mysql.cj.jdbc.Driver"; //com.mysql.jdbc.Driver
    private static final String DATABASE_HOST = "localhost";
    private static final String DATABASE_PORT = "3358";
    private static final String DATABASE = "test";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "123456";
    private static final String DATABASE_URL = "jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE
            + "?useUnicode=true&useSSL=false&characterEncoding=UTF8&serverTimezone=UTC";

    //endregion

    // 所有文件开启 覆盖配置
    // 指定表名配置，不指定代表所有
    public static void main(String[] args) {
        String[] tableNames = new String[]{"account_balance"};//null;//
        generator(
                //new LayerType[]{LayerType.entity,LayerType.mapperxml},
                null,
                tableNames);
        System.out.println("==========ok=========");
    }

    //包名 com.github.bjlhx15
    private static final String packageName = "${package}";

    //代码生成路径 相对当前的路径
    private static final String baseProjectPath = System.getProperty("user.dir");

    //代码注释作者
    private static final String author = "<a>https://www.cnblogs.com/bjlhx/</a>";

    // 生成的分层代码
    enum LayerType {
        entity("/${rootArtifactId}-model"),
        mapper("/${rootArtifactId}-dao"),
        mapperxml("/${rootArtifactId}-web"),
        service("/${rootArtifactId}-service"),
        web("/${rootArtifactId}-web"),
        ;

        LayerType(String path) {
            this.path = path;
        }

        /**
         * 路径
         */
        private String path;
        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }
    }

    /**
     * 逆向工程 生成分层代码，默认覆盖模式
     *
     * @param layerTypes 要生成的分层名 null 全部，指定的话只生成指定的包
     * @param tableNames 要生成的表名
     */
    private static void generator(LayerType[] layerTypes, String[] tableNames) {
        if (layerTypes == null) {
            layerTypes = LayerType.values();
        }
        for (LayerType layerType : layerTypes) {
            String projectPath = baseProjectPath + layerType.getPath();
            // 全局配置
            GlobalConfig gc = getGlobalConfig(projectPath);
            // 数据源配置
            DataSourceConfig dsc = getDataSourceConfig();
            // 包配置
            PackageConfig pc = getPackageConfig();
            // xml配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {

                }
            };
            if (layerType.equals(LayerType.mapperxml)) {
                // 自定义配置
                cfg = getInjectionConfig(projectPath);
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
        //数据库表映射到实体的命名策略,该处下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表映射到实体的命名策略,该处下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        if (tableNames != null && tableNames.length > 0) {
            strategy.setInclude(tableNames); //被扫描的表名 需要包含的表名，允许正则表达式（与exclude二选一配置）
        }

        //实体类自动继承Entity,不需要也可以
//        strategy.setSuperEntityClass(String.format("%s.po.BasePO", packageName));
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(false);
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id","delflag","status","remark","created_datetime","updated_datetime");

        // 自定义 service 父类
        //strategy.setSuperServiceClass(String.format("%s.service.base.IBaseService", packageName));
        // 自定义 service 实现类父类
        //strategy.setSuperServiceImplClass(String.format("%s.service.base.BaseServiceImpl", packageName));

        // 控制层是否使用Rest风格  生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //控制层自动继承父类BaseController,不需要也可以 BaseController
        strategy.setSuperControllerClass(String.format("%s.controller.base.BaseController", packageName));
        strategy.setControllerMappingHyphenStyle(true);

        //根据表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        return strategy;
    }

    //xml 定制化
    private static InjectionConfig getInjectionConfig(String projectPath) {
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
                return projectPath + "/src/main/resources/mapper/"
                        + "/autoxml/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    // 数据源配置
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATABASE_URL)
                // dsc.setSchemaName("public");
                .setDriverName(DATABASE_DRIVER_NAME)
                .setUsername(DATABASE_USERNAME)
                .setPassword(DATABASE_PASSWORD)
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //将数据库中timestamp转换成date
                        if (fieldType.toLowerCase().contains("timestamp")) {
                            return DbColumnType.DATE;
                        }
                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                    }
                });
        return dsc;
    }

    // 包配置
    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(moduleName)//自定义模块名 account
        pc.setParent(packageName)//《====包名（自己手动设置）com.lihongxu.test 与 module组合
                .setEntity("po.auto")//Entity包名
                .setMapper("mapper.auto")//mapper包名
                .setService("service.auto")
                .setServiceImpl("service.auto.impl")
                .setController("controller.auto");
        return pc;
    }

    // 全局配置
    private static GlobalConfig getGlobalConfig(String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");//生成文件的输出目录
        gc.setOpen(false);//是否打开输出目录 默认true
        gc.setFileOverride(true);// 是否覆盖已有文件 默认false
        gc.setActiveRecord(false);//开启 ActiveRecord 模式 默认false

        gc.setAuthor(author);// 作者名
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);//开启 BaseResultMap 默认false
        gc.setBaseColumnList(true);//开启 baseColumnList 默认false
        gc.setServiceName("I%sService"); //service接口 命名方式 例如：%sBusiness 生成 UserBusiness
        gc.setSwagger2(true); //实体属性 Swagger2 注解

        // 自定义文件命名，注意 %s 会自动填充表实体属性！ 使用定制化生成了
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setControllerName("%sController");
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
                        // .setService(new TemplateConfig().getService())
                        // .setServiceImpl(new TemplateConfig().getServiceImpl())
                        .setService("/templates/cacheService.java")
                        .setServiceImpl("/templates/cacheServiceImpl.java")
                        .setController(null);
                break;
            case web:
                templateConfig.setEntity(null)
                        .setMapper(null)
                        .setXml(null)
                        .setService(null)
                        .setServiceImpl(null)
//                        .setController(null);
//                        .setController(new TemplateConfig().getController());
                        .setController("/templates/basecontroller.java");
                break;
            default:
                throw new IllegalArgumentException("参数匹配错误，请检查");
        }
        return templateConfig;
    }
}
