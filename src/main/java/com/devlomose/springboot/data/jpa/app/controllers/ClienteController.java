package com.devlomose.springboot.data.jpa.app.controllers;

import com.devlomose.springboot.data.jpa.app.models.dao.ClienteDAO;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.models.service.ClienteService;
import com.devlomose.springboot.data.jpa.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/ver/{id}")
    public String getDetalle(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Cliente cliente = clienteService.findById(id);
        if(cliente == null){
            flash.addFlashAttribute("error", "Cliente no existe en la BD");
            return "redirect:/cliente/listado";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Detalle del cliente: " + cliente.getNombre());
        return "ver";
    }

    @GetMapping("/listado")
    public String list(@RequestParam(name="page", defaultValue="0") int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Cliente> clientes =  clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/cliente/listado", clientes);

        model.addAttribute("titulo", "Listado Cliente");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
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
                              @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
                              SessionStatus sessionStatus){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Crear Cliente");
            return "clientes/form";
        }

        if(!foto.isEmpty()){
            Path uploadsDirectory = Paths.get("src/main/resources/static/uploads");
            String rootPath = uploadsDirectory.toFile().getAbsolutePath();
            try {
                byte[] bytes = foto.getBytes();
                Path fullPath = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(fullPath, bytes);
                flash.addFlashAttribute("info", " Has subido correctamente '" +
                                                    foto.getOriginalFilename() +
                                                    "'");
                cliente.setFoto(foto.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
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
