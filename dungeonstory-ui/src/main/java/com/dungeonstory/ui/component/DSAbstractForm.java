package com.dungeonstory.ui.component;

import java.io.Serializable;

import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public abstract class DSAbstractForm<T> extends AbstractForm<T> {

    private static final long serialVersionUID = -2461539480770697988L;

    private Button           cancelButton;
    private CancelHandler<T> cancelHandler;

    public interface CancelHandler<T> extends Serializable {

        void onCancel(T entity);
    }

    public DSAbstractForm(Class<T> entityClass) {
        super(entityClass);

        //config des boutons
        setSaveCaption("Enregistrer");
        setDeleteCaption("");
        setCancelCaption("Annuler");
        setModalWindowTitle("");
        getDeleteButton().setIcon(VaadinIcons.TRASH);
    }
    
    @Override
    public void setEntity(T entity) {
        beforeSetEntity(entity);
        super.setEntity(entity);
        afterSetEntity();
    }
    
    public void beforeSetEntity(T entity) {
        
    }
    
    public void afterSetEntity() {
        
    }

    @Override
    protected Button createResetButton() {
        return new MButton(getResetCaption()).withVisible(false);
    }

    protected String getResetCaption() {
        return "Réinitialiser";
    }

    protected Button createCancelButton() {
        return new MButton(getCancelCaption()).withVisible(false);
    }

    public Button getCancelButton() {
        if (cancelButton == null) {
            setCancelButton(createCancelButton());
        }
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
        this.cancelButton.addClickListener(event -> cancel(event));
    }

    protected void cancel(ClickEvent event) {
        cancelHandler.onCancel(getEntity());
    }

    public CancelHandler<T> getCancelHandler() {
        return cancelHandler;
    }

    public void setCancelHandler(CancelHandler<T> cancelHandler) {
        this.cancelHandler = cancelHandler;
        getCancelButton().setVisible(this.cancelHandler != null);
    }

    @Override
    public HorizontalLayout getToolbar() {
        return new MHorizontalLayout(getSaveButton(), getResetButton(), getCancelButton(), getDeleteButton());
    }

    public void adjustButtons() {
        adjustSaveButtonState();
        adjustResetButtonState();
    }

}
