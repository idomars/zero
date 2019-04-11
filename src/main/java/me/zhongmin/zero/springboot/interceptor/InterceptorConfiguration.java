package me.zhongmin.zero.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class InterceptorConfiguration implements WebMvcConfigurer {
    //注入拦截器
    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    /**
     * 配置spirngboot2.x 拦截器
     * 默认的拦截器同时会拦截静态资源文件
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").order(2);
        //排除登录路径 order 越小拦截器优先级越高
        registry.addInterceptor(logInterceptor).addPathPatterns("/**").excludePathPatterns("/login").order(1);
    }

    /**
     * 转换请求路径 请求 /min/** 的请求会转至static/静态资源文件夹下
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/min/**").addResourceLocations("classpath:/static/");
    }
}
