package ${package}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//旧版本 WebMvcConfigurerAdapter  spring5弃用了 WebMvcConfigurerAdapter
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //提到最前面 ，主要为了处理基础类型时 XXX not cast String type 类似问题，结合请求类型必须为application/json
        // 提前使用MappingJackson2HttpMessageConverter 处理 避免使用 StringHttpMessageConverter处理 String类型
        converters.add(0,new MappingJackson2HttpMessageConverter());
    }
}
