package com.devlomose.springboot.data.jpa.app.auth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginSuccessHandler at: src/main/java/com/devlomose/springboot/data/jpa/app/authHandler
 * Created by @DevLomoSE at 27/9/21 19:30.
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();

        FlashMap flashMap = new FlashMap();
        flashMap.put("success", authentication.getName()+", sesión iniciada con éxito!");

        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        if(authentication != null){
            logger.info("Usuario "+authentication.getName()+" se ha loggeado con éxito");
        }

        //super.onAuthenticationSuccess(request, response, authentication);
        response.sendRedirect("/cliente/listado");

    }
}
