package com.kakao.review2.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadTestController {
    @GetMapping("/uploadajax")
    public void uploadAjax(){
        System.out.println("222");
    }
}
