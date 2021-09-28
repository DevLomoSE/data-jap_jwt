package com.devlomose.springboot.data.jpa.app.controllers.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/login")
    public String login(@RequestParam(name="error", required = false) String error,
                        @RequestParam(name="logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash){

        logger.info("=== LOGIN ===");

        logger.info("principal "+principal);
        if(principal != null){
            flash.addFlashAttribute("info", "Ya tiene una sesión activa.");
            return "redirect:/cliente/listado";
        }

        logger.info("logout "+logout);
        if(logout != null){
            model.addAttribute("success", "Se ha cerrado la sesion con exito.");
        }

        logger.info("error "+error);
        if(error != null){
            model.addAttribute("error", "Credenciales incorrectas!");
        }

        return "auth/login";
    }

}
