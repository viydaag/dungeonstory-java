package com.dungeonstory.ui.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.viritin.button.ConfirmButton;

import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.field.listener.ElementAddedListener;
import com.dungeonstory.ui.field.listener.ElementRemovedListener;
import com.vaadin.data.Binder;
import com.vaadin.fluent.ui.FButton;
import com.vaadin.fluent.ui.FGridLayout;
import com.vaadin.fluent.ui.FVerticalLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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
 * <li>The element type needs to have an empty parameter constructor or user
 * must provide an Instantiator.
 * </ul>
 *
 * Elements in the edited collection are modified with Binder. Fields
 * should defined in a class. A simple usage example for editing
 * List&gt;Address&lt; addresses:
 * <pre><code>
 *  public static class AddressRow {
 *      ComboBox type = new ComboBox();
 *      TextField street = new TextField();
 *      TextField city = new TextField();
 *      TextField zipCode = new TextField();
 *  }
 *
 *  public static class PersonForm&lt;Person&gt; extends AbstractForm {
 *      private final ElementCollectionField&lt;Address&gt; addresses
 *              = new ElementCollectionField&lt;Address&gt;(Address.class,
 *                      AddressRow.class).withCaption("Addresses");
 *
 * </code></pre>
 *
 * <p>
 * Components in row model class don't need to match properties in the edited
 * entity. So you can add "custom columns" just by introducing them in your
 * editor row.
 * <p>
 * By default the field always contains an empty instance to create new rows. If
 * instances are added with some other method (or UI shouldn't add them at all),
 * you can configure this with setAllowNewItems. Deletions can be configured
 * with setAllowRemovingItems.
 * <p>
 * If developer needs to do some additional logic during element
 * addition/removal, one can subscribe to related events using
 * addElementAddedListener/addElementRemovedListener.
 *
 *
 * @author Jean-Christophe Fortier
 * @param <ET> The type in the entity collection. The type must have empty
 * parameter constructor or you have to provide Instantiator.
 *
 */
public class ElementCollectionField<ET> extends AbstractElementCollection<ET, List<ET>> {

    private static final long serialVersionUID = 8573373104105052804L;

    // Visible items on the page = value + 1 (empty)
    List<ET> items = new ArrayList<>();

    boolean inited = false;

    protected VerticalLayout mainLayout;
    protected GridLayout     gridLayout;

    private boolean          visibleHeaders = true;
    private boolean          requireVerificationForRemoval;
    private AbstractForm<ET> popupEditor;

    public ElementCollectionField(Class<ET> elementType, Class<?> formType) {
        this(elementType, null, formType);
    }

    public ElementCollectionField(Class<ET> elementType, Instantiator<ET> i, Class<?> formType) {
        super(elementType, i, formType);
        items = new ArrayList<>();
        mainLayout = new FVerticalLayout().withMargin(false);
        gridLayout = new FGridLayout().withSpacing(true);
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

        Binder<ET> beanBinder = getBinderFor(v);
        for (String property : getVisibleProperties()) {
            Optional<Binder.Binding<ET, ?>> binding = beanBinder.getBinding(property);
            Component c = null;
            if (!binding.isPresent()) {
                c = getComponentFor(v, property.toString());
                Logger.getLogger(ElementCollectionField.class.getName()).log(Level.WARNING, "No editor field for{0}", property);
            } else {
                c = (Component) binding.get().getField();
            }
            gridLayout.addComponent(c);
            gridLayout.setComponentAlignment(c, Alignment.MIDDLE_LEFT);
        }
        if (getPopupEditor() != null) {
            FButton b = new FButton(VaadinIcons.EDIT).withStyleName(ValoTheme.BUTTON_ICON_ONLY).withClickListener(event -> editInPopup(v));
            gridLayout.addComponent(b);
        }
        if (isAllowRemovingItems()) {
            gridLayout.addComponent(createRemoveButton(v));
        }
        if (!isAllowEditItems()) {
            beanBinder.setReadOnly(true);
        }
    }

    protected Component createRemoveButton(final ET v) {
        Button b;
        if (requireVerificationForRemoval) {
            b = new ConfirmButton();
        } else {
            b = new Button();
        }
        b.setIcon(VaadinIcons.TRASH);
        b.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        b.addStyleName(ValoTheme.BUTTON_DANGER);
        b.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 5019806363620874205L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                removeElement(v);
            }
        });
        return b;
    }

    @Override
    public void removeInternalElement(ET v) {
        int index = itemsIdentityIndexOf(v);
        items.remove(index);
        int row = index + 1;
        gridLayout.removeRow(row);
    }

    @Override
    public VerticalLayout getLayout() {
        return mainLayout;
    }

    @Override
    public void setPersisted(ET v, boolean persisted) {
        int row = itemsIdentityIndexOf(v) + 1;
        if (isAllowRemovingItems()) {
            Button c = (Button) gridLayout.getComponent(gridLayout.getColumns() - 1, row);
            if (persisted) {
                c.setDescription(getDeleteElementDescription());
            } else {
                c.setDescription(getDisabledDeleteElementDescription());
            }
            c.setEnabled(persisted);
        }
    }

    private int itemsIdentityIndexOf(Object o) {
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) == o) {
                return index;
            }
        }
        return -1;
    }

    private void ensureInited() {
        if (!inited) {
            value = new ArrayList<>();
            mainLayout.addComponent(statusLayout);
            mainLayout.addComponent(gridLayout);
            int columns = getVisibleProperties().size();
            if (isAllowRemovingItems()) {
                columns++;
            }
            if (getPopupEditor() != null) {
                columns++;
            }
            gridLayout.setColumns(columns);

            if (visibleHeaders) {
                for (Object property : getVisibleProperties()) {
                    Component header = createHeader(property);
                    gridLayout.addComponent(header);
                }
                if (isAllowRemovingItems()) {
                    // leave last header slot empty, "actions" colunn
                    gridLayout.newLine();
                }
            }
            inited = true;
        }
    }

    /**
     * Creates the header for given property. By default a simple Label is used.
     * Override this method to style it or to replace it with something more
     * complex.
     *
     * @param property the property for which header is to be created.
     * @return the component used for header
     */
    protected Component createHeader(Object property) {
        Label header = new Label(getPropertyHeader(property.toString()));
        header.setWidthUndefined();
        return header;
    }

    public ElementCollectionField<ET> withEditorInstantiator(Instantiator<?> instantiator) {
        setEditorInstantiator(instantiator);
        return this;
    }

    public ElementCollectionField<ET> withNewEditorInstantiator(EditorInstantiator<?, ET> instantiator) {
        setNewEditorInstantiator(instantiator);
        return this;
    }

    public ElementCollectionField<ET> withVisibleHeaders(boolean visibleHeaders) {
        this.visibleHeaders = visibleHeaders;
        return this;
    }

    @Override
    public void clear() {
        if (inited) {
            super.clear();
            items.clear();
            int rows = inited ? 1 : 0;
            while (gridLayout.getRows() > rows) {
                gridLayout.removeRow(rows);
            }
        }

    }

    public String getDisabledDeleteElementDescription() {
        return disabledDeleteThisElementDescription;
    }

    public void setDisabledDeleteThisElementDescription(String disabledDeleteThisElementDescription) {
        this.disabledDeleteThisElementDescription = disabledDeleteThisElementDescription;
    }

    private String disabledDeleteThisElementDescription = "Fill this row to add a new element, currently ignored";

    public String getDeleteElementDescription() {
        return deleteThisElementDescription;
    }

    private String deleteThisElementDescription = "Delete this element";

    public void setDeleteThisElementDescription(String deleteThisElementDescription) {
        this.deleteThisElementDescription = deleteThisElementDescription;
    }

    @Override
    public void onElementAdded() {
        if (isAllowNewItems()) {
            newInstance = createInstance();
            addInternalElement(newInstance);
            setPersisted(newInstance, false);
        }
    }

    @Override
    public ElementCollectionField<ET> setPropertyHeader(String propertyName, String propertyHeader) {
        super.setPropertyHeader(propertyName, propertyHeader);
        return this;
    }

    @Override
    public ElementCollectionField<ET> setVisibleProperties(List<String> properties, List<String> propertyHeaders) {
        super.setVisibleProperties(properties, propertyHeaders);
        return this;
    }

    @Override
    public ElementCollectionField<ET> setVisibleProperties(List<String> properties) {
        super.setVisibleProperties(properties);
        return this;
    }

    @Override
    public ElementCollectionField<ET> setAllowNewElements(boolean allowNewItems) {
        super.setAllowNewElements(allowNewItems);
        return this;
    }

    @Override
    public ElementCollectionField<ET> setAllowRemovingItems(boolean allowRemovingItems) {
        super.setAllowRemovingItems(allowRemovingItems);
        return this;
    }

    @Override
    public ElementCollectionField<ET> withCaption(String caption) {
        super.withCaption(caption);
        return this;
    }

    @Override
    public ElementCollectionField<ET> addElementRemovedListener(ElementRemovedListener<ET> listener) {
        super.addElementRemovedListener(listener);
        return this;
    }

    @Override
    public ElementCollectionField<ET> addElementAddedListener(ElementAddedListener<ET> listener) {
        super.addElementAddedListener(listener);
        return this;
    }

    /**
     * Expands the column with given property id
     *
     * @param propertyId the id of column that should be expanded in the UI
     * @return the element collection field
     */
    public ElementCollectionField<ET> expand(String... propertyId) {
        for (String propertyId1 : propertyId) {
            int index = getVisibleProperties().indexOf(propertyId1);
            if (index == -1) {
                throw new IllegalArgumentException("The expanded property must available");
            }
            gridLayout.setColumnExpandRatio(index, 1);
        }
        if (gridLayout.getWidth() == -1) {
            gridLayout.setWidth(100, Unit.PERCENTAGE);
        }
        // TODO should also make width of elements automatically 100%, both
        // existing and added, now obsolete config needed for row model
        return this;
    }

    public ElementCollectionField<ET> withFullWidth() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    public ElementCollectionField<ET> withId(String id) {
        setId(id);
        return this;
    }

    public ElementCollectionField<ET> setRequireVerificationForRemoving(boolean requireVerification) {
        requireVerificationForRemoval = requireVerification;
        return this;
    }

    public AbstractForm<ET> getPopupEditor() {
        return popupEditor;
    }

    /**
     * Method to set form to allow editing more properties than it would be
     * convenient inline.
     *
     * @param newPopupEditor the popup editor to be used to edit instances
     */
    public void setPopupEditor(AbstractForm<ET> newPopupEditor) {
        this.popupEditor = newPopupEditor;
        if (newPopupEditor != null) {
            newPopupEditor.setSavedHandler(new AbstractForm.SavedHandler<ET>() {
                private static final long serialVersionUID = 389618696563816566L;

                @Override
                public void onSave(ET entity) {
                    Binder<ET> beanBinder = getBinderFor(entity);
                    beanBinder.writeBeanIfValid(entity);
                    // TODO refresh binding
                    popupEditor.getPopup().close();
                }
            });
        }
    }

    /**
     * Opens a (possibly configured) popup editor to edit given entity.
     * 
     * @param entity the entity to be edited
     */
    public void editInPopup(ET entity) {
        getPopupEditor().setEntity(entity);
        getPopupEditor().openInModalPopup();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<List<ET>> getContainerType() {
        return (Class<List<ET>>) items.getClass();
    }

}
