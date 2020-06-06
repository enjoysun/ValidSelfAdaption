package com.myou.validadpation.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author myou
 * @Date 2020/6/5  4:03 下午
 */
@Data
public class CarQo {
    @NotNull(message = "车名不能为空")
    @NotEmpty(message = "车名不能为空")
    private String carName;
    @NotEmpty(message = "品牌不能为空")
    private String carBrand;
    @NotEmpty(message = "车牌不能为空")
    private String carNumber;
    @Min(value = 10, message = "不能小于10")
    private int age;
}
