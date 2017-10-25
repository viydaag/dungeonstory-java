package com.dungeonstory.ui.field;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.ui.field.listener.ElementAddedListener;
import com.dungeonstory.ui.field.listener.ElementRemovedListener;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.fluent.ui.FButton;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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
 * Elements in the edited collection are modified with Binder. Fields
 * should be defined in a class. A simple usage example for editing
 * List&gt;Address&lt; addresses:
 * <pre><code>
 *  public static class AddressRow {
 *      ComboBox type = new ComboBox();
 *      TextField street = new FTextField();
 *      TextField city = new FTextField();
 *      TextField zipCode = new FTextField();
 *  }
 *
 *  public static class PersonForm&lt;Person&gt; extends AbstractForm {
 *      private final ElementCollectionGrid&lt;Address&gt; addresses
 *              = new ElementCollectionGrid&lt;Address&gt;(Address.class,
 *                      AddressRow.class).withCaption("Addresses");
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

    private Grid<ET>             grid;
    private ListDataProvider<ET> dataProvider;
    private Button               addButton;

    boolean inited = false;

    private VerticalLayout layout = new VerticalLayout();

    private String[] deleteElementStyles;

    private String disabledDeleteThisElementDescription = "Fill this row to add a new element, currently ignored";

    public ElementCollectionGrid(Class<ET> elementType, Class<?> formType) {
        this(elementType, null, formType);
    }

    public ElementCollectionGrid(Class<ET> elementType, Instantiator<ET> i, Class<?> formType) {
        super(elementType, i, formType);
        setShowStatusLabel(true);
    }

    @Override
    public void attach() {
        super.attach();
        ensureInited();
    }

    @Override
    public void addInternalElement(final ET v) {
        ensureInited();
        dataProvider.refreshAll();
    }

    @Override
    public void removeInternalElement(ET v) {
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
            value = new ArrayList<ET>();
            layout.setMargin(false);
            layout.addComponent(statusLayout);

            grid = new Grid<ET>();
            grid.setWidth(100, Unit.PERCENTAGE);
            grid.setHeightUndefined();
            
            dataProvider = DataProvider.ofCollection(value);
            grid.setDataProvider(dataProvider);

            addButton = new Button(VaadinIcons.PLUS);
            addButton.addClickListener(click -> addElement(createInstance()));

            //generate all columns with proper component
            for (String propertyName : getVisibleProperties()) {
                grid.addComponentColumn(pojo -> {
                    Binder<ET> binder = getBinderFor(pojo);
                    if (!isAllowEditItems()) {
                        binder.setReadOnly(true);
                    }
                    Component component = (Component) binder.getBinding(propertyName).get().getField();
                    if (component == null) {
                        getComponentFor(pojo, propertyName);
                    }
                    return component;
                }).setCaption(getPropertyHeader(propertyName));

                grid.setRowHeight(40);
            }

            if (isAllowRemovingItems()) {
                grid.addComponentColumn(entity -> createDeleteButton(entity)).setWidth(75).setId("Remove");
            }

            layout.addComponent(grid);
            if (isAllowNewItems()) {
                layout.addComponent(addButton);
            }

            inited = true;
        }
    }

    protected FButton createDeleteButton(ET entity) {
        FButton button = new FButton(VaadinIcons.TRASH).withClickListener(event -> removeElement(entity))
                                                       .withStyleName(ValoTheme.BUTTON_ICON_ONLY)
                                                       .withDescription(getDeleteElementDescription());

        if (getDeleteElementStyles() != null) {
            for (String style : getDeleteElementStyles()) {
                button.addStyleName(style);
            }
        }

        return button;
    }

    @Override
    public void addElement(ET instance) {
        super.addElement(instance);
        grid.scrollToEnd();
    }

    @Override
    public void clear() {
        if (inited) {
            super.clear();
            dataProvider.refreshAll();
        }
    }
    
    @Override
    protected boolean setValue(List<ET> value, boolean userOriginated) {
        boolean result = super.setValue(value, userOriginated);
        dataProvider.refreshAll();
        return result;
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

    private String deleteThisElementDescription = Messages.getInstance().getMessage("grid.button.delete.description");

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
        //nothing
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
        return (Class<List<ET>>) value.getClass();
    }

}