package com.khh.demo.stomp.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by 951087952@qq.com on 2017/8/17.
 * 这个是配置springmvc的java文件
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.khh.demo.stomp.web.controller")
public class MyWebConf {

    /**
     * 配置JSP视图解析器
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/demo/stomp/");
        resolver.setSuffix(".jsp");
        //添加jstl支持
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return resolver;
    }

}
