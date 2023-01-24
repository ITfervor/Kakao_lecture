package com.kakao.review.aop;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Log4j2
public class MeasuringInterceptor implements HandlerInterceptor {
    //Controller에게 요청을 하기 전에 호출되는 메서드
    //false로 리턴하면 Controller에게 요청을 전달하지 않음
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.warn("Contoller가 요청을 처리하기전에 호출");

        return true;
    }


    @Override
    //컨트롤러가 요청을 처리한 후 호출되는 메서드

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {

        //로그 기록
        log.warn("요청을 정상적으로 처리 한 후 호출");

    }

    @Override
    //Controller가 요청을 처리한 후 무조건 호출되는 메서드
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        log.warn("비정상적으로 처리되도 호츌");

    }
}
