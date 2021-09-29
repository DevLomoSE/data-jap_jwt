package com.devlomose.springboot.data.jpa.app.view.csv;

import com.devlomose.springboot.data.jpa.app.controllers.FacturaController;
import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ClienteCsvView at: src/main/java/com/devlomose/springboot/data/jpa/app/view/csv
 * Created by @DevLomoSE at 29/9/21 12:14.
 */
@Component("clientes/listar.csv")
public class ClienteCsvView extends AbstractView {

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    public ClienteCsvView() {
        setContentType("text/csv");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        logger.info("=== Building listado clientes CSV ===");

        httpServletResponse.setHeader("Content-Disposition", "attachement; filename=\"Clientes\"");
        httpServletResponse.setContentType(getContentType());

        Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");

        ICsvBeanWriter beanWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
                                                        CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"id","nombre","apellido","email","createdAt"};
        beanWriter.writeHeader(header);

        for(Cliente cliente: clientes){
            beanWriter.write(cliente, header);
        }

        beanWriter.close();

        logger.info("=== CSV listado clientes built ===");
    }
}
