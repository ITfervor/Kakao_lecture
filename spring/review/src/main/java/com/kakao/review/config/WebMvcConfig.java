package com.kakao.review.config;

import com.kakao.review.aop.MeasuringInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;



import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//웹 설정 클래스
@Configuration//설정인것을 알려줌
@Log4j2
public class WebMvcConfig implements WebMvcConfigurer {
    //인터셉터 설정 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MeasuringInterceptor()) //인터셉트 연결
            .addPathPatterns("/user"); //인터셉터가 적용될 URL
    }
}
