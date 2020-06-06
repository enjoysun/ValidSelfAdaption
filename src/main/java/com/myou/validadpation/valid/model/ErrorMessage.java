package com.myou.validadpation.valid.model;

import lombok.Data;

/**
 * @Author myou
 * @Date 2020/6/5  4:25 下午
 */
@Data
public class ErrorMessage {
    public ErrorMessage(String errorPropertyName, String errorMessage) {
        this.errorPropertyName = errorPropertyName;
        this.errorMessage = errorMessage;
    }

    private String errorPropertyName;
    private String errorMessage;
}
