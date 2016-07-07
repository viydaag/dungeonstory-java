package com.dungeonstory.view.component;

import org.vaadin.viritin.fields.SubSetSelector;

import com.vaadin.data.Property;

public class DSSubSetSelector<T> extends SubSetSelector<T> {

    private static final long serialVersionUID = 8754503528316399935L;

    public DSSubSetSelector(Class<T> elementType) {
        super(elementType);
    }
    
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        if (newDataSource != null) {
            super.setPropertyDataSource(newDataSource);
        }
    }

}
