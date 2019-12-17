生成`controller`、`service`、`po`、`dao【mapper、xml(位于web下的resources中)】`层代码，生成的代码需根据个人需求的改动，方可使用  
项目使用mybatis-plus 代码生成 自动生成，
- 说明：
    - 所有层级生成文件自动放在在对应层下的auto下，请不要修改auto下任何文件，以防止需求修改再次代码生成覆盖掉
    - 各层扩展 可以继承原有文件使用
    
- DATABASE_XX : 数据库配置项
- main 生产方法
    - tableNames 表配置
    - LayerType 分层配置
    
高级说明
- service 支持自定义 BaseService，默认设置关闭，可以搜索 strategy.setSuperService 开启
-     