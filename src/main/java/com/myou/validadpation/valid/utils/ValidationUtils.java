package com.myou.validadpation.valid.utils;

import com.myou.validadpation.valid.model.ValidResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @Author myou
 * @Date 2020/6/6  3:36 下午
 */
public class ValidationUtils {
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 验证ValueObject
     *
     * @param object 验证对象类
     * @param groups hibernateValidator 验证组
     * @param <T>    泛型
     * @return
     */
    public static <T> ValidResult validObject(T object, Class<?>... groups) {
        ValidResult validResult = new ValidResult();
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        validResult.setError(null != validate && validate.size() > 0);
        validate.forEach(tConstraintViolation -> {
            validResult.addError(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
        });
        return validResult;
    }

    /**
     * 验证ValueObject的指定field
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> ValidResult validField(T object, Map<String, Object> map) {
        ValidResult result = new ValidResult();
        Set<String> keySet = map.keySet();
        keySet.forEach(field -> {
            if (null != field) {
                Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(object, field);
                result.setError(null != constraintViolations && constraintViolations.size() > 0);
                constraintViolations.forEach(tConstraintViolation -> {
                    result.addError(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
                });
            }
        });
        return result;
    }
}
