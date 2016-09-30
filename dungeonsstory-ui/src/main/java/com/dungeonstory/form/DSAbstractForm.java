package com.dungeonstory.form;

import org.vaadin.viritin.MBeanFieldGroup;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.server.FontAwesome;

public abstract class DSAbstractForm<T> extends AbstractForm<T> {

    private static final long serialVersionUID = -2461539480770697988L;

    public DSAbstractForm() {
        super();

        //config des boutons
        setSaveCaption("Enregistrer");
        setCancelCaption("Annuler");
        setDeleteCaption("");
        setModalWindowTitle("");
        getDeleteButton().setIcon(FontAwesome.TRASH_O);
    }
    
    @Override
    public MBeanFieldGroup<T> setEntity(T entity) {
        beforeSetEntity(entity);
        MBeanFieldGroup<T> fieldgroup = super.setEntity(entity);
        afterSetEntity();
        return fieldgroup;
    }
    
    public void beforeSetEntity(T entity) {
        
    }
    
    public void afterSetEntity() {
        
    }

}
