package com.devlomose.springboot.data.jpa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * LocaleController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers
 * Created by @DevLomoSE at 28/9/21 15:07.
 */
@Controller
public class LocaleController {

    @GetMapping("/locale")
    public String locale(HttpServletRequest request){
        String lastURL = request.getHeader("referer");

        return "redirect:".concat(lastURL);
    }
}
