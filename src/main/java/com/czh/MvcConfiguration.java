package com.czh;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by czh on 17-6-4.
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    //json解析ModelAndView
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.freeMarker().cache(false);
    }

    /**
     * 静态资源
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("d/static/**").addResourceLocations("/static/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 我们要求DispatcherServlet将对静态资源的请求转发到Servlet容
     * 器中默认的Servlet上,而不是使用DispatcherServlet本身来处理
     * 此类请求
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }


}
