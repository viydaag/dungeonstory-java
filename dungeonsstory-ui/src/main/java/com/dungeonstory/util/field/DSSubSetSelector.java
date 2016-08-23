package com.dungeonstory.util.field;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.SubSetSelector;

import com.vaadin.data.Property;

/**
 * Classe temporaire pour corriger bug de SubSetSelector
 *
 * @param <T>
 */
public class DSSubSetSelector<T> extends SubSetSelector<T> {

    private static final long serialVersionUID = 8754503528316399935L;

    public DSSubSetSelector(Class<T> elementType) {
        super(elementType);
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        if (newDataSource != null) {
            super.setPropertyDataSource(newDataSource);
        }
    }
    
    public MTable<T> getTable() {
        return super.getTable();
    }

}
