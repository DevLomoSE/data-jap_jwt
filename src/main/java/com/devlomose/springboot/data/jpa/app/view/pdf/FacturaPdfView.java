package com.devlomose.springboot.data.jpa.app.view.pdf;

import com.devlomose.springboot.data.jpa.app.controllers.FacturaController;
import com.devlomose.springboot.data.jpa.app.models.entity.Factura;
import com.devlomose.springboot.data.jpa.app.models.entity.ItemFactura;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Locale;
import java.util.Map;

/**
 * FacturaPdfView at: src/main/java/com/devlomose/springboot/data/jpa/app/view/pdf
 * Created by @DevLomoSE at 28/9/21 16:09.
 */
@Component("facturas/detalle")
public class FacturaPdfView extends AbstractPdfView {

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter pdfWriter, HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse)
            throws Exception {

        logger.info("=== Building Factura Detalle pdf ===");

        Locale locale = localeResolver.resolveLocale(httpServletRequest);

        PdfPCell cell = null;
        Factura factura = (Factura) model.get("factura");

        PdfPTable tabla = new PdfPTable(1);
        cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.datos.cliente",null, locale)));
        cell.setBackgroundColor(new Color(226,227,229));
        cell.setPadding(8f);
        tabla.setSpacingAfter(20);
        tabla.addCell(cell);
        tabla.addCell(factura.getCliente().getNombre()+" "+factura.getCliente().getApellido());
        tabla.addCell(factura.getCliente().getEmail());

        PdfPTable tabla2 =new PdfPTable(1);
        cell = new PdfPCell(new Phrase("Datos de la factura"));
        cell.setBackgroundColor(new Color(226,227,229));
        cell.setPadding(8f);
        tabla2.setSpacingAfter(20);
        tabla2.addCell(cell);
        tabla2.addCell(messageSource.getMessage("text.factura.datos.factura.folio",null, locale)
                            +": "+factura.getId());
        tabla2.addCell(messageSource.getMessage("text.factura.datos.factura.descripcion",null, locale)
                            +": "+factura.getDescripcion());
        tabla2.addCell(messageSource.getMessage("text.cliente.fecha",null, locale)
                            +": "+factura.getCreatedAt());

        document.add(tabla);
        document.add(tabla2);

        PdfPTable tabla3 = new PdfPTable(4);
        tabla3.setWidths(new float[] {3,1,1,1});
        tabla3.addCell(messageSource.getMessage("text.factura.tabla.producto",null, locale));
        tabla3.addCell(messageSource.getMessage("text.factura.tabla.precio",null, locale));
        tabla3.addCell(messageSource.getMessage("text.factura.tabla.cantidad",null, locale));
        tabla3.addCell(messageSource.getMessage("text.factura.tabla.total",null, locale));

        for(ItemFactura item: factura.getItems()){
            tabla3.addCell(item.getProducto().getNombre());

            cell = new PdfPCell(new Phrase("$ "+item.getProducto().getPrecio().toString()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase("$ "+item.calculateImport().toString()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            tabla3.addCell(cell);
        }

        cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.tabla.total",null, locale)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        tabla3.addCell(cell);

        cell = new PdfPCell(new Phrase("$ "+factura.getTotal().toString()));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        tabla3.addCell(cell);

        document.add(tabla3);

        logger.info("=== PDF Detalle factura built ===");
    }
}
