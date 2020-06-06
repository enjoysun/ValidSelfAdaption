package com.myou.validadpation.valid.intercept;

import com.alibaba.fastjson.JSON;
import com.myou.validadpation.model.CarQo;
import com.myou.validadpation.valid.annotation.ElasticValid;
import com.myou.validadpation.valid.model.ValidResult;
import com.myou.validadpation.valid.utils.ValidationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author myou
 * @Date 2020/6/5  5:42 下午
 */
@Component
public class ValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Parameter[] parameters = ((HandlerMethod) handler).getMethod().getParameters();
        for (Parameter parameter : parameters) {
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ElasticValid.class)) {
                    if (request.getMethod().equals("POST")) {
                        Object object = JSON.parseObject(body, parameter.getParameterizedType());
                        ValidResult validResult = ValidationUtils.validObject(object);
                    }
                    if (request.getMethod().equals("PATCH")) {
                        Object object = JSON.parseObject(body, parameter.getParameterizedType());
                        Map<String, Object> parse = (Map<String, Object>) JSON.parse(body);
                        ValidResult validResult = ValidationUtils.validField(object, parse);
                    }
                }
            }
        }
        System.out.println("=====拦截器启动====");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=====执行中=====");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("====end====");
    }
}
