package com.dungeonstory.ui.component;

import org.vaadin.viritin.button.DeleteButton;

import com.vaadin.icons.VaadinIcons;

public abstract class DSAbstractForm<T> extends AbstractForm<T> {

    private static final long serialVersionUID = -2461539480770697988L;

    public DSAbstractForm(Class<T> entityClass) {
        super(entityClass);

        //config des boutons
        setSaveCaption("Enregistrer");
        setDeleteCaption("");
        setCancelCaption("Annuler");
        setModalWindowTitle("");

        DeleteButton deleteButton = new DeleteButton("");
        deleteButton.setIcon(VaadinIcons.TRASH);
        deleteButton.setCancelCaption("Annuler");
        deleteButton.setConfirmWindowCaption("Confirmation");
        deleteButton.setConfirmationText("Êtes-vous certain de vouloir supprimer?");
        setDeleteButton(deleteButton);
    }
    


}
