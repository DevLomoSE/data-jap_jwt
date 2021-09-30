package com.devlomose.springboot.data.jpa.app.controllers.rest;

import com.devlomose.springboot.data.jpa.app.models.service.ClienteService;
import com.devlomose.springboot.data.jpa.app.view.xml.ClienteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClienteRestController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers/rest
 * Created by @DevLomoSE at 29/9/21 17:54.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listado")
    @Secured("ROLE_ADMIN")
    public ClienteList list(){
        return new ClienteList(clienteService.findAll());
    }
}
