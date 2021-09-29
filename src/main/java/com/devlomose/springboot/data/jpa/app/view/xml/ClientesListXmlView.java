package com.devlomose.springboot.data.jpa.app.view.xml;

import com.devlomose.springboot.data.jpa.app.controllers.FacturaController;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ClientesXmlView at: src/main/java/com/devlomose/springboot/data/jpa/app/view/xml
 * Created by @DevLomoSE at 29/9/21 13:49.
 */
@Component("clientes/listar.xml")
public class ClientesListXmlView extends MarshallingView {

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    public ClientesListXmlView(Jaxb2Marshaller marshaller) {
        super(marshaller);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        logger.info("=== Building Clientes listado xml ===");

        model.remove("titulo");
        model.remove("page");

        Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");

        model.remove("clientes");

        model.put("clientes", new ClienteList(clientes.getContent()));

        super.renderMergedOutputModel(model, request, response);
        logger.info("=== xml Clientes listado built ===");
    }
}
