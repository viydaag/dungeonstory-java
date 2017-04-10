package com.dungeonstory.view;

import org.vaadin.viritin.LazyList;
import org.vaadin.viritin.fields.MTextField;

import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.DSTheme;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractCrudView<T extends Entity> extends VerticalSpacedLayout implements CrudView<T> {

    private static final long serialVersionUID = -6564885112560677215L;

    private Label            title;
    private HorizontalLayout buttonLayout;
    protected TextField      filter;
    private String           filterBy = "name";

    protected DSAbstractForm<T>    form;
    protected DSGrid<T>            grid;
    protected DataService<T, Long> service;

    private boolean isFormPopup     = false;
    private boolean isCreateAllowed = true;
    private boolean isDeleteAllowed = true;
    private boolean isFilterAllowed = true;

    public abstract DSAbstractForm<T> getForm();

    public abstract DSGrid<T> getGrid();

    public abstract DataService<T, Long> getDataService();

    public AbstractCrudView() {

        form = getForm();
        grid = getGrid();
        service = getDataService();

        filter = new MTextField().withInputPrompt("filtre...").withStyleName(DSTheme.FILTER_TEXT);
        filter.addTextChangeListener(e -> {
            listEntries(e.getText());
        });
        Button clearFilterButton = new Button(FontAwesome.CLOSE);
        clearFilterButton.addClickListener(event -> {
            filter.clear();
            listEntries(); //not necessary on Vaadin 8
        });
        CssLayout filterLayout = new CssLayout(filter, clearFilterButton);
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
            addComponent(grid);
        } else {
            if (isFilterAllowed()) {
                addComponent(filterLayout);
            }
            addComponents(grid);
        }
    }

    protected void initGrid() {
        grid.addSelectionListener(selectionEvent -> {
            entrySelected();
        });
    }

    protected void listEntries(String text) {
        if (isFilterAllowed()) {
            grid.lazyLoadFrom((int firstRow, boolean[] sortAscending, String[] property) -> {
                String[] order = new String[sortAscending.length];
                for (int i = 0; i < sortAscending.length; i++) {
                    order[i] = sortAscending[i] ? "ASC" : "DESC";
                }
                return service.findAllByLikePagedOrderBy(getFilterBy(), text, firstRow, LazyList.DEFAULT_PAGE_SIZE,
                        property, order);
            }, () -> service.countWithFilter(getFilterBy(), text));
        } else {
            grid.lazyLoadFrom((int firstRow, boolean sortAscending, String property) -> service.findAllPaged(firstRow,
                    LazyList.DEFAULT_PAGE_SIZE), () -> (int) service.count());
        }
        //        grid.setRows(service.findAll());
    }

    protected void listEntries() {
        listEntries(filter.getValue());
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

        try {
            //save to database
            service.saveOrUpdate(entity);

            //refresh ui
            grid.deselectAll();
            closeForm();
            //        grid.refresh(entity);
            listEntries();
            grid.scrollTo(entity);

            Notification.show("Saved!", Type.HUMANIZED_MESSAGE);
        } catch (Exception e) {
            Notification.show("Failed! " + e.getLocalizedMessage(), Type.ERROR_MESSAGE);
            listEntries();
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
        closeForm();
    }

    @Override
    public void entrySelected() {
        if (form != null) {
            form.setEntity(grid.getSelectedRow());
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
            service.delete(entity);
            closeForm();
            grid.refreshRows();
        } catch (Exception e) {
            Notification.show(
                    "Erreur suppression : soit les données n'existent pas ou ils sont utilisées sur d'autres objets",
                    Type.ERROR_MESSAGE);
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

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public boolean isFilterAllowed() {
        return isFilterAllowed;
    }

    public void setFilterAllowed(boolean isFilterAllowed) {
        this.isFilterAllowed = isFilterAllowed;
    }

}
