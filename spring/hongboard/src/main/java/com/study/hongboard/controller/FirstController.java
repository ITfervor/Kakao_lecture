package com.study.hongboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String sayHi(Model model){
        model.addAttribute("username", "정엽" );
        return "greeting";
    }

    @GetMapping("/bye")
    public String sayGoodye(Model model){
        model.addAttribute("nickname","정엽");
        return "byebye";
    }
}
