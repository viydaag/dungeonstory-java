package com.dungeonstory.ui.field;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import org.vaadin.viritin.button.MButton;

import com.dungeonstory.ui.field.listener.ElementAddedListener;
import com.dungeonstory.ui.field.listener.ElementRemovedListener;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

/**
 * A field suitable for editing collection of referenced objects tied to parent
 * object only. E.g. OneToMany/ElementCollection fields in JPA world.
 * <p>
 * Some features/restrictions:
 * <ul>
 * <li>The field is valid when all elements are valid.
 * <li>The field is always non buffered
 * <li>The element type needs to have an empty paremeter constructor or user
 * must provide an Instantiator.
 * </ul>
 *
 * Elements in the edited collection are modified with BeanFieldGroup. Fields
 * should defined in a class. A simple usage example for editing
 * List&gt;Address&lt; adresses:
 * <pre><code>
 *  public static class AddressRow {
 *      EnumSelect type = new EnumSelect();
 *      FTextField street = new FTextField();
 *      FTextField city = new FTextField();
 *      FTextField zipCode = new FTextField();
 *  }
 *
 *  public static class PersonForm&lt;Person&gt; extends AbstractForm {
 *      private final ElementCollectionGrid&lt;Address&gt; addresses
 *              = new ElementCollectionGrid&lt;Address&gt;(Address.class,
 *                      AddressRow.class).withCaption("Addressess");
 *
 * </code></pre>
 *
 * <p>
 * By default the field contains a button to add new elements. If instances are
 * added with some other method (or UI shouldn't add them at all), you can
 * configure this with setAllowNewItems. Deletions can be configured with
 * setAllowRemovingItems.
 * <p>
 *
 * @param <ET> The type in the entity collection. The type must have empty
 * parameter constructor or you have to provide Instantiator.
 */
public class ElementCollectionGrid<ET> extends AbstractElementCollection<ET, List<ET>> {

    private static final long serialVersionUID = 3979170822301544331L;

    //    private MTable<ET> table;
    private Grid<ET>             grid;
    private List<ET>             items = new ArrayList<ET>();
    private ListDataProvider<ET> dataProvider;
    private Button               addButton;

    //    private MButton addButton = new MButton(VaadinIcons.PLUS, new Button.ClickListener() {
    //
    //        private static final long serialVersionUID = 6115218255676556647L;
    //
    //        @Override
    //        public void buttonClick(Button.ClickEvent event) {
    //            addElement(createInstance());
    //        }
    //    });

    private IdentityHashMap<ET, MButton> elementToDelButton = new IdentityHashMap<>();

    boolean inited = false;

    private VerticalLayout layout = new VerticalLayout();

    private String[] deleteElementStyles;

    private String disabledDeleteThisElementDescription = "Fill this row to add a new element, currently ignored";

    public ElementCollectionGrid(Class<ET> elementType, Class<?> formType) {
        super(elementType, formType);
    }

    public ElementCollectionGrid(Class<ET> elementType, Instantiator<ET> i, Class<?> formType) {
        super(elementType, i, formType);
    }

    @Override
    public void attach() {
        super.attach();
        ensureInited();
    }

    @Override
    public void addInternalElement(final ET v) {
        ensureInited();
        items.add(v);
        dataProvider.refreshAll();
    }

    @Override
    public void removeInternalElement(ET v) {
        items.remove(v);
        elementToDelButton.remove(v);
        dataProvider.refreshAll();
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    public Button getAddButton() {
        return addButton;
    }

    /**
     * @return the Table used in the implementation. Configure carefully.
     */
    public Grid<ET> getGrid() {
        return grid;
    }

    @Override
    public void setPersisted(ET v, boolean persisted) {
        // NOP
    }

    private void ensureInited() {
        if (!inited) {
            layout.setMargin(false);
            //            setHeight("300px");

            grid = new Grid<ET>(getElementType());
            grid.setWidth(100, Unit.PERCENTAGE);
            grid.setHeightUndefined();
            dataProvider = DataProvider.ofCollection(items);
            grid.setDataProvider(dataProvider);

            addButton = new Button(VaadinIcons.PLUS);
            addButton.addClickListener(click -> addElement(createInstance()));

            grid.removeAllColumns();
            //TODO : add component for each field column with Vaadin 8.1
            for (String propertyName : getVisibleProperties()) {
                grid.addColumn(propertyName).setCaption(getPropertyHeader(propertyName));
            }

            //TODO : add better button component with icon with Vaadin 8.1
            if (isAllowRemovingItems()) {
                grid.addColumn(entity -> "-", new ButtonRenderer<ET>(clickEvent -> removeElement(clickEvent.getItem()))).setCaption("")
                        .setId("Remove");
            }

            //            for (Object propertyId : getVisibleProperties()) {
            //                grid.addGeneratedColumn(propertyId, new Table.ColumnGenerator() {
            //
            //                    private static final long serialVersionUID = 3637140096807147630L;
            //
            //                    @Override
            //                    public Object generateCell(Table source, Object itemId, Object columnId) {
            //                        Binder<ET> fg = getBinderFor((ET) itemId);
            //                        if (!isAllowEditItems()) {
            //                            fg.setReadOnly(true);
            //                        }
            //                        Component component = (Component) fg.getBinding((String) columnId).get().getField();
            //                        if (component == null) {
            //                            getComponentFor((ET) itemId, columnId.toString());
            //                        }
            //                        return component;
            //                    }
            //                });
            //
            //            }
            //            ArrayList<Object> cols = new ArrayList<Object>(getVisibleProperties());

            //            if (isAllowRemovingItems()) {
            //                grid.addGeneratedColumn("__ACTIONS", new Table.ColumnGenerator() {
            //
            //                    private static final long serialVersionUID = 492486828008202547L;
            //
            //                    @Override
            //                    public Object generateCell(Table source, final Object itemId, Object columnId) {
            //
            //                        MButton b = new MButton(VaadinIcons.TRASH).withListener(new Button.ClickListener() {
            //
            //                            private static final long serialVersionUID = -1257102620834362724L;
            //
            //                            @Override
            //                            public void buttonClick(Button.ClickEvent event) {
            //                                removeElement((ET) itemId);
            //                            }
            //                        }).withStyleName(ValoTheme.BUTTON_ICON_ONLY);
            //                        b.setDescription(getDeleteElementDescription());
            //
            //                        if (getDeleteElementStyles() != null) {
            //                            for (String style : getDeleteElementStyles()) {
            //                                b.addStyleName(style);
            //                            }
            //                        }
            //
            //                        elementToDelButton.put((ET) itemId, b);
            //                        return b;
            //                    }
            //                });
            //                grid.setColumnHeader("__ACTIONS", "");
            //                cols.add("__ACTIONS");
            //            }

            //            grid.setVisibleColumns(cols.toArray());
            //            for (Object property : getVisibleProperties()) {
            //                grid.setColumnHeader(property, getPropertyHeader(property.toString()));
            //            }
            layout.addComponent(grid);
            if (isAllowNewItems()) {
                layout.addComponent(addButton);
            }
            inited = true;
        }
    }

    @Override
    public void clear() {
        if (inited) {
            items.clear();
            elementToDelButton.clear();
            dataProvider.refreshAll();
        }
    }

    public String getDisabledDeleteElementDescription() {
        return disabledDeleteThisElementDescription;
    }

    public void setDisabledDeleteThisElementDescription(String disabledDeleteThisElementDescription) {
        this.disabledDeleteThisElementDescription = disabledDeleteThisElementDescription;
    }

    public String getDeleteElementDescription() {
        return deleteThisElementDescription;
    }

    private String deleteThisElementDescription = "Delete this element";

    public void setDeleteThisElementDescription(String deleteThisElementDescription) {
        this.deleteThisElementDescription = deleteThisElementDescription;
    }

    public String[] getDeleteElementStyles() {
        return deleteElementStyles;
    }

    public void addDeleteElementStyles(String... deleteElementStyles) {
        this.deleteElementStyles = deleteElementStyles;
    }

    @Override
    public void onElementAdded() {
        // NOP
    }

    @Override
    public ElementCollectionGrid<ET> setPropertyHeader(String propertyName, String propertyHeader) {
        super.setPropertyHeader(propertyName, propertyHeader);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> setVisibleProperties(List<String> properties, List<String> propertyHeaders) {
        super.setVisibleProperties(properties, propertyHeaders);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> setVisibleProperties(List<String> properties) {
        super.setVisibleProperties(properties);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> setAllowNewElements(boolean allowNewItems) {
        super.setAllowNewElements(allowNewItems);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> setAllowRemovingItems(boolean allowRemovingItems) {
        super.setAllowRemovingItems(allowRemovingItems);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> withCaption(String caption) {
        super.withCaption(caption);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> addElementRemovedListener(ElementRemovedListener<ET> listener) {
        super.addElementRemovedListener(listener);
        return this;
    }

    @Override
    public ElementCollectionGrid<ET> addElementAddedListener(ElementAddedListener<ET> listener) {
        super.addElementAddedListener(listener);
        return this;
    }

    public ElementCollectionGrid<ET> withEditorInstantiator(Instantiator<?> instantiator) {
        setEditorInstantiator(instantiator);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<List<ET>> getContainerType() {
        return (Class<List<ET>>) items.getClass();
    }

}