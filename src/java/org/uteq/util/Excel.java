/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.uteq.bean.CantLetras;
import org.uteq.model.Estudiante;

/**
 *
 * @author Moises
 */
public final class Excel {

    List<String> encabezados;

    public Excel() {
        encabezados = encabezados();
    }

    public String generarExcel(List<Estudiante> lista, String tipo) {
        FileOutputStream archivo = null;
        String rutaArchivo = null;
        String titulo = "";
        if(tipo.equals("1")){
            titulo = "Examen Complexivo";
        }
        else if(tipo.equals("2")){
            titulo = "Examen Complexivo de Gracia";
        }
        try {
            double notaFinal = 0;
//            String rutaArchivo = SessionUtil.getRuta() + "reporteExcel.xls";
            /*Se crea el objeto de tipo File con la ruta del archivo*/
//            File temporal = File.createTempFile("reporte", ".xls");
            File archivoXLS = File.createTempFile("reporte-" + lista.get(0).getIdTitulo().getTitulo(), ".xls");
            rutaArchivo = archivoXLS.getAbsolutePath();
            /*Si el archivo existe se elimina*/
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            /*Se crea el archivo*/
            archivoXLS.createNewFile();
            /*Se crea el libro de excel usando el objeto de tipo Workbook*/
            Workbook libro = new HSSFWorkbook();
            /*Se inicializa el flujo de datos con el archivo xls*/
            archivo = new FileOutputStream(archivoXLS);
            /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
            Sheet hoja = libro.createSheet("Reporte");


            /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
            Row fila = hoja.createRow(3);

            Font font = libro.createFont();
            font.setBold(true);
            font.setFontName("CALIBRI");
            font.setFontHeight((short) (16 * 20));

            Font fontheader = libro.createFont();
            fontheader.setBold(true);
            fontheader.setFontName("CALIBRI");
            fontheader.setFontHeight((short) (11 * 20));

            Font fontcontenido = libro.createFont();
            fontcontenido.setBold(false);
            fontcontenido.setFontName("CALIBRI");
            fontcontenido.setFontHeight((short) (11 * 20));

            hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            hoja.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
            hoja.addMergedRegion(new CellRangeAddress(2, 2, 0, 6));

            CellStyle style1 = libro.createCellStyle();
            style1.setAlignment(HorizontalAlignment.CENTER);
            hoja.createRow(0).createCell(0).setCellValue(lista.get(0).getIdTitulo().getIdCarrera().getIdFacultad().getFacultad().toUpperCase());
            hoja.createRow(1).createCell(0).setCellValue(lista.get(0).getIdTitulo().getIdCarrera().getCarrera().toUpperCase());
            hoja.createRow(2).createCell(0).setCellValue("Calificaciones de " + titulo);
            hoja.getRow(0).getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
            hoja.getRow(0).getCell(0).getCellStyle().setFont(font);
            hoja.getRow(1).getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
            hoja.getRow(1).getCell(0).getCellStyle().setFont(font);
            hoja.getRow(2).getCell(0).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
            hoja.getRow(2).getCell(0).getCellStyle().setFont(font);
            CellStyle style;
            style = libro.createCellStyle();
            style.setFont(fontheader);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);

            CellStyle style4;
            style4 = libro.createCellStyle();
            style4.setFont(fontheader);
            style4.setBorderTop(BorderStyle.THIN);
            style4.setBorderBottom(BorderStyle.THIN);
            style4.setBorderRight(BorderStyle.THIN);
            style4.setBorderLeft(BorderStyle.THIN);

            for (int c = 0; c < encabezados.size(); c++) {
                /*Creamos la celda a partir de la fila actual*/
                Cell celda = fila.createCell(c);

                /*Si la fila es la número 0, estableceremos los encabezados*/
                celda.setCellValue(encabezados.get(c));
                if (c > 1 && c < 5) {

                    style.setWrapText(true);
                    celda.setCellStyle(style);
                } else {
                    style4.setWrapText(false);
                    celda.setCellStyle(style4);
                }
            }
            hoja.setAutoFilter(new CellRangeAddress(fila.getRowNum(), fila.getRowNum(), fila.getCell(0).getColumnIndex(), fila.getCell(6).getColumnIndex()));

            CellStyle style2;
            style2 = libro.createCellStyle();
            style2.setFont(fontcontenido);
            style2.setBorderTop(BorderStyle.THIN);
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBorderRight(BorderStyle.THIN);
            style2.setBorderLeft(BorderStyle.THIN);
            for (int f = 4; f < lista.size() + 4; f++) {
                Estudiante e = lista.get(f - 4);
                /*La clase Row nos permitirá crear las filas*/
                fila = hoja.createRow(f);
                Cell celda = fila.createCell(0);
                celda.setCellValue(e.getCedula());
                celda.setCellStyle(style2);
                celda = fila.createCell(1);
                celda.setCellValue(e.getApellidos() + " " + e.getNombres());
                celda.setCellStyle(style2);

                if (tipo.equals("1")) {
                    notaFinal = e.getActaGradoList().get(0).getNotaComplexivoGeneral() + e.getActaGradoList().get(0).getNotaComplexivoProfesional();
                    notaFinal = Math.round(notaFinal * 100);
                    notaFinal = notaFinal / 100;
                    if (notaFinal != 0) {
                        celda = fila.createCell(2);
                        celda.setCellValue(e.getActaGradoList().get(0).getNotaComplexivoGeneral() * 10);
                        celda.setCellStyle(style2);
                        celda = fila.createCell(3);
                        celda.setCellValue(e.getActaGradoList().get(0).getNotaComplexivoProfesional() * 10);
                        celda.setCellStyle(style2);
                        celda = fila.createCell(4);
                        celda.setCellValue(notaFinal);
                        celda.setCellStyle(style2);
                    } else {
                        celda = fila.createCell(2);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                        celda = fila.createCell(3);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                        celda = fila.createCell(4);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                    }
                } else if (tipo.equals("2")) {
                    notaFinal = e.getActaGradoList().get(0).getNotaGraciaGeneral() + e.getActaGradoList().get(0).getNotaGraciaProfesional();
                    notaFinal = Math.round(notaFinal * 100);
                    notaFinal = notaFinal / 100;
                    if (notaFinal != 0) {
                        celda = fila.createCell(2);
                        celda.setCellValue(e.getActaGradoList().get(0).getNotaGraciaGeneral() * 10);
                        celda.setCellStyle(style2);
                        celda = fila.createCell(3);
                        celda.setCellValue(e.getActaGradoList().get(0).getNotaGraciaProfesional() * 10);
                        celda.setCellStyle(style2);
                        celda = fila.createCell(4);
                        celda.setCellValue(notaFinal);
                        celda.setCellStyle(style2);
                    } else {
                        celda = fila.createCell(2);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                        celda = fila.createCell(3);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                        celda = fila.createCell(4);
                        celda.setCellValue(" ");
                        celda.setCellStyle(style2);
                    }
                }
                celda = fila.createCell(5);
                if (notaFinal != 0) {
                    celda.setCellValue(CantLetras.convertirNumerosLetras(notaFinal));
                } else {
                    celda.setCellValue(" ");
                }
                celda.setCellStyle(style2);
                celda = fila.createCell(6);
                celda.setCellValue(estado(notaFinal));
                celda.setCellStyle(style2);

                hoja.autoSizeColumn(0, false);
                hoja.autoSizeColumn(1, false);

                hoja.autoSizeColumn(5, false);
                hoja.autoSizeColumn(6, false);

            }
            PrintSetup ps = hoja.getPrintSetup();
            hoja.setAutobreaks(true);
            ps.setFitHeight((short) 0);
            ps.setFitWidth((short) 1);
            /*Escribimos en el libro*/
            libro.write(archivo);
            /*Cerramos el flujo de datos*/
            archivo.close();
            /*Y abrimos el archivo con la clase Desktop*/
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
            return rutaArchivo;
        }
    }

    public List<String> encabezados() {
        List<String> enc = new ArrayList<>();
        enc.add("Cédula");
        enc.add("Apellidos y Nombres");
        enc.add("Compe. Grales.");
        enc.add("Compe. Específic.");
        enc.add("Calif. Final");
        enc.add("Calificación en Letras");
        enc.add("Resultado");
        return enc;
    }

    public String estado(double nota) {
        if (nota >= 7) {
            return "Aprobado";
        } else if (nota == 0) {
            return "No se presentó";
        } else {
            return "Reprobado";
        }
    }
}
