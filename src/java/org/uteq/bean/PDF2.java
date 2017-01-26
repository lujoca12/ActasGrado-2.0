///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.uteq.bean;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.uteq.model.ActaGrado;
//import org.uteq.model.Estudiante;
//import org.uteq.model.Titulo;
////import org.uteq.controlador.ActaGradoJpaController;
////import org.uteq.controlador.EstudianteJpaController;
//
///**
// *
// * @author jacob
// */
//public class PDF2 {
//
//    Document document;
//    String nombreArchivo;
//    Byte[] archivo;
//    String ruta;
//
//    private EntityManagerFactory emf;
//    private EntityManager em;
//    private static final String rutaImg = "\\imagenes";
//    private static final String rutaPdf = "\\recursos";
//    private static final String rutaFuentes = "\\fuentes";
//
//    public PDF2() {
//        this.emf = Persistence.createEntityManagerFactory("TitulacionPU");
//    }
//
//    public void setRuta(String ruta) {
//        this.ruta = ruta;
//    }
//
//    public void setNombreArchivo(String nombreArchivo) {
//        this.nombreArchivo = nombreArchivo;
//    }
//
//    private void crearFuentes() {
//
//    }
//
//    public ByteArrayOutputStream generarPDF(String object) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ActaGradoJpaController agjc = new ActaGradoJpaController(emf);
//        EstudianteJpaController esjc = new EstudianteJpaController(emf);
//        Estudiante e = esjc.findEstudiante(object);
//        String autoridad = e.getIdTitulo().getIdCarrera().getIdFacultad().getDenominacionAutoridad();
//        String genero;
//        String genero2;
//        String genero3;
//        String genero4;
//        String genero5;
//        String genero6;
//        if ("M".equals(e.getGenero())) {
//            genero = "o";
//        } else {
//            genero = "a";
//        }
//        if ("M".equals(e.getGenero())) {
//            genero2 = "del";
//        } else {
//            genero2 = "la";
//        }
//        if ("M".equals(e.getGenero())) {
//            genero3 = "señor";
//        } else {
//            genero3 = "señorita";
//        }
//        if ("M".equals(e.getGenero())) {
//            genero4 = "el";
//        } else {
//            genero4 = "la";
//        }
//        if ("M".equals(e.getGenero())) {
//            genero5 = "lo";
//        } else {
//            genero5 = "la";
//        }
//        if ("M".equals(e.getGenero())) {
//            genero6 = "al";
//        } else {
//            genero6 = "la";
//        }
//        document = new Document(PageSize.A4, 75, 65, 50, 50);
//        try {
//            PdfWriter.getInstance(document, baos);
//            FileOutputStream fileOutputStream = new FileOutputStream(ruta + rutaPdf + nombreArchivo);
//            PdfWriter.getInstance(document, fileOutputStream);
//            document.open();
//            Image image = null;
//            image = Image.getInstance(ruta + rutaImg + "\\logoUTEQ.png");
//            image.scaleAbsolute(50f, 50f);
//            image.setAlignment(Image.ALIGN_LEFT);
//            BaseFont baseFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial Narrow.ttf", BaseFont.WINANSI, true);
//            BaseFont subFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial.ttf", BaseFont.WINANSI, true);
//            Font fontContenido = new Font(baseFont, 11, Font.NORMAL, BaseColor.BLACK);
//            Font fontTitulos = FontFactory.getFont(
//                    FontFactory.TIMES_ROMAN, 16, Font.BOLDITALIC,
//                    BaseColor.BLACK);
//            Font fontSub = new Font(subFont, 11, Font.BOLD, BaseColor.BLACK);
//
//            //    Creacion de una tabla
//            PdfPTable table = new PdfPTable(3);
//            table.setWidthPercentage(100);
//            table.setSpacingBefore(0f);
//            table.setSpacingAfter(0f);
//            //Agregar la imagen anterior a una celda de la tabla
//            PdfPCell cell = new PdfPCell(image);
//            //Propiedades de la celda
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//            //Agregar la celda a la tabla
//            table.addCell(cell);
//
//            PdfPTable table1 = new PdfPTable(1);
//            table1.setWidthPercentage(100);
//            table1.setSpacingBefore(0f);
//            table1.setSpacingAfter(0f);
//
//            Paragraph paragraph = new Paragraph();
//            paragraph.add(new Phrase("Universidad Técnica Estatal de Quevedo \n", fontTitulos));
//            paragraph.setFont(fontTitulos);
//            PdfPCell cell1 = new PdfPCell(paragraph);
//            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setBorder(PdfPCell.NO_BORDER);
//            table1.addCell(cell1);
//
//            paragraph = new Paragraph();
//            paragraph.add(new Phrase(e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad() + " \n \n ACTA DE GRADO \n MODALIDAD PROYECTO DE INVESTIGACIÓN", fontSub));
//            paragraph.setFont(fontSub);
//            cell1 = new PdfPCell(paragraph);
//            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setBorder(PdfPCell.NO_BORDER);
//            table1.addCell(cell1);
//
//            cell = new PdfPCell(table1);
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("\n \n \n No." + e.getActaGradoList().get(0).getNumeroActaGrado() + "", fontSub));
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//
//            table.addCell(cell);
//            // Agregar la tabla al documento
//            float[] columnWidths = new float[]{10f, 60f, 10f};
//            table.setWidths(columnWidths);
//            document.add(table);
//
//            //  Creacion del parrafo
//            paragraph = new Paragraph();
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            //Agregar un titulo con su respectiva fuente
//
//// Agregar saltos de linea
//// Agregar contenido con su respectiva fuente
//            paragraph
//                    .add(new Phrase(
//                            "En la ciudad de Quevedo, provincia de Los Ríos a las " + e.getActaGradoList().get(0).getFechaIngresoActa() + " del " + e.getActaGradoList().get(0).getFechaIngresoActa() + " de " + e.getActaGradoList().get(0).getFechaIngresoActa() + "  del " + e.getActaGradoList().get(0).getFechaIngresoActa() + ", en la sala " + e.getActaGradoList().get(0).getFechaIngresoActa() + ", siendo el día y la hora fijados mediante disposición administrativa del " + autoridad.toUpperCase() + ", acatando lo dispuesto en el Art. 50 del Reglamento de la Unidad de Titulación Especial de la Universidad Técnica Estatal de Quevedo, se instala " + e.getActaGradoList().get(0).getNombreAutoridad().toUpperCase() + ", " + autoridad.toUpperCase() + " y " + e.getActaGradoList().get(0).getNombreSecretario().toUpperCase() + ", Secretario Abogado de la Unidad de Admisión y Registro y el Graduad" + genero + ", para elaborar la presente acta de Grado " + genero2 + " " + genero3 + " " + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", previo a la obtención del título de: " + e.getIdTitulo().getTitulo().toUpperCase() + ". \n \n"
//                            + "Luego de cumplir con lo dispuesto en los artículos 50 al 55 del Reglamento de la Unidad de Titulación Especial de la Universidad Técnica Estatal de Quevedo, " + genero4 + " " + genero3 + " " + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", de conformidad con el pronunciamiento dado por los señores Miembros del Tribunal de Proyecto de Investigación, ha obtenido la calificación promedio definitiva de Sustentación de: " + e.getActaGradoList().get(0).getNotaTrabajoInvestigación() + " y, el promedio de calificaciones obtenidas durante los periodos de estudio, emitidos por el responsable de la Unidad de Admisión y Registro, es de: " + e.getActaGradoList().get(0).getPromedioEstudios() + ". Conforme lo dispone el Art. 56 del Reglamento de la Unidad de Titulación Especial de la Universidad Técnica Estatal de Quevedo, se establece como nota de grado " + genero2 + " " + genero3 + " " + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", la calificación de " + (e.getActaGradoList().get(0).getNotaGraciaGeneral() + e.getActaGradoList().get(0).getPromedioEstudios()) / 2 + ". \n \n"
//                            + "En consecuencia se " + genero5 + " proclama apt" + genero + " " + genero6 + " " + genero3 + " " + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + " para ser investid" + genero + " como " + e.getIdTitulo().getTitulo().toUpperCase() + ". La ceremonia de incorporación y entrega del título, se efectuará en un acto solemne programado por la " + e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad().toUpperCase() + ".\n \n"
//                            + "En virtud de lo actuado, firman la presente acta el señor Decano, el Graduado y la suscrita Secretaria Abogada de la Unidad de Admisión y Registro, que certifica.",
//                            fontContenido));
//            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            //Agregar el parrafo al documento
//            document.add(paragraph);
//            //Crear tabla nueva con dos posiciones
//            table = new PdfPTable(2);
//            float[] longitudes = {5.0f, 5.0f};
//            table.setWidthPercentage(100);
//            table.setSpacingBefore(0f);
//            table.setSpacingAfter(0f);
//            table.setWidths(longitudes);
//
//            fontContenido = new Font(baseFont, 11, Font.BOLD, BaseColor.BLACK);
//            cell = new PdfPCell(new Phrase("___________________________ \n" + e.getActaGradoList().get(0).getNombreAutoridad().toUpperCase() + "\n"
//                    + "DECANO (A)/DIRECTOR (A)", fontContenido));
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("___________________________ \n" + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + "\n"
//                    + "GRADUAD" + genero.toUpperCase() + "", fontContenido));
//            cell.setBorder(PdfPCell.NO_BORDER);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//            document.add(table);
//
//            paragraph = new Paragraph(new Phrase("\n \n LO CERTIFICO.-", fontContenido));
//            paragraph.add(new Phrase(Chunk.NEWLINE));
//            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
//            document.add(paragraph);
//
//            paragraph = new Paragraph(new Phrase("___________________________ \n" + e.getActaGradoList().get(0).getNombreSecretario().toUpperCase() + "\n"
//                    + "SECRETARIO/A ABOGADO/A U.A.R.", fontContenido));
//            paragraph.setAlignment(Element.ALIGN_CENTER);
//            document.add(paragraph);
//
////Cerrar el documento
//            document.close();
//
//        } catch (Exception ex) {
//            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            return baos;
//        }
//    }
//}
