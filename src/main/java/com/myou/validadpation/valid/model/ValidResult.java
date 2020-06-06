package com.myou.validadpation.valid.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author myou
 * @Date 2020/6/5  4:27 下午
 */
@Data
public class ValidResult {
    private boolean isError;
    private List<ErrorMessage> errors;

    public void addError(String prop, String error) {
        if (null == errors) {
            errors = new ArrayList<>();
        }
        errors.add(new ErrorMessage(prop, error));
    }
}
