/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.test;

import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;
import org.uteq.model.Estudiante;

/**
 *
 * @author USUARIO
 */
public class LazySorter implements Comparator<Estudiante> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Estudiante o1, Estudiante o2) {
        try {
            Field f;
            f = Estudiante.class.getDeclaredField(this.sortField);
            f.setAccessible(Boolean.TRUE);
            Object value1 = f.get(o1);
            Object value2 = f.get(o2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
