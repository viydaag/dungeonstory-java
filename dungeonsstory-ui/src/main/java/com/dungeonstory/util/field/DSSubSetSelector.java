package com.dungeonstory.util.field;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.SubSetSelector;

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
    
    @Override
    @SuppressWarnings("unchecked")
    public MTable<T> getTable() {
        return super.getTable();
    }

}
