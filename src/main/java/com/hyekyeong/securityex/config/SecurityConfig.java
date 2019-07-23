package com.hyekyeong.securityex.config;

import com.hyekyeong.securityex.security.CustomLoginSuccessHandler;
import com.hyekyeong.securityex.security.CustomUserDetailsService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')");

        //로그인 페이지로 이동해서 로그인
//        http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());
        http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login");


        //로그아웃 처리
        http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");

        //자동로그인 설정
        http.rememberMe().key("zerock").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);

    }

    //JDBC를 이용하는 Java 설정
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
//        log.info("configure JDBC.....................................");
//
//        String queryUser = "select userid, userpw, enabled from tbl_member where userid= ? ";
//        String queryDetails = "select userid, auth from tbl_member_auth where userid=? ";
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery(queryUser)
//                .authoritiesByUsernameQuery(queryDetails);
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        log.info("configure.......................................");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//
//        auth.inMemoryAuthentication().withUser("member").password("$2a$10$2tRHnHqH79bCg/M7ZMW0S.PoQLJ9ACNf0d.93IM.YbWzCArwbRFqy").roles("MEMBER");
//    }

    //CustomLoginSuccessHandler 를 java 설정을 이용해서 추가
//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler(){
//        return new CustomLoginSuccessHandler();
//    }

    //BcryptPasswordEncoder를 지정한다
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    // in custom userdetails
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }
}
