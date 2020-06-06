package com.myou.validadpation.controller;

import com.myou.validadpation.model.CarQo;
import com.myou.validadpation.valid.annotation.ElasticValid;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author myou
 * @Date 2020/6/5  3:57 下午
 */
@RestController
@RequestMapping("/car")
public class TestController {

    @PostMapping
    public void testPostValid(@ElasticValid @RequestBody CarQo carQo) {
        System.out.println(carQo.toString());
    }

    @PatchMapping
    public void testPatchValid(@ElasticValid @RequestBody CarQo carQo) {
        System.out.println(carQo.toString());
    }
}
