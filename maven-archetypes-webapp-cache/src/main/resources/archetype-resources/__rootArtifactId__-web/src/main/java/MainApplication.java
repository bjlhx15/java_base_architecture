package ${package};


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

/**
 * 项目启动类
 * */
@SpringBootApplication
@MapperScan("${package}.mapper")
//xml资源
@ImportResource({
        "classpath:applicationContext.xml"
})
@EnableCaching
public class MainApplication extends SpringBootServletInitializer {
    //SpringBootServletInitializer web启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
//        return super.configure(application);
    }
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}