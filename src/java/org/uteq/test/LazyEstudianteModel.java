/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.persistence.indirection.IndirectList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.uteq.model.ActaGrado;
import org.uteq.model.Estudiante;

/**
 *
 * @author USUARIO
 */
public class LazyEstudianteModel extends LazyDataModel<Estudiante> {

    private List<Estudiante> datasource;
    private int titulo;
    private int titulacion;

    public LazyEstudianteModel(List<Estudiante> datasource) {
        this.datasource = datasource;
    }

    public LazyEstudianteModel() {
    }

    @Override
    public Estudiante getRowData(String rowKey) {
        for (Estudiante e : datasource) {
            if (e.getCedula().equals(rowKey)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Estudiante e) {
        return e.getCedula();
    }

    @Override
    public List<Estudiante> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Estudiante> data = new ArrayList<>();
        IndirectList acta = new IndirectList();
        String fieldValue;

        //filter
        for (Estudiante car : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field f = car.getClass().getDeclaredField(filterProperty);
                        f.setAccessible(Boolean.TRUE);
                        if (filterProperty.equals("actaGradoList")) {
                            acta = (IndirectList) f.get(car);
                            ActaGrado a = (ActaGrado) acta.get(0);
                            fieldValue = String.valueOf(a.getActaGenerada());
                        } else {
                            fieldValue = String.valueOf(f.get(car));
                        }

                        if (filterValue == null || fieldValue.toLowerCase().contains(filterValue.toString().toLowerCase())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(car);
            }
        }

        //sort
        try {
            if (sortField != null) {
                Collections.sort(data, new LazySorter(sortField, sortOrder));
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

    public int getTitulo() {
        return titulo;
    }

    public void setTitulo(int titulo) {
        this.titulo = titulo;
    }

    public int getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(int titulacion) {
        this.titulacion = titulacion;
    }
}
