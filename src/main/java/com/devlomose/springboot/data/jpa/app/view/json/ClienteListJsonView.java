package com.devlomose.springboot.data.jpa.app.view.json;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import com.devlomose.springboot.data.jpa.app.view.xml.ClienteList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * ClienteListJsonView at: src/main/java/com/devlomose/springboot/data/jpa/app/view/json
 * Created by @DevLomoSE at 29/9/21 15:49.
 */
@Component("clientes/listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView {

    @Override
    protected Object filterModel(Map<String, Object> model) {
        logger.info("=== Building Clientes listado JSON ===");

        model.remove("titulo");
        model.remove("page");

        Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
        model.remove("clientes");
        model.put("clientes", clientes.getContent());

        logger.info("=== JSON Clientes listado built ===");
        return super.filterModel(model);
    }
}
