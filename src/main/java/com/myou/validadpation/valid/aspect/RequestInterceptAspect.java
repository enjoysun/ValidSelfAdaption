package com.myou.validadpation.valid.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author myou
 * @Date 2020/6/5  4:32 下午
 */
@Component
@Aspect
public class RequestInterceptAspect {

    @Before("@annotation(javax.validation.Valid)")
    public void validPatchParams(){
        System.out.printf("ok");
    }
}
