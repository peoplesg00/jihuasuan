package com.atli.provider.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import response.ResultEnum;
import response.ResultText;

/**
 * @author: LI
 * @Date: 2020/7/6 17:08
 * @Description:
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Object handler(Exception e) {
        System.out.println(e.getMessage());
        return ResultText.error(ResultEnum.DATA_FAIL, e.getMessage());
    }
}
