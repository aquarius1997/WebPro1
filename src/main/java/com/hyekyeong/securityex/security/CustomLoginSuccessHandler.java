package com.hyekyeong.securityex.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

        log.warn("Login Success");

        //로그인한 사용자에 부여된 권한 Authentication 개겣를 이용해서 사용자가 가진 모든 권한을 문자열로 체크
        List<String> roleNames = new ArrayList<>();
        auth.getAuthorities().forEach(authority -> {roleNames.add(authority.getAuthority());
        });

        log.warn("ROLE NAMES: " + roleNames);

        //권한에 따라 리다이렉트
        if(roleNames.contains("ROLE_ADMIN")){
            response.sendRedirect("/sample/admin");
            return;
        }
        if(roleNames.contains("ROLE_MEMBER")){
            response.sendRedirect("/sample/member");
            return;
        }

        response.sendRedirect("/");
    }
}
