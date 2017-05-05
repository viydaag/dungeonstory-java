package com.dungeonstory.util.field;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

public class DSSubSetSelector2<ET, C extends Collection<ET>> extends CustomField<C>
        implements AbstractForm.SavedHandler<ET>, AbstractForm.ResetHandler<ET> {

    private static final long serialVersionUID = -3340932901781677504L;

    private ComboBox<ET> cb;

    //    private MTable<ET>        table;
    private Grid<ET>         grid;
    private C                selected;
    private Button           newInstanceBtn;
    private final Class<ET>  elementType;
    private Class<C>         containerType;
    private HorizontalLayout toprow;
    private VerticalLayout   verticalLayout;
    private AbstractForm<ET> newInstanceForm;
    private List<ET>         availableOptions;
    private int              limit = Integer.MAX_VALUE;

    private Function<String, ET> instantiator = this::instantiateOption;

    public DSSubSetSelector2(Class<ET> elementType) {
        this.elementType = elementType;
        cb = new ComboBox<>();
        //        table = new MTable<>(elementType).withFullWidth();
        grid = new Grid<>();
        setHeight("300px");
        toprow = new HorizontalLayout(cb);
        verticalLayout = new VerticalLayout(toprow);
        verticalLayout.addComponentsAndExpand(grid);

        //        table.setPageLength(5);
        grid.setHeightByRows(5);
        grid.setWidth(100, Unit.PERCENTAGE);

        //TODO : replace with Component renderer in 8.1
        grid.addColumn(entity -> "-", new ButtonRenderer<ET>(clickEvent -> removeSelectedOption(clickEvent.getItem()))).setCaption("")
                .setId("Remove");
        //        grid.addColumnVisibilityChangeListener(event -> System.out.println("cloumn visible change"));

        //        table.withGeneratedColumn("Remove", new MTable.SimpleColumnGenerator<ET>() {
        //            @Override
        //            public Object generate(final ET entity) {
        //                return getToolColumnContent(entity);
        //            }
        //        });
        //        table.setColumnHeader("Remove", "");
        cb.setPlaceholder("Add to selection...");
        cb.addValueChangeListener(new ValueChangeListener<ET>() {

            private static final long serialVersionUID = 2474890452288971675L;

            @Override
            public void valueChange(com.vaadin.data.HasValue.ValueChangeEvent<ET> event) {
                if (event.getValue() != null) {
                    ET pojo = event.getValue();
                    //                    cb.getBic().removeItem(pojo);
                    //                    cb.setValue(null);
                    //                    table.addItem(pojo);
                    //                    selected.add(pojo);

                    C newValue = cloneSelected();
                    newValue.add(pojo);
                    //                    cb.setEnabled(selected.size() < limit);
                    // fire value change
                    //                    fireValueChange(true);
                    setValue(newValue);
                }

            }
        });
    }

    @Override
    public C getValue() {
        return selected;
    }

    @Override
    public void onReset(ET entity) {
        newInstanceForm.closePopup();
    }

    @Override
    public void onSave(ET entity) {
        // TODO, figure out something here, needs a listener/handler
        // getProvider().persist(elementType, newInstance);
        //        table.addItem(entity);
        //        selected.add(entity);

        C newValue = cloneSelected();
        newValue.add(entity);
        /*
         * Here we check the table for limit because the added entity could be equal to another added previously.
         * Since the table has a list container and selected has a map, they do not have the same behavior for equality.
         */

        //        cb.setEnabled(table.size() < limit);
        if (newInstanceForm != null) {
            newInstanceForm.closePopup();
        }
        // fire value change
        //        fireValueChange(true);
        setValue(newValue);

    }

    @Override
    protected Component initContent() {
        return verticalLayout;
    }

    @Override
    protected void doSetValue(C value) {
        selected = value;

        if (selected == null) {
            Class<C> clazz = getType();
            if (clazz != null && List.class.isAssignableFrom(clazz)) {
                selected = (C) new ArrayList<ET>();
            } else {
                selected = (C) new HashSet<ET>();
            }
        } else {
            containerType = (Class<C>) value.getClass();
        }
        final ArrayList<ET> arrayList = new ArrayList<>(availableOptions);
        arrayList.removeAll(selected);
        cb.clear();

        cb.setItems(arrayList);
        //        cb.getBic().fireItemSetChange();
        cb.setEnabled(selected.size() < limit);
        grid.setItems(new ArrayList<ET>(selected));

    }

    @SuppressWarnings("rawtypes")
    private Class<C> getType() {
        return containerType;
    }

    /**
     * Generates the tool cell content in the listing of selected items. By
     * default contains button to remove selection. Overridden implementation
     * can add other stuff there as well, like edit button.
     *
     * @param entity the entity for which the cell content is created
     * @return the content (String or Component)
     */
    protected Object getToolColumnContent(final ET entity) {
        Button button = new Button(VaadinIcons.MINUS);
        button.setDescription("Removes the selection from the list");
        button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        button.addClickListener(event -> removeSelectedOption(entity));
        button.setStyleName(ValoTheme.BUTTON_SMALL);
        return button;

    }

    /**
     * @param entity the entity to be removed from the selection
     */
    public void removeSelectedOption(ET entity) {
        //        cb.addOption(entity);
        //        table.removeItem(entity);
        //        selected.remove(entity);
        C newValue = cloneSelected();
        newValue.remove(entity);

        //        cb.setEnabled(selected.size() < limit);
        // fire value change
        //        fireValueChange(true);
        setValue(newValue);
    }

    public String getPlaceholder() {
        return cb.getPlaceholder();
    }

    public void setPlaceholder(String inputPrompt) {
        cb.setPlaceholder(inputPrompt);
    }

    /**
     * Sets a form which can be used to add new items to the selection. A button
     * to add new instances is displayed if this method is called.
     *
     * @param newInstanceForm the form
     */
    public void setNewInstanceForm(AbstractForm<ET> newInstanceForm) {
        this.newInstanceForm = newInstanceForm;
        if (newInstanceForm != null) {
            if (newInstanceBtn == null) {
                newInstanceBtn = new MButton(VaadinIcons.PLUS).withStyleName(ValoTheme.BUTTON_ICON_ONLY);
                newInstanceBtn.addClickListener(click -> addEntity(null));
                toprow.addComponent(newInstanceBtn);
            }
        } else if (newInstanceBtn != null) {
            toprow.removeComponent(newInstanceBtn);
            newInstanceBtn = null;
        }
    }

    public AbstractForm<ET> getNewInstanceForm() {
        return newInstanceForm;
    }

    protected String getDeleteButtonCaption() {
        return "-";
    }

    /**
     * @return the reference to the Table used by this field internally.
     * Modifying this object directly might cause odd behavior.
     */
    public Grid<ET> getGrid() {
        return grid;
    }

    protected void addEntity(String input) {

        ET newInstance = instantiator.apply(input);

        if (newInstanceForm != null) {
            String caption = "Add new " + elementType.getSimpleName();
            newInstanceForm.setEntity(newInstance);
            newInstanceForm.setSavedHandler(this);
            newInstanceForm.setResetHandler(this);
            Window w = newInstanceForm.openInModalPopup();
            w.setWidth("70%");
            w.setCaption(caption);
        } else {
            onSave(newInstance);
        }
    }

    protected ET instantiateOption(String stringInput) {
        try {
            return elementType.getConstructor().newInstance();
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException
                | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param options the list of options from which the sub set is selected
     * @return this
     */
    public DSSubSetSelector2 setItems(List<ET> options) {
        availableOptions = options;
        cb.setItems(new ArrayList<>(options));
        return this;
    }

    public String getNewEntityCaption() {
        return newInstanceBtn.getCaption();
    }

    public void setNewEntityCaption(String newEntityCaption) {
        newInstanceBtn.setCaption(newEntityCaption);
    }

    public void setCaptionGenerator(ItemCaptionGenerator<ET> cg) {
        cb.setItemCaptionGenerator(cg);
    }

    /**
     * With this method users can be allowed to add new entities directly via
     * the combobox used to select entities from the existing set.
     * <p>
     * Developer can decide what to do with the input string, by overriding the
     * instantiateOption method. Also override onSave method if you need to do
     * something specific with new entities (e.g. save to persistency context
     * and pass the persisted instance to the default action that adds it to
     * select and available options).
     * <p>
     * If a newInstanceForm is set, the created the entity is then shown for
     * further editing in the form.
     *
     * @param allowAddingNewItems true if hitting enter without suggestions
     * should add a new item.
     */
    public void setNewItemsAllowed(boolean allowAddingNewItems, Function<String, ET> instantiator) {
        //        cb.setNewItemsAllowed(allowAddingNewItems);
        if (instantiator != null) {
            this.instantiator = instantiator;
        }
        if (allowAddingNewItems) {
            cb.setNewItemHandler(stringInput -> addEntity(stringInput));
        } else {
            cb.setNewItemHandler(null);
        }
    }

    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit of elements added to the SubSetSelector. Setting the limit
     * or adding elements will trigger the combo box availability.
     *
     * @param limit the maximum number of selected elements (collection size)
     */
    public void setLimit(int limit) {
        if (limit < 0) {
            this.limit = Integer.MAX_VALUE;
        } else {
            this.limit = limit;
        }
        cb.setEnabled(selected.size() < limit);
    }

    public ComboBox<ET> getComboBox() {
        return cb;
    }

    private C cloneSelected() {
        Class<C> clazz = getType();
        C collection = null;
        if (clazz != null && List.class.isAssignableFrom(clazz)) {
            collection = (C) new ArrayList<ET>(selected);
        } else {
            collection = (C) new HashSet<ET>(selected);
        }
        return collection;
    }

}
