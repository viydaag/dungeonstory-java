package com.dungeonstory.view;

import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public abstract class AbstractCrudView<T extends Entity> extends VerticalSpacedLayout implements CrudView<T> {

    private static final long    serialVersionUID = -6564885112560677215L;

    private Label                titre;
    private DSAbstractForm<T>    form;
    private BeanGrid<T>          grid;
    private DataService<T, Long> service;
    private boolean              isFormPopup      = false;

    public abstract DSAbstractForm<T> getForm();

    public abstract BeanGrid<T> getGrid();

    public abstract DataService<T, Long> getDataService();

    public AbstractCrudView() {

        form = getForm();
        grid = getGrid();
        service = getDataService();

        HorizontalLayout boutonLayout = null;
        if (form != null) {
            titre = new Label(form.toString());

            Button addNew = new Button("", FontAwesome.PLUS);

            addNew.addClickListener(this::addNew);
            boutonLayout = new HorizontalLayout(addNew);

            form.setEntity(null);

            //ajout handlers pour boutons
            form.setSavedHandler(this::entrySaved);
            form.setResetHandler(this::entryReset);
            form.setDeleteHandler(this::deleteSelected);
        }

        grid.addSelectionListener(selectionEvent -> {
            entrySelected();
        });

        if (form != null) {
            addComponents(titre, boutonLayout, form, grid);
        } else {
            addComponents(titre, grid);
        }
    }

    public void entrySaved(T entity) {
        //save to database
        service.create(entity);

        //refresh ui
        closeForm();
//        grid.refresh(entity);
        grid.setData(service.findAll());
        grid.scrollTo(entity);

        Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }

    private void closeForm() {
        form.setEntity(null);
        if (isFormPopup()) {
            form.closePopup();
        }
    }

    public void entryReset(T entity) {
        closeForm();
    }

    public void entrySelected() {
        form.setEntity(grid.getSelectedRow() == null ? service.create() : grid.getSelectedRow());
        if (isFormPopup()) {
            form.openInModalPopup();
        }
        form.focusFirst();
    }

    public void addNew(Button.ClickEvent e) {
        form.setEntity(service.create());
        if (isFormPopup()) {
            form.openInModalPopup();
        }
    }

    public void deleteSelected(T entity) {
        grid.remove(entity);
        closeForm();
        service.delete(entity);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        grid.setData(service.findAll());
    }
    
    public boolean isFormPopup() {
        return isFormPopup;
    }

    public void setFormPopup(boolean isFormPopup) {
        this.isFormPopup = isFormPopup;
    }

}
