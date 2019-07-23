package com.hyekyeong.securityex.security;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class PasswordEncoderTests {

    @Setter(onMethod_ = {@Autowired})
    private PasswordEncoder pwEncoder;

    @Test
    public void testEncode(){

        String str = "member";
        String enStr = pwEncoder.encode(str);

        log.info(enStr);
    }
}
