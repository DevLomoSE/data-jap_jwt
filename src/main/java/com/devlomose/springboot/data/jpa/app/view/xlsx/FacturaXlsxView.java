package com.devlomose.springboot.data.jpa.app.view.xlsx;

import com.devlomose.springboot.data.jpa.app.controllers.FacturaController;
import com.devlomose.springboot.data.jpa.app.models.entity.Factura;
import com.devlomose.springboot.data.jpa.app.models.entity.ItemFactura;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * FacturaXlsxView at: src/main/java/com/devlomose/springboot/data/jpa/app/view/xlsx
 * Created by @DevLomoSE at 29/9/21 10:34.
 */
@Component("facturas/detalle.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {

        logger.info("=== Building Factura Detalle XLSX ===");

        MessageSourceAccessor messages = getMessageSourceAccessor();

        httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"Detalle Factura\"");
        Factura factura = (Factura) model.get("factura");
        Sheet sheet = workbook.createSheet("Factura");

        // forma 1
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(messages.getMessage("text.factura.datos.cliente"));

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getNombre()+" "+factura.getCliente().getApellido());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(factura.getCliente().getEmail());

        // forma 2
        sheet.createRow(4).createCell(0).setCellValue(messages.getMessage("text.factura.datos.factura"));
        sheet.createRow(5).createCell(0)
                .setCellValue(messages.getMessage("text.factura.datos.factura.folio")+": "+factura.getId());
        sheet.createRow(6).createCell(0)
                .setCellValue(messages.getMessage("text.factura.datos.factura.descripcion")+": "+factura.getDescripcion());
        sheet.createRow(7).createCell(0)
                .setCellValue(messages.getMessage("text.cliente.fecha")+": "+factura.getCreatedAt());

        CellStyle theadstyle = workbook.createCellStyle();
        theadstyle.setBorderBottom(BorderStyle.MEDIUM);
        theadstyle.setBorderTop(BorderStyle.MEDIUM);
        theadstyle.setBorderRight(BorderStyle.MEDIUM);
        theadstyle.setBorderLeft(BorderStyle.MEDIUM);
        theadstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        theadstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodystyle = workbook.createCellStyle();
        tbodystyle.setBorderBottom(BorderStyle.THIN);
        tbodystyle.setBorderTop(BorderStyle.THIN);
        tbodystyle.setBorderRight(BorderStyle.THIN);
        tbodystyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(9);
        header.createCell(0).setCellValue(messages.getMessage("text.factura.tabla.producto"));
        header.createCell(1).setCellValue(messages.getMessage("text.factura.tabla.precio"));
        header.createCell(2).setCellValue(messages.getMessage("text.factura.tabla.cantidad"));
        header.createCell(3).setCellValue(messages.getMessage("text.factura.tabla.total"));
        header.getCell(0).setCellStyle(theadstyle);
        header.getCell(1).setCellStyle(theadstyle);
        header.getCell(2).setCellStyle(theadstyle);
        header.getCell(3).setCellStyle(theadstyle);

        int rowNumber = 10;
        for(ItemFactura item: factura.getItems()){
            Row rowBillData = sheet.createRow(rowNumber ++);
            cell = rowBillData.createCell(0);
            cell.setCellValue(item.getProducto().getNombre());
            cell.setCellStyle(tbodystyle);

            cell = rowBillData.createCell(1);
            cell.setCellValue(item.getProducto().getPrecio());
            cell.setCellStyle(tbodystyle);

            cell = rowBillData.createCell(2);
            cell.setCellValue(item.getCantidad());
            cell.setCellStyle(tbodystyle);

            cell = rowBillData.createCell(3);
            cell.setCellValue(item.calculateImport());
            cell.setCellStyle(tbodystyle);
        }

        Row rowTotal = sheet.createRow(rowNumber);
        cell = rowTotal.createCell(2);
        cell.setCellValue(messages.getMessage("text.factura.tabla.total"));
        cell.setCellStyle(tbodystyle);

        cell = rowTotal.createCell(3);
        cell.setCellValue("$ "+factura.getTotal());
        cell.setCellStyle(tbodystyle);

        logger.info("=== XLSX Detalle factura built ===");
    }
}
