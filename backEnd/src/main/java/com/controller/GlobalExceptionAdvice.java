package com.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "输入数据错误，请重新输入");
        map.put("code","200");
        return map;
    }

}
