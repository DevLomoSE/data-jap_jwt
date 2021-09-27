package com.devlomose.springboot.data.jpa.app.controllers;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.models.entity.Factura;
import com.devlomose.springboot.data.jpa.app.models.entity.Producto;
import com.devlomose.springboot.data.jpa.app.models.service.ClienteServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * FacturaController at: src/main/java/com/devlomose/springboot/data/jpa/app/controllers
 * Created by @DevLomoSE at 22/9/21 12:01.
 */
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private ClienteServiceImplement clienteService;


     @GetMapping("/form/{IdCliente}")
    public String create(@PathVariable(value="IdCliente") Long IdCliente,
                         Map<String, Object> model,
                         RedirectAttributes flash/*,
                         SessionStatus sessionStatus*/) {
        Cliente cliente = clienteService.findById(IdCliente);

        if(cliente == null){
            flash.addFlashAttribute("error", "El cliente solicitado no existe en la BD.");
            return "redirect:/cliente/listado";
        }

         Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", "Crear factura");

        /*sessionStatus.setComplete();*/

        return "facturas/form";
    }

    @GetMapping(value = "/busqueda/productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> loadProducts(@PathVariable String term){
        return clienteService.findByName(term);
    }



}
