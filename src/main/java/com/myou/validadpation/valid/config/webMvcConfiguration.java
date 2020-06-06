package com.myou.validadpation.valid.config;

import com.myou.validadpation.valid.intercept.ValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author myou
 * @Date 2020/6/5  5:49 下午
 */
@Configuration
public class webMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private ValidationInterceptor validationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor).addPathPatterns("/**");
    }
}
