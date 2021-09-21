package com.devlomose.springboot.data.jpa.app.controllers;

import com.devlomose.springboot.data.jpa.app.models.dao.ClienteDAO;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.models.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

/**
 * ClienteController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers
 * Created by @DevLomoSE at 14/9/21 10:46.
 */
@Controller
@RequestMapping("/cliente")
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listado")
    public String list(Model model){
        model.addAttribute("titulo", "Listado Cliente");
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes/listar";
    }

    @GetMapping("/form")
    public String createForm(Map<String, Object> model){
        Cliente cliente = new Cliente();

        model.put("titulo", "Crear Cliente");
        model.put("cliente", cliente);

        return "clientes/form";

    }

    @PostMapping("/form")
    public String saveCliente(@Valid Cliente cliente, BindingResult result, Model model,
                              RedirectAttributes flash, SessionStatus sessionStatus){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Crear Cliente");
            return "clientes/form";
        }

        String flashMessage = (cliente.getId() != null)
                                ? "Cliente modificado exitosamente"
                                : "Cliente creado exitosamente";

        clienteService.save(cliente);
        sessionStatus.setComplete();
        flash.addFlashAttribute("success", flashMessage);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/form/{id}")
    public String editClient(@PathVariable(value="id") Long id, Map<String, Object> model,
                            RedirectAttributes flash){
        Cliente cliente = null;
        if(id > 0){
            cliente = clienteService.findById(id);
            if(cliente == null){
                flash.addFlashAttribute("error", "El cliente no se encuentra en la BD");
                return "redirect:clientes/listado";
            }
        }else{
            flash.addFlashAttribute("error", "Error al buscar el cliente");
            return "redirect:clientes/listado";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");
        return "clientes/form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteClient(@PathVariable(value="id") Long id, RedirectAttributes flash){
        if(id > 0){
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado exitosamente");
        }
        return "redirect:/cliente/listado";
    }

}
