package org.uteq.bean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.uteq.test.LazyEstudianteModel;
import org.uteq.model.ActaGrado;
import org.uteq.model.Estudiante;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.uteq.model.DenominacionTitulo;
import org.uteq.model.ExamenComplexivo;
import org.uteq.model.Facultad;
import org.uteq.model.Titulo;
import org.uteq.sesion.ActaGradoFacadeLocal;
import org.uteq.sesion.DenominacionTituloFacadeLocal;
import org.uteq.sesion.EstudianteFacadeLocal;
import org.uteq.sesion.ExamenComplexivoFacadeLocal;
import org.uteq.sesion.FacultadFacadeLocal;
import org.uteq.sesion.TipoTitulacionFacadeLocal;
import org.uteq.sesion.TituloFacadeLocal;
import org.uteq.util.SessionUtil;

/**
 *
 * @author Moises
 */
@ViewScoped
@Named
public class EstudianteMb implements Serializable {

    @EJB
    private TituloFacadeLocal tfl;
    @EJB
    private TipoTitulacionFacadeLocal ttfl;
    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    private ActaGradoFacadeLocal agfl;
    @EJB
    private FacultadFacadeLocal ffl;
    @EJB
    private ExamenComplexivoFacadeLocal ecfl;
    @EJB
    private DenominacionTituloFacadeLocal dtfl;

    private LazyDataModel<Estudiante> list;
    private int IdTitulo;
    private int IdTipoTitulacion;
    private String rutaF;
    private UploadedFile file;
    private List<Estudiante> listEstudiantes;
    private Facultad object;
    private Date hora;
    private Date fecha;
    private Titulo titulo;
    private String stringTipotit;
    private String secuencial;
    private String ban;
    private boolean ban1;
    private List<Estudiante> listFilter;
    private String mensaje;
    private Integer periodo;
    private String denominacion;
    private String secuencia;
    private Facultad facultad;
    private String autoridad;
    private String secretario;

    private Integer selectedFacultad;
    private Facultad facuSelect;
    private List<SelectItem> facultadL;

    private Document document;
    private String nombreArchivo;
    private Byte[] archivo;
    private String ruta;
    private String numeroActa;
    private static final String rutaImg = "\\imagenes";
    private static final String rutaPdf = "\\recursos";
    private static final String rutaFuentes = "\\fuentes";
    private StreamedContent media;

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }
    
    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Titulo getTitulo() {
        if (IdTitulo != 0) {
            titulo = tfl.find(IdTitulo);
            return titulo;
        } else {
            return titulo = null;
        }

    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public void guardarSecuencial() {
        if (!secuencia.equals("") || secuencia != null) {
            facultad = SessionUtil.getUsuario().getIdFacultad();
            facultad.setSecuencialActa(secuencia);
            ffl.edit(facultad);
        }
    }

    public void guardarPeriodo() {
        if (!denominacion.equals("") || denominacion != null) {
            ExamenComplexivo ex = new ExamenComplexivo();
            ex.setDenominacionExC(denominacion);
            ecfl.create(ex);
        }
    }

    public Date getFecha() {
        if (selectedEstudiante != null) {
            fecha = selectedEstudiante.getActaGradoList().get(0).getFechaIngresoActa();
        }
        return fecha;
    }

    public String getStringTipotit() {
        if (IdTipoTitulacion != 0) {
            return ttfl.find(IdTipoTitulacion).getTipoTitulacion();
        } else {
            return "";
        }
    }

    public void setStringTipotit(String stringTipotit) {
        this.stringTipotit = stringTipotit;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        if (selectedEstudiante != null) {
            hora = selectedEstudiante.getActaGradoList().get(0).getHoraGenerarActa();
        }
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Facultad getObject() {
        return object;
    }

    public void setObject(Facultad object) {
        this.object = object;
    }

    public Boolean getColors() {

        return listFilter.get(0).getActaGradoList().get(0).getActaGenerada();
    }

    public List<Estudiante> getListEstudiantes() {
        if (SessionUtil.getUsuario().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            listEstudiantes = efl.findAll();
        } else {
            listEstudiantes = efl.findByFacultad(SessionUtil.getUsuario().getIdFacultad());
        }
        return listEstudiantes;
    }

    public void setListEstudiantes(List<Estudiante> listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }

    public List<Estudiante> getListFilter() {
        return listFilter;
    }

    public void setListFilter(List<Estudiante> listFilter) {
        this.listFilter = listFilter;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getRutaF() {
        return rutaF;
    }

    public void setRutaF(String rutaF) {
        this.rutaF = rutaF;
    }

    public int getIdTipoTitulacion() {
        return IdTipoTitulacion;
    }

    public void setIdTipoTitulacion(int IdTipoTitulacion) {
        this.IdTipoTitulacion = IdTipoTitulacion;
    }

    private Estudiante selectedEstudiante;

    public int getIdTitulo() {
        return IdTitulo;
    }

    public void setIdTitulo(int IdTitulo) {
        this.IdTitulo = IdTitulo;
    }

    public Estudiante getSelectedEstudiante() {
        if (selectedEstudiante != null) {
            selectedEstudiante = efl.find(selectedEstudiante.getCedula());
        }
        return selectedEstudiante;
    }

    public void setSelectedEstudiante(Estudiante selectedEstudiante) {
        this.selectedEstudiante = selectedEstudiante;
    }

    /**
     * Creates a new instance of Estudiante
     */
    public EstudianteMb() {        
        autoridad = new String();
        secretario = new String();
        selectedEstudiante = null;
    }
    
    @PostConstruct
    public void load() {
        media = null;
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event != null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            try {
                InputStream is = event.getFile().getInputstream();
                String ruta = servletContext.getRealPath("/") + "evidencias\\";
                Files.copy(is, new File(ruta, event.getFile().getFileName()).toPath());
                ActaGrado actaGrado = agfl.find(selectedEstudiante.getActaGradoList().get(0).getIdActaGrado());
                actaGrado.setActaBloqueada(Boolean.TRUE);
                actaGrado.setEvidencia("evidencias/" + event.getFile().getFileName());
                agfl.edit(actaGrado);
            } catch (Exception e) {
                e.getMessage();
            } finally {
                FacesMessage message = new FacesMessage("El archivo", event.getFile().getFileName() + " fue subido correctamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void update() {
        if (IdTitulo != 0) {
            list = getList();
        } else {
            IdTitulo = 0;
            list = new LazyEstudianteModel();
        }
    }

    public LazyDataModel<Estudiante> getList() {
        list = new LazyEstudianteModel(new ArrayList<Estudiante>());
        if (IdTitulo != 0 && IdTipoTitulacion != 0 && periodo != 0) {
            switch (IdTipoTitulacion) {
                case 1:
                    list = new LazyEstudianteModel(efl.findEstudianteEntitiesA(IdTitulo, IdTipoTitulacion, periodo));
                    list.setRowCount(efl.findEstudianteEntitiesA(IdTitulo, IdTipoTitulacion, periodo).size());
                    break;
                case 2:
                    list = new LazyEstudianteModel(efl.findEstudianteEntitiesG(IdTitulo, IdTipoTitulacion, periodo));
                    list.setRowCount(efl.findEstudianteEntitiesG(IdTitulo, IdTipoTitulacion, periodo).size());
                    break;
                case 3:
                    break;
                default:
                    break;
            }

            return list;
        } else {
            return list;
        }

    }

    public void guardarNota() {
        try {
            if (selectedEstudiante.getActaGradoList().get(0).getPromedioEstudios() >= 7 && selectedEstudiante.getActaGradoList().get(0).getPromedioEstudios() <= 10) {
                agfl.edit(selectedEstudiante.getActaGradoList().get(0));
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado correctamente", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error verifique los valores", null));
            }
        } catch (Exception ex) {
            Logger.getLogger(EstudianteMb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rutaOpen() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            String ruta2 = "recursos/" + selectedEstudiante.getCedula() + ".pdf";
            //String ruta = servletContext.getRealPath("/") + "recursos\\" + selectedEstudiante.getCedula() + ".pdf";
            context.addCallbackParam("view", ruta2);
        } catch (Exception ex) {
            ex.getMessage();
        }
        //return ruta2;
    }

    public void rutaOpenEvidencia() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            String ruta2 = selectedEstudiante.getActaGradoList().get(0).getEvidencia();
            context.addCallbackParam("view", ruta2);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public Boolean getEstadoActa(Estudiante e) {
        e = efl.find(e.getCedula());
        if (e != null) {
            Boolean ban2;
            ban2 = e.getActaGradoList().get(0).getActaGenerada();
            return ban2;
        } else {
            return Boolean.FALSE;
        }
    }

    public float getPromedioEstudios(Estudiante e) {
        e = efl.find(e.getCedula());
        float p;
        try {
            p = e.getActaGradoList().get(0).getPromedioEstudios();
        } catch (Exception ex) {
            p = 0F;
        }
        return p;
    }

    public Boolean getNotaPromedio(Estudiante e) {
        return (e.getActaGradoList().get(0).getPromedioEstudios() != 0 || e.getActaGradoList().get(0).getPromedioEstudios() != null);
    }

    public Boolean getEstadoActaBloqueada(Estudiante e) {
        e = efl.find(e.getCedula());
        if (e != null) {
            Boolean ban2;
            ban2 = e.getActaGradoList().get(0).getActaBloqueada();
            return ban2;
        } else {
            return Boolean.FALSE;
        }
    }

    public Estudiante getEstudianteUpdated(Estudiante e) {
        e = efl.find(e.getCedula());
        return e;
    }

    public void generarActa() {
        getFecha();
        getHora();
        try {
            Titulo t = tfl.find(selectedEstudiante.getIdTitulo().getIdtitulo());
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
//            if (ban1) {
//                pdf.numeroActa = secuencial;
//            } else {
//                Integer e = Integer.parseInt(t.getIdCarrera().getIdFacultad().getSecuencialActa());
//                pdf.numeroActa = e.toString();
//            }
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            ruta = servletContext.getRealPath("/");
            nombreArchivo = "\\" + selectedEstudiante.getCedula() + ".pdf";
            genPDF(selectedEstudiante.getCedula(), fecha, hora);
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            listFilter = null;
        }
    }

    public void genPDF(String object, Date fecha, Date hora) {
        Estudiante e = efl.find(object);
        DenominacionTitulo dt = dtfl.findTituloGenero(e.getIdTitulo().getIdtitulo(), e.getGenero());
        ActaGrado actaGrado = agfl.find(e.getActaGradoList().get(0).getIdActaGrado());
        Facultad facultad = ffl.find(e.getIdTitulo().getIdCarrera().getIdFacultad().getIdFacultad());
        String autoridad = e.getIdTitulo().getIdCarrera().getIdFacultad().getDenominacionAutoridad();
        String secretaria = e.getIdTitulo().getIdCarrera().getIdFacultad().getDenominacionSecretario();
        String nautoridad = e.getIdTitulo().getIdCarrera().getIdFacultad().getAutoridad();
        String nsecretaria = e.getIdTitulo().getIdCarrera().getIdFacultad().getSecretario();
        String genero;
        String genero2;
        String genero3;
        String genero4;
        String genero5;
        String genero6;
        String genero7;
        String genero8;
        String genero9;
        String genero10;
        String numeroAct;
        boolean numA = false;
        if (e.getActaGradoList().get(0).getNumeroActaGrado() == null) {
            if (e.getIdTitulo().getIdCarrera().getIdFacultad().getPresentarSecuencial()) {
                numeroAct = e.getIdTitulo().getIdCarrera().getIdFacultad().getSecuencialActa();
                numA = true;
            } else {
                numeroAct = "........";
                numA = false;
            }
        } else {
            numeroAct = e.getActaGradoList().get(0).getNumeroActaGrado();
        }
        if ("0".equals(e.getIdTitulo().getIdCarrera().getIdFacultad().getGeneroAutoridad())) {
            genero7 = "el";
            genero10 = "del";
        } else {
            genero7 = "la";
            genero10 = "de la";
        }
        if ("0".equals(e.getIdTitulo().getIdCarrera().getIdFacultad().getGeneroSecretario())) {
            genero8 = "el";
            genero9 = "o";
        } else {
            genero8 = "la";
            genero9 = "a";
        }
        if ("0".equals(e.getGenero())) {
            genero = "o";
            genero2 = "del";
            genero3 = "señor";
            genero4 = "el";
            genero5 = "lo";
            genero6 = "al";
        } else {
            genero = "a";
            genero2 = "de la";
            genero3 = "señora(ita)";
            genero4 = "la";
            genero5 = "la";
            genero6 = "a la";
        }
        document = new Document(PageSize.A4, 75, 65, 50, 50);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ruta + rutaPdf + nombreArchivo);
            PdfWriter pdfw = PdfWriter.getInstance(document, fileOutputStream);
            //PdfWriter.getInstance(document, fileOutputStream);
            document.open();
            Image image = null;
            image = Image.getInstance(ruta + "imagenes" + "\\logoUTEQ.png");
            image.setAlignment(Image.ALIGN_LEFT);
            image.scaleAbsolute(50, 50);
            BaseFont baseFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial Narrow.ttf", BaseFont.WINANSI, true);
            BaseFont subFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial.ttf", BaseFont.WINANSI, true);
            Font fontContenido = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
            Font boldContenido = new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK);
            Font fontTitulos = FontFactory.getFont(
                    FontFactory.TIMES_ROMAN, 18, Font.BOLDITALIC,
                    BaseColor.BLACK);
            Font fontSub = new Font(subFont, 12, Font.BOLD, BaseColor.BLACK);

            // Creacion de una tabla
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);

            PdfPCell cell = new PdfPCell(image);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(100);
            table1.setSpacingBefore(0f);
            table1.setSpacingAfter(0f);

            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase("Universidad Técnica Estatal de Quevedo \n", fontTitulos));
            paragraph.setFont(fontTitulos);
            PdfPCell cell1 = new PdfPCell(paragraph);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.addCell(cell1);

            paragraph = new Paragraph();
            paragraph.add(new Phrase(e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad().toUpperCase()
                    + " \n \n ACTA DE GRADO \n MODALIDAD EXAMEN COMPLEXIVO", fontSub));
            paragraph.setFont(fontSub);
            cell1 = new PdfPCell(paragraph);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(PdfPCell.NO_BORDER);
            table1.addCell(cell1);

            cell = new PdfPCell(table1);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            PdfPTable table2 = new PdfPTable(1);
            table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Barcode128 code = new Barcode128();
            code.setCodeType(Barcode.CODE128);
            code.setCode(e.getCedula());
            Image img1 = code.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);

            PdfPCell cell4;

            Paragraph par = new Paragraph();
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Chunk(img1, 0, -30));
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Phrase(Chunk.NEWLINE));
            par.add(new Phrase("\n \n \n No." + numeroAct + "", fontSub));
            par.setAlignment(Element.ALIGN_RIGHT);

            cell4 = new PdfPCell(par);

            cell4.setBorder(PdfPCell.NO_BORDER);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell4);
// Agregar la tabla al documento
            float[] columnWidths = new float[]{10f, 60f, 15f};
            table.setWidths(columnWidths);
            document.add(table);

            // Creacion del parrafo
            paragraph = new Paragraph();
            paragraph.add(new Phrase(Chunk.NEWLINE));
// Agregar un titulo con su respectiva fuente

// Agregar saltos de linea
// Agregar contenido con su respectiva fuente
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(hora);

            double promedioEstudios = e.getActaGradoList().get(0).getPromedioEstudios();
            promedioEstudios = Double.parseDouble(String.format(Locale.ROOT, "%.2f", promedioEstudios));
            double promedio = 0F;
            if (e.getIdTipoTitulacion().getIdTipoTitulacion() == 1) {
                promedio = (e.getActaGradoList().get(0).getNotaComplexivoGeneral() + e.getActaGradoList().get(0).getNotaComplexivoProfesional());
            } else if (e.getIdTipoTitulacion().getIdTipoTitulacion() == 2) {
                promedio = (e.getActaGradoList().get(0).getNotaGraciaGeneral() + e.getActaGradoList().get(0).getNotaGraciaProfesional());
            }

            promedio = Double.parseDouble(String.format(Locale.ROOT, "%.2f", promedio));
            double notaFinal = promedio * 0.2 + promedioEstudios * 0.8;
            notaFinal = Double.parseDouble(String.format(Locale.ROOT, "%.2f", notaFinal));
            String nl1 = CantLetras.convertirNumerosLetras(promedio);
            String nl2 = CantLetras.convertirNumerosLetras(promedioEstudios);
            String nl3 = CantLetras.convertirNumerosLetras(notaFinal);
            paragraph.add(new Phrase("En la ciudad de Quevedo, provincia de Los Ríos a las ", fontContenido));
            paragraph.add(new Phrase(CantLetras.convertirNumerosLetra(cal2.get(Calendar.HOUR_OF_DAY)).toLowerCase() + " horas " + CantLetras.convertirNumerosLetra(cal2.get(Calendar.MINUTE)).toLowerCase() + " minutos del día "
                    + dia(cal.get(Calendar.DAY_OF_WEEK)).toLowerCase() + " " + cal.get(Calendar.DAY_OF_MONTH) + " de " + mes(cal.get(Calendar.MONTH)).toLowerCase() + " del " + cal.get(Calendar.YEAR)
                    + ",", boldContenido));
            paragraph.add(new Phrase(" en el Auditórium de la " + e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad() + ", ", fontContenido));
            paragraph.add(new Phrase("sala 01,", boldContenido));
            paragraph.add(new Phrase(" siendo el día y la hora fijados mediante"
                    + " disposición administrativa, se instala " + genero7 + " " + autoridad + ", " + genero4 + " Graduad" + genero
                    + " y " + genero8 + " suscrit" + genero9 + " " + secretaria + ", para elaborar la presente Acta de Grado " + genero2 + " "
                    + genero3 + " ", fontContenido));
            paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ",", boldContenido));
            paragraph.add(new Phrase(" previo a la obtención "
                    + "del título de: ", fontContenido));
            paragraph.add(new Phrase(dt.getDenominacionTitulo().toUpperCase() + ". \n \n", boldContenido));
            paragraph.add(new Phrase("Luego de cumplir con lo dispuesto en los artículos 12 al 18 del Reglamento de la Unidad de Titulación Especial "
                    + "de la Universidad Técnica Estatal de Quevedo, con base en la información verificada por " + genero8 + " " + secretaria + ", " + genero4 + " " + genero3 + " ", fontContenido));
            paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
            paragraph.add(new Phrase("aprobó el Examen de Carácter Complexivo obteniendo la calificación promedio de: ", fontContenido));
            paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", promedio) + ") " + nl1, boldContenido));
            paragraph.add(new Phrase(" y registra un promedio de calificaciones obtenidas durante los periodos de estudio de: ", fontContenido));
            paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", promedioEstudios) + ") " + nl2 + ". ", boldContenido));
            paragraph.add(new Phrase("Conforme lo dispone el Art. 20 del Reglamento de la Unidad de Titulación Especial de la Universidad Técnica Estatal de Quevedo, "
                    + "se establece como nota de grado " + genero2 + " " + genero3 + " ", fontContenido));
            paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
            paragraph.add(new Phrase("la calificación de: ", fontContenido));
            paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", notaFinal) + ") " + nl3 + ". \n \n", boldContenido));
            paragraph.add(new Phrase("En consecuencia se " + genero5 + " proclama apt" + genero + " " + genero6 + " " + genero3 + " ", fontContenido));
            paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
            paragraph.add(new Phrase("para ser investid" + genero + " como ", fontContenido));
            paragraph.add(new Phrase(dt.getDenominacionTitulo().toUpperCase() + ". ", boldContenido));
            paragraph.add(new Phrase("La ceremonia de incorporación y entrega del título, se efectuará en un acto solemne programado por la "
                    + e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad() + ".\n \n"
                    + "En virtud de lo actuado, firman la presente Acta " + genero7 + " " + autoridad + ", " + genero4 + " Graduad" + genero + " y " + genero8 + " suscrit" + genero9 + " " + secretaria + ", que certifica.",
                    fontContenido));
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));

// Agregar el parrafo al documento
            paragraph.setLeading(16);
            document.add(paragraph);
// Crear tabla nueva con dos posiciones
            table = new PdfPTable(2);
            float[] longitudes = {5.0f, 5.0f};
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(0f);
            table.setWidths(longitudes);

            fontContenido = new Font(baseFont, 11, Font.BOLD, BaseColor.BLACK);
            cell = new PdfPCell(new Phrase("_______________________________________ \n"
                    + nautoridad + "\n"
                    + autoridad.toUpperCase(), fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("_____________________________________ \n"
                    + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + "\n"
                    + "GRADUAD" + genero.toUpperCase() + "\n" + "CC.N° " + e.getCedula(), fontContenido));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            document.add(table);

            paragraph = new Paragraph(new Phrase("\n \n LO CERTIFICO.-", fontContenido));
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setLeading(16);
            document.add(paragraph);

            paragraph = new Paragraph(new Phrase("_____________________________________ \n"
                    + nsecretaria + "\n"
                    + secretaria.toUpperCase(), fontContenido));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            actaGrado.setNumeroActaGrado(numeroAct);
            actaGrado.setNombreAutoridad(nautoridad);
            actaGrado.setNombreSecretario(nsecretaria);
            actaGrado.setActaGenerada(Boolean.TRUE);
            actaGrado.setFechaIngresoActa(fecha);
            actaGrado.setHoraGenerarActa(hora);
            if (numA) {
                Integer actaF = Integer.parseInt(numeroAct) + 1;
                facultad.setSecuencialActa(actaF.toString());
                ffl.edit(facultad);
            }
            agfl.edit(actaGrado);
//Cerrar el documento

            document.close();

        } catch (Exception ex) {
        }
    }

    private static String dia(int dias) {
        String result = "";
        switch (dias) {
            case 1: {
                result = "Domingo";
                break;
            }
            case 2: {
                result = "Lunes";
                break;
            }
            case 3: {
                result = "Martes";
                break;
            }
            case 4: {
                result = "Miércoles";
                break;
            }
            case 5: {
                result = "Jueves";
                break;
            }
            case 6: {
                result = "Viernes";
                break;
            }
            case 7: {
                result = "Sábado";
                break;
            }
            default: {
                result = "Error" + dias;
                break;
            }
        }
        return result;
    }

    private String mes(int mes) {
        String result = "";
        switch (mes) {
            case 0: {
                result = "Enero";
                break;
            }
            case 1: {
                result = "Febrero";
                break;
            }
            case 2: {
                result = "Marzo";
                break;
            }
            case 3: {
                result = "Abril";
                break;
            }
            case 4: {
                result = "Mayo";
                break;
            }
            case 5: {
                result = "Junio";
                break;
            }
            case 6: {
                result = "Julio";
                break;
            }
            case 7: {
                result = "Agosto";
                break;
            }
            case 8: {
                result = "Septiembre";
                break;
            }
            case 9: {
                result = "Octubre";
                break;
            }
            case 10: {
                result = "Noviembre";
                break;
            }
            case 11: {
                result = "Diciembre";
                break;
            }
            default: {
                result = "Error";
                break;
            }
        }
        return result;
    }

    public void editEstudiante() {
        try {
            efl.edit(selectedEstudiante);
            if (selectedEstudiante.getActaGradoList().get(0).getActaGenerada()) {
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                ruta = servletContext.getRealPath("/");
                genTodos(selectedEstudiante.getActaGradoList().get(0));

                mensaje = "Registro actualizado y Acta generada correctamente";
            } else {
                mensaje = "Registro actualizado correctamente";
            }
        } catch (Exception ex) {
            ex.getMessage();
            mensaje = "Error al guardar datos";

        } finally {
            FacesMessage message = new FacesMessage(mensaje);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void genTodos(ActaGrado a) {
        try {
            Estudiante e = a.getCedulaEstudiante();
            nombreArchivo = "\\" + e.getCedula() + ".pdf";
            DenominacionTitulo dt = dtfl.findTituloGenero(e.getIdTitulo().getIdtitulo(), e.getGenero());
            String autoridad = e.getIdTitulo().getIdCarrera().getIdFacultad().getDenominacionAutoridad();
            String secretaria = e.getIdTitulo().getIdCarrera().getIdFacultad().getDenominacionSecretario();
            String nautoridad = e.getIdTitulo().getIdCarrera().getIdFacultad().getAutoridad();
            String nsecretaria = e.getIdTitulo().getIdCarrera().getIdFacultad().getSecretario();
            String genero;
            String genero2;
            String genero3;
            String genero4;
            String genero5;
            String genero6;
            String genero7;
            String genero8;
            String genero9;
            String genero10;
            String numeroAct;
            boolean numA = false;
            if (e.getActaGradoList().get(0).getNumeroActaGrado() == null) {
                if (e.getIdTitulo().getIdCarrera().getIdFacultad().getPresentarSecuencial()) {
                    numeroAct = e.getIdTitulo().getIdCarrera().getIdFacultad().getSecuencialActa();
                    numA = true;
                } else {
                    numeroAct = "........";
                    numA = false;
                }
            } else {
                numeroAct = e.getActaGradoList().get(0).getNumeroActaGrado();
            }
            if ("0".equals(e.getIdTitulo().getIdCarrera().getIdFacultad().getGeneroAutoridad())) {
                genero7 = "el";
                genero10 = "del";
            } else {
                genero7 = "la";
                genero10 = "de la";
            }
            if ("0".equals(e.getIdTitulo().getIdCarrera().getIdFacultad().getGeneroSecretario())) {
                genero8 = "el";
                genero9 = "o";
            } else {
                genero8 = "la";
                genero9 = "a";
            }
            if ("0".equals(e.getGenero())) {
                genero = "o";
                genero2 = "del";
                genero3 = "señor";
                genero4 = "el";
                genero5 = "lo";
                genero6 = "al";
            } else {
                genero = "a";
                genero2 = "de la";
                genero3 = "señora(ita)";
                genero4 = "la";
                genero5 = "la";
                genero6 = "a la";
            }
            document = new Document(PageSize.A4, 75, 65, 50, 50);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(ruta + rutaPdf + nombreArchivo);
                PdfWriter pdfw = PdfWriter.getInstance(document, fileOutputStream);
                //PdfWriter.getInstance(document, fileOutputStream);
                document.open();
                Image image = null;
                image = Image.getInstance(ruta + "imagenes" + "\\logoUTEQ.png");
                image.setAlignment(Image.ALIGN_LEFT);
                image.scaleAbsolute(50, 50);
                BaseFont baseFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial Narrow.ttf", BaseFont.WINANSI, true);
                BaseFont subFont = BaseFont.createFont(ruta + rutaFuentes + "\\Arial.ttf", BaseFont.WINANSI, true);
                Font fontContenido = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
                Font boldContenido = new Font(baseFont, 12, Font.BOLD, BaseColor.BLACK);
                Font fontTitulos = FontFactory.getFont(
                        FontFactory.TIMES_ROMAN, 18, Font.BOLDITALIC,
                        BaseColor.BLACK);
                Font fontSub = new Font(subFont, 12, Font.BOLD, BaseColor.BLACK);

                // Creacion de una tabla
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                PdfPCell cell = new PdfPCell(image);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                PdfPTable table1 = new PdfPTable(1);
                table1.setWidthPercentage(100);
                table1.setSpacingBefore(0f);
                table1.setSpacingAfter(0f);

                Paragraph paragraph = new Paragraph();
                paragraph.add(new Phrase("Universidad Técnica Estatal de Quevedo \n", fontTitulos));
                paragraph.setFont(fontTitulos);
                PdfPCell cell1 = new PdfPCell(paragraph);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorder(PdfPCell.NO_BORDER);
                table1.addCell(cell1);

                paragraph = new Paragraph();
                paragraph.add(new Phrase(e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad().toUpperCase()
                        + " \n \n ACTA DE GRADO \n MODALIDAD EXAMEN COMPLEXIVO", fontSub));
                paragraph.setFont(fontSub);
                cell1 = new PdfPCell(paragraph);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorder(PdfPCell.NO_BORDER);
                table1.addCell(cell1);

                cell = new PdfPCell(table1);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                PdfPTable table2 = new PdfPTable(1);
                table2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                Barcode128 code = new Barcode128();
                code.setCodeType(Barcode.CODE128);
                code.setCode(e.getCedula());
                Image img1 = code.createImageWithBarcode(pdfw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);

                PdfPCell cell4;

                Paragraph par = new Paragraph();
                par.add(new Phrase(Chunk.NEWLINE));
                par.add(new Chunk(img1, 0, -30));
                par.add(new Phrase(Chunk.NEWLINE));
                par.add(new Phrase(Chunk.NEWLINE));
                par.add(new Phrase("\n \n \n No." + numeroAct + "", fontSub));
                par.setAlignment(Element.ALIGN_RIGHT);

                cell4 = new PdfPCell(par);

                cell4.setBorder(PdfPCell.NO_BORDER);
                cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell4);
// Agregar la tabla al documento
                float[] columnWidths = new float[]{10f, 60f, 15f};
                table.setWidths(columnWidths);
                document.add(table);

                // Creacion del parrafo
                paragraph = new Paragraph();
                paragraph.add(new Phrase(Chunk.NEWLINE));
// Agregar un titulo con su respectiva fuente

// Agregar saltos de linea
// Agregar contenido con su respectiva fuente
                Calendar cal = Calendar.getInstance();
                cal.setTime(a.getFechaIngresoActa());
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(a.getHoraGenerarActa());

                double promedioEstudios = e.getActaGradoList().get(0).getPromedioEstudios();
                promedioEstudios = Double.parseDouble(String.format(Locale.ROOT, "%.2f", promedioEstudios));
                double promedio = (e.getActaGradoList().get(0).getNotaComplexivoGeneral() + e.getActaGradoList().get(0).getNotaComplexivoProfesional());
                promedio = Double.parseDouble(String.format(Locale.ROOT, "%.2f", promedio));
                double notaFinal = promedio * 0.2 + promedioEstudios * 0.8;
                notaFinal = Double.parseDouble(String.format(Locale.ROOT, "%.2f", notaFinal));
                String nl1 = CantLetras.convertirNumerosLetras(promedio);
                String nl2 = CantLetras.convertirNumerosLetras(promedioEstudios);
                String nl3 = CantLetras.convertirNumerosLetras(notaFinal);
                paragraph.add(new Phrase("En la ciudad de Quevedo, provincia de Los Ríos a las ", fontContenido));
                paragraph.add(new Phrase(CantLetras.convertirNumerosLetra(cal2.get(Calendar.HOUR_OF_DAY)).toLowerCase() + " horas " + CantLetras.convertirNumerosLetra(cal2.get(Calendar.MINUTE)).toLowerCase() + " minutos del día "
                        + dia(cal.get(Calendar.DAY_OF_WEEK)).toLowerCase() + " " + cal.get(Calendar.DAY_OF_MONTH) + " de " + mes(cal.get(Calendar.MONTH)).toLowerCase() + " del " + cal.get(Calendar.YEAR)
                        + ",", boldContenido));
                paragraph.add(new Phrase(" en el Auditórium de la " + e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad() + ", ", fontContenido));
                paragraph.add(new Phrase("sala 01,", boldContenido));
                paragraph.add(new Phrase(" siendo el día y la hora fijados mediante"
                        + " disposición administrativa, se instala " + genero7 + " " + autoridad + ", " + genero4 + " Graduad" + genero
                        + " y " + genero8 + " suscrit" + genero9 + " " + secretaria + ", para elaborar la presente Acta de Grado " + genero2 + " "
                        + genero3 + " ", fontContenido));
                paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ",", boldContenido));
                paragraph.add(new Phrase(" previo a la obtención "
                        + "del título de: ", fontContenido));
                paragraph.add(new Phrase(dt.getDenominacionTitulo().toUpperCase() + ". \n \n", boldContenido));
                paragraph.add(new Phrase("Luego de cumplir con lo dispuesto en los artículos 12 al 18 del Reglamento de la Unidad de Titulación Especial "
                        + "de la Universidad Técnica Estatal de Quevedo, con base en la información verificada por " + genero8 + " " + secretaria + ", " + genero4 + " " + genero3 + " ", fontContenido));
                paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
                paragraph.add(new Phrase("aprobó el Examen de Carácter Complexivo obteniendo la calificación promedio de: ", fontContenido));
                paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", promedio) + ") " + nl1, boldContenido));
                paragraph.add(new Phrase(" y registra un promedio de calificaciones obtenidas durante los periodos de estudio de: ", fontContenido));
                paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", promedioEstudios) + ") " + nl2 + ". ", boldContenido));
                paragraph.add(new Phrase("Conforme lo dispone el Art. 20 del Reglamento de la Unidad de Titulación Especial de la Universidad Técnica Estatal de Quevedo, "
                        + "se establece como nota de grado " + genero2 + " " + genero3 + " ", fontContenido));
                paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
                paragraph.add(new Phrase("la calificación de: ", fontContenido));
                paragraph.add(new Phrase("(" + String.format(Locale.ROOT, "%.2f", notaFinal) + ") " + nl3 + ". \n \n", boldContenido));
                paragraph.add(new Phrase("En consecuencia se " + genero5 + " proclama apt" + genero + " " + genero6 + " " + genero3 + " ", fontContenido));
                paragraph.add(new Phrase(e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + ", ", boldContenido));
                paragraph.add(new Phrase("para ser investid" + genero + " como ", fontContenido));
                paragraph.add(new Phrase(dt.getDenominacionTitulo().toUpperCase() + ". ", boldContenido));
                paragraph.add(new Phrase("La ceremonia de incorporación y entrega del título, se efectuará en un acto solemne programado por la "
                        + e.getIdTitulo().getIdCarrera().getIdFacultad().getFacultad() + ".\n \n"
                        + "En virtud de lo actuado, firman la presente Acta " + genero7 + " " + autoridad + ", " + genero4 + " Graduad" + genero + " y " + genero8 + " suscrit" + genero9 + " " + secretaria + ", que certifica.",
                        fontContenido));
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.add(new Phrase(Chunk.NEWLINE));
                paragraph.add(new Phrase(Chunk.NEWLINE));
                paragraph.add(new Phrase(Chunk.NEWLINE));
                paragraph.add(new Phrase(Chunk.NEWLINE));

// Agregar el parrafo al documento
                paragraph.setLeading(16);
                document.add(paragraph);
// Crear tabla nueva con dos posiciones
                table = new PdfPTable(2);
                float[] longitudes = {5.0f, 5.0f};
                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);
                table.setWidths(longitudes);

                fontContenido = new Font(baseFont, 11, Font.BOLD, BaseColor.BLACK);
                cell = new PdfPCell(new Phrase("_______________________________________ \n"
                        + nautoridad.toUpperCase() + "\n"
                        + autoridad.toUpperCase(), fontContenido));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("_____________________________________ \n"
                        + e.getApellidos().toUpperCase() + " " + e.getNombres().toUpperCase() + "\n"
                        + "GRADUAD" + genero.toUpperCase() + "\n" + "CC.N° " + e.getCedula(), fontContenido));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                document.add(table);

                paragraph = new Paragraph(new Phrase("\n \n LO CERTIFICO.-", fontContenido));
                paragraph.add(new Phrase(Chunk.NEWLINE));
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                paragraph.setLeading(16);
                document.add(paragraph);

                paragraph = new Paragraph(new Phrase("_____________________________________ \n"
                        + nsecretaria.toUpperCase() + "\n"
                        + secretaria.toUpperCase(), fontContenido));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
//Cerrar el documento

                document.close();

            } catch (Exception ex) {

            }
        } catch (Exception e) {
            e.getMessage();

        }
    }

    public void setList(LazyDataModel<Estudiante> list) {
        this.list = list;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public boolean isBan1() {
        return ban1;
    }

    public void setBan1(boolean ban1) {
        this.ban1 = ban1;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getSelectedFacultad() {
        return selectedFacultad;
    }

    public void setSelectedFacultad(Integer selectedFacultad) {
        this.selectedFacultad = selectedFacultad;
    }

    public List<SelectItem> getFacultadL() {
        facultadL = new ArrayList<>();
        List<Facultad> fs;
        if (SessionUtil.getUsuario().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            fs = ffl.findAll();
        } else {
            fs = new ArrayList<>();
            fs.add(SessionUtil.getUsuario().getIdFacultad());
        }
        for (Facultad f : fs) {
            facultadL.add(new SelectItem(f.getIdFacultad(), f.getFacultad()));
        }
        return facultadL;
    }

    public void setFacultadL(List<SelectItem> facultadL) {
        this.facultadL = facultadL;
    }

    public Facultad getFacuSelect() {
        facuSelect = new Facultad();
        if (selectedFacultad != null || selectedFacultad != 0) {
            facuSelect = ffl.find(selectedFacultad);
        }
        return facuSelect;
    }

    public void setFacuSelect(Facultad facuSelect) {
        this.facuSelect = facuSelect;
    }

    public void guardarFacultad() {
        getFacuSelect();
        try {
            facuSelect.setDenominacionAutoridad(autoridad);
            facuSelect.setDenominacionSecretario(secretario);
            ffl.edit(facuSelect);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se editó el acta"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se editó el acta"));
        }
    }
    
    public void cargarReporte(){
        media = null;
//        DaoRepActaEmision daoReport = new DaoRepActaEmision();
//        if(themePromociones != null && themeMaestria != null)
//           media = daoReport.reporte(this.themePromociones.getId(), themeMaestria.getId());
    }

    public String getAutoridad() {
        if (selectedFacultad != null || selectedFacultad != 0) {
            autoridad = ffl.find(selectedFacultad).getDenominacionAutoridad();
        } else {
            autoridad = "";
        }
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getSecretario() {
        secretario = " ";
        if (selectedFacultad != null || selectedFacultad != 0) {
            secretario = ffl.find(selectedFacultad).getDenominacionSecretario();
        } else {
            secretario = "";
        }
        return secretario;
    }

    public void setSecretario(String secretario) {
        this.secretario = secretario;
    }
}
