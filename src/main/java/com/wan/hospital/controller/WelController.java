package com.wan.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelController {

    @GetMapping("/welcome")
    public String getInformation(Model model, HttpServletRequest httpServletRequest) {

        return "welcome";
    }
}
