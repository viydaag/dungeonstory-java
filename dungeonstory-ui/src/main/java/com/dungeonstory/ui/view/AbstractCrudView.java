package com.dungeonstory.ui.view;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractCrudView<T extends Entity> extends VerticalLayout implements CrudView<T> {

    private static final long serialVersionUID = -6564885112560677215L;

    private Label            title;
    private HorizontalLayout buttonLayout;
    protected TextField      filter;
    private String           filterBy = "name";

    protected DSAbstractForm<T>    form;
    protected DSGrid<T>            grid;
    protected DataService<T, Long> service;
    protected CrudDataProvider<T>  dataProvider;

    private boolean isFormPopup     = false;
    private boolean isCreateAllowed = true;
    private boolean isDeleteAllowed = true;
    private boolean isFilterAllowed = true;

    private Button addButton;

    private CssLayout filterLayout;

    public abstract DSAbstractForm<T> getForm();

    public abstract DSGrid<T> getGrid();

    public abstract DataService<T, Long> getDataService();

    public AbstractCrudView() {

        form = getForm();
        grid = getGrid();
        service = getDataService();
        dataProvider = new CrudDataProvider<>(service, getFilterBy());

        filter = new TextField();
        filter.setId("filterText");
        filter.setPlaceholder("filtre...");
        filter.addValueChangeListener(e -> {
            listEntries(e.getValue());
        });
        Button clearFilterButton = new Button(VaadinIcons.CLOSE);
        clearFilterButton.setId("clearFilterButton");
        clearFilterButton.addClickListener(event -> filter.clear());
        filterLayout = new CssLayout(filter, clearFilterButton);
        filterLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        initForm();

        initGrid();

        if (form != null) {
            addComponent(title);
            if (isCreateAllowed()) {
                addComponent(buttonLayout);
            }
            addComponent(form);
            if (isFilterAllowed()) {
                addComponent(filterLayout);
            }
            addComponentsAndExpand(grid);
        } else {
            if (isFilterAllowed()) {
                addComponent(filterLayout);
            }
            addComponentsAndExpand(grid);
        }
    }

    protected void initGrid() {
        grid.setId("crudGrid");
        grid.setDataProvider(dataProvider);
        grid.addSelectionListener(selectionEvent -> {
            entrySelected(selectionEvent.getFirstSelectedItem().orElse(null));
        });
    }

    protected void listEntries(String text) {
        dataProvider.setFilter(text);
    }

    protected void listEntries() {
        listEntries(filter.getValue());
    }

    protected void initForm() {
        if (form != null) {
            title = new Label(form.toString());

            if (isCreateAllowed()) {
                addButton = new Button("", VaadinIcons.PLUS);
                addButton.setId("crudAddButton");
                addButton.addClickListener(this::addNew);
                buttonLayout = new HorizontalLayout(addButton);
            }

            form.setEntity(null);

            //ajout handlers pour boutons
            form.setSavedHandler(this::entrySaved);
            form.setResetHandler(this::entryReset);
            form.setCancelHandler(this::cancel);

            if (isDeleteAllowed()) {
                form.setDeleteHandler(this::deleteSelected);
            } else {
                form.getDeleteButton().setVisible(false);
            }

            form.getSaveButton().setId("crudSaveButton");
            form.getResetButton().setId("crudResetButton");
            form.getDeleteButton().setId("crudDeleteButton");
            form.getCancelButton().setId("crudCancelButton");
        }
    }

    @Override
    public void entrySaved(T entity) {

        try {
            //save to database
            dataProvider.save(entity);

            //refresh ui
            grid.deselectAll();
            closeForm();

            Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
        } catch (ConstraintViolationException e) {
            String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
            Notification.show("Failed! " + message, Type.ERROR_MESSAGE);
            listEntries();
            throw e;
        } catch (Exception e) {
            Notification.show("Failed! " + e.getLocalizedMessage(), Type.ERROR_MESSAGE);
            listEntries();
            throw e;
        }
    }

    protected void closeForm() {
        if (form != null) {
            form.setEntity(null);
            if (isFormPopup()) {
                form.closePopup();
            }
        }
    }

    @Override
    public void entryReset(T entity) {
        boolean isNew = dataProvider.refresh(entity);
        if (isNew) {
            entity = service.create();
        }
        form.setEntity(entity);
    }

    public void cancel(T entity) {
        try {
            entryReset(entity);
        } catch (Exception e) {
            form.setEntity(null);
        }
        closeForm();
        grid.deselectAll();
    }

    @Override
    public void entrySelected(T entity) {
        if (form != null) {
            form.setEntity(entity);

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
        form.focusFirst();
    }

    @Override
    public void deleteSelected(T entity) {
        try {
            dataProvider.delete(entity);
            closeForm();
        } catch (Exception e) {
            Notification.show("Erreur suppression : soit les données n'existent pas ou ils sont utilisées sur d'autres objets", Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        listEntries();
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
        addButton.setVisible(isCreateAllowed);
    }

    public boolean isDeleteAllowed() {
        return isDeleteAllowed;
    }

    public void setDeleteAllowed(boolean isDeleteAllowed) {
        this.isDeleteAllowed = isDeleteAllowed;
        form.getDeleteButton().setVisible(isDeleteAllowed);
    }

    protected void setService(DataService<T, Long> service) {
        this.service = service;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
        dataProvider.setFilterProperty(filterBy);
    }

    public boolean isFilterAllowed() {
        return isFilterAllowed;
    }

    public void setFilterAllowed(boolean isFilterAllowed) {
        this.isFilterAllowed = isFilterAllowed;
        filterLayout.setVisible(isFilterAllowed);
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public void setSortInMemory(boolean sortInMemory) {
        this.dataProvider.setSortInMemory(sortInMemory);
    }

}
