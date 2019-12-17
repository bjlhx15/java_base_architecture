package com.github.bjlhx15.mybatis.springboot.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//xml资源
@ImportResource({
        "classpath:applicationContext.xml"
//        ,
//        "classpath:mvc-servlet.xml"
})
//属性
//@PropertySource({
//        "classpath:application.properties",
//        "classpath:config.properties",
//        "classpath:important.properties"
//})
//mapper
@MapperScan({
        "com.github.bjlhx15.mybatis.springboot.base.repository"
})//将项目中对应的mapper类的路径加进来
@EnableCaching
public class MainServiceApplication extends SpringBootServletInitializer  {
    //SpringBootServletInitializer web启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainServiceApplication.class);
    }


    /**
     *  前端 跟 后端交互 数据式转换
     * @return
     */
//    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {

        ObjectMapper objectMapper = new ObjectMapper();

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        /**
         * 将long类型的数据转为String类型 防止出现【在Long长度大于17位时会出现精度丢失的问题】
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        return jackson2HttpMessageConverter;
    }


    //main启动
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MainServiceApplication.class, args);
        System.err.println("\r\n---项目 启动成功---");
    }


//    @Bean
    public RestTemplate newRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
