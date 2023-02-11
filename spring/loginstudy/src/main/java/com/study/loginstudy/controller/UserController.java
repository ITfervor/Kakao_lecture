package com.study.loginstudy.controller;

import com.study.loginstudy.service.UserService;
import com.study.loginstudy.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // 생성자를 자동 생성해준다.
public class UserController {
    @Autowired // 의존관계를 자동으로 설정해준다. Service객체 주입
    UserService userService;

    @GetMapping("/signup") //@RequestMapping(Method=RequestMethod.GET)와 같다
    public String signUpForm(){

        return "signup";
    }

    @PostMapping("/signup") //@RequestMapping(Method=RequestMethod.POST)와 같다
    public String singUp(UserVo userVo){

        userService.joinUser(userVo);
        return "redirect:/login";
    }


}
