package com.devlomose.springboot.data.jpa.app.controllers;

import com.devlomose.springboot.data.jpa.app.models.dao.ClienteDAO;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClienteController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers
 * Created by @DevLomoSE at 14/9/21 10:46.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteDAOJPA")
    private ClienteDAO clienteDAO;

    @GetMapping("/listado")
    public String list(Model model){
        model.addAttribute("titulo", "Listado Cliente");
        model.addAttribute("clientes", clienteDAO.findAll());
        return "clientes/listar";
    }

}
