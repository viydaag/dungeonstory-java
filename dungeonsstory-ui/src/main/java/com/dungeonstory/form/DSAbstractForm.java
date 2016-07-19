package com.dungeonstory.form;

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

}
