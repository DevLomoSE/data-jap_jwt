package com.devlomose.springboot.data.jpa.app.controllers;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.models.entity.Factura;
import com.devlomose.springboot.data.jpa.app.models.entity.ItemFactura;
import com.devlomose.springboot.data.jpa.app.models.entity.Producto;
import com.devlomose.springboot.data.jpa.app.models.service.ClienteServiceImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
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

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

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

    @GetMapping(value = "/busqueda/productos", produces = {"application/json"})
    public @ResponseBody List<Producto> loadProducts(@RequestParam(name = "producto") String term){
        return clienteService.findByName(term);
    }

    @PostMapping("/form")
    public String saveBill(@Valid Factura factura,
                           BindingResult result,
                           Model model,
                           @RequestParam(name="item_id[]", required = false) Long[] itemId,
                           @RequestParam(name="cantidad[]", required = false) Integer[] cantidad,
                           RedirectAttributes flash,
                           SessionStatus status){

         if(result.hasErrors()){
             model.addAttribute("titulo", "Crear Factura");
             return "facturas/form";
         }

         if(itemId == null || itemId.length == 0){
             model.addAttribute("title", "Crear Factura");
             model.addAttribute("error", "Error: La factura debe tener elementos relacionados");
             return "facturas/form";
         }

         for(int i=0; i<itemId.length; i++){
             Producto producto = clienteService.findProductoById(itemId[i]);

             ItemFactura itemFactura = new ItemFactura();
             itemFactura.setCantidad(cantidad[i]);
             itemFactura.setProducto(producto);

             factura.addItemFactura(itemFactura);

             logger.debug("ID: "+itemId[i].toString() + ", cantidad: "+cantidad[i].toString());
         }

         clienteService.saveFactura(factura);

         status.setComplete();

         flash.addFlashAttribute("success", "Factura guardad con éxito");

         return "redirect:/cliente/ver/"+factura.getCliente().getId();
    }

}
