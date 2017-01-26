/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author USUARIO
 */
@ViewScoped
@Named
public class FileMb implements Serializable{

    private File ficheros;
    private List<File> lisFic;
    private String ruta;
    private List<File> filtereFiles;

    public List<File> getLisFic() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        context.getContextPath();
        String sDirectorio = context.getRealPath("/") + "recursos";
        lisFic = new ArrayList <File>();
        ficheros = new File(sDirectorio);
        if (ficheros.exists()) {
            File[] ficheros1 = ficheros.listFiles();            
            lisFic = Arrays.asList(ficheros1);
        } else {

        }
        return lisFic;
    }

    public void setLisFic(List<File> lisFic) {
        this.lisFic = lisFic;
    }

    public File getFicheros() {
        return ficheros;
    }

    public void setFicheros(File ficheros) {
        this.ficheros = ficheros;
    }

    private void obtenerArchivos() {

    }

    public String getRuta() {
        return ruta;
    }
    public String obtenerRuta(){
        return "1204526667.pdf";
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<File> getFiltereFiles() {
        return filtereFiles;
    }

    public void setFiltereFiles(List<File> filtereFiles) {
        this.filtereFiles = filtereFiles;
    }
    
    

    /**
     * Creates a new instance of FileMb
     */
    
    public FileMb() {
    }

}
