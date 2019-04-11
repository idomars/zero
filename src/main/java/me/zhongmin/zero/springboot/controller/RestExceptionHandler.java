package me.zhongmin.zero.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 统一异常的处理
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus
    public void runtimeExceptionHandler(Throwable e){
        log.error(e.getMessage(),e);
    }
}
