package com.hyekyeong.securityex.exception;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.lang.Exception;

//예외 처리를 목적으로 하는 클래스
@ControllerAdvice
public class CommonExceptionAdvice {

    //log 관련
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model){

        logger.error("Exception......." + ex.getMessage());
        model.addAttribute("exception", ex);
        //logger.error(model);
        return "error_page";
    }
}
