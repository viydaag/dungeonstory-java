package com.dungeonstory.view;

import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.VerticalSpacedLayout;
import com.dungeonstory.view.component.BeanGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public abstract class AbstractCrudView<T extends Entity> extends VerticalSpacedLayout implements CrudView<T> {

    private static final long serialVersionUID = -6564885112560677215L;

    private Label                  title;
    private HorizontalLayout       buttonLayout;
    
    protected DSAbstractForm<T>    form;
    protected BeanGrid<T>          grid;
    protected DataService<T, Long> service;

    private boolean isFormPopup     = false;
    private boolean isCreateAllowed = true;
    private boolean isDeleteAllowed = true;

    public abstract DSAbstractForm<T> getForm();

    public abstract BeanGrid<T> getGrid();

    public abstract DataService<T, Long> getDataService();

    public AbstractCrudView() {

        form = getForm();
        grid = getGrid();
        service = getDataService();

        initForm();

        grid.addSelectionListener(selectionEvent -> {
            entrySelected();
        });

        if (form != null) {
            addComponent(title);
            if (isCreateAllowed()) {
                addComponent(buttonLayout);
            }
            addComponents(form, grid);
        } else {
            addComponents(grid);
        }
    }

    protected void initForm() {
        if (form != null) {
            title = new Label(form.toString());

            if (isCreateAllowed()) {
                Button addNew = new Button("", FontAwesome.PLUS);
                addNew.addClickListener(this::addNew);
                buttonLayout = new HorizontalLayout(addNew);
            }

            form.setEntity(null);

            //ajout handlers pour boutons
            form.setSavedHandler(this::entrySaved);
            form.setResetHandler(this::entryReset);

            if (isDeleteAllowed()) {
                form.setDeleteHandler(this::deleteSelected);
            } else {
                form.getDeleteButton().setVisible(false);
            }
        }
    }

    @Override
    public void entrySaved(T entity) {
        //save to database
        service.saveOrUpdate(entity);

        //refresh ui
        closeForm();
        //        grid.refresh(entity);
        grid.setData(service.findAll());
        grid.scrollTo(entity);

        Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
    }

    protected void closeForm() {
        form.setEntity(null);
        if (isFormPopup()) {
            form.closePopup();
        }
    }

    @Override
    public void entryReset(T entity) {
        closeForm();
    }

    @Override
    public void entrySelected() {
        if (form != null) {
            form.setEntity(grid.getSelectedRow() == null ? service.create() : grid.getSelectedRow());
            if (isFormPopup()) {
                form.openInModalPopup();
            }
            form.focusFirst();
        }
    }

    public void addNew(Button.ClickEvent e) {
        form.setEntity(service.create());
        if (isFormPopup()) {
            form.openInModalPopup();
        }
    }

    @Override
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

    public boolean isCreateAllowed() {
        return isCreateAllowed;
    }

    public void setCreateAllowed(boolean isCreateAllowed) {
        this.isCreateAllowed = isCreateAllowed;
    }

    public boolean isDeleteAllowed() {
        return isDeleteAllowed;
    }

    public void setDeleteAllowed(boolean isDeleteAllowed) {
        this.isDeleteAllowed = isDeleteAllowed;
    }

    protected void setService(DataService<T, Long> service) {
        this.service = service;
    }

}
