package com.kakao.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String password ="1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw:" + enPw);
        enPw = passwordEncoder.encode(password);
        System.out.println("ewPw:" + enPw);

        boolean result = passwordEncoder.matches(password, enPw);// 비교하는 문장 앞에는 암호화 뒤애는 평문
        System.out.println("result: "+ result);
    }
}
