package com.devlomose.springboot.data.jpa.app.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * LoginController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers/auth
 * Created by @DevLomoSE at 27/9/21 17:50.
 */
@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(name="error", required = false) String error,
                        Model model, Principal principal, RedirectAttributes flash){

        if(principal != null){
            flash.addFlashAttribute("info", "Ya tiene una sesion activa.");
            return "redirect:/cliente/listado";
        }

        if(error != null){
            model.addAttribute("error", "Credenciales incorrectas!");
        }

        return "auth/login";
    }

}
