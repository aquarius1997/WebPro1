package com.hyekyeong.securityex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class CommonController {

    // 사용자에게 에러 메세지를 보여준다
    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model){

        log.info("access Denied : " + auth);

        model.addAttribute("msg", "Access Denied");

    }

    //커스텀 로그인 페이지
    @GetMapping("/customLogin")
    public void loginInput(String error, String logout, Model model){

        log.info("error : " + error);
        log.info("logout : " + logout);

        //에러 발생시
        if(error != null){
            model.addAttribute("error", "Login Error Check Your Account");
        }

        //로그아웃을 시도하려고 할 때
        if(logout != null){
            model.addAttribute("logout", "Logout!!!");
        }

    }

    //Get방식으로 로그아웃을 결정하는 페이지
    @GetMapping("/customLogout")
    public void logoutGET(){
        log.info("custom logout");
    }

    //POST 방식으로 실제 로그아웃이 실행되는 부분
    @PostMapping("/customLogout")
    public void logoutPost(){
        log.info("post custom logout");
    }
}
