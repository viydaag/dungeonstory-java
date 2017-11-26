package com.dungeonstory.ui.field;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dungeonstory.ui.field.event.ElementAddedEvent;
import com.dungeonstory.ui.field.event.ElementRemovedEvent;
import com.dungeonstory.ui.field.listener.ElementAddedListener;
import com.dungeonstory.ui.field.listener.ElementRemovedListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.dnd.EffectAllowed;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.dnd.DragSourceExtension;
import com.vaadin.ui.dnd.DropTargetExtension;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

/**
 * 
 * A two column selection field with draggable elements.
 * The value type is a collection of objects.
 * Selected elements appears in the right column and available elements are in the left column.
 *
 * @param <ET> type of each element
 * @param <C> field value type (extends Collection)
 */
public class SubSetSelectorDraggable<ET, C extends Collection<ET>> extends CustomField<C> {

    private static final long serialVersionUID = -3340932901781677504L;

    private C                                          selected;
    private Class<C>                                   containerType;
    private VerticalLayout                             verticalLayout;
    private HorizontalSplitPanel                       splitPanel;
    private VerticalLayout                             availableLayout;
    private VerticalLayout                             valueLayout;
    private List<ET>                                   availableOptions;
    private List<DragSourceExtension<DraggableButton>> availableDragSource;
    private int                                        limit = Integer.MAX_VALUE;
    private List<String>                               buttonStyles;
    private int                                        buttonHeight;

    private ItemCaptionGenerator<ET> itemCaptionGenerator = (pojo) -> pojo.toString();
    private ItemCaptionGenerator<ET> descriptionGenerator = (pojo) -> "";

    public SubSetSelectorDraggable() {
        this(null, null);
    }

    public SubSetSelectorDraggable(String caption, List<ET> options) {
        availableLayout = new VerticalLayout();
        availableLayout.setMargin(false);
        availableLayout.setSpacing(false);

        valueLayout = new VerticalLayout();
        valueLayout.setMargin(false);
        valueLayout.setSpacing(false);

        splitPanel = new HorizontalSplitPanel(availableLayout, valueLayout);
        splitPanel.setMinSplitPosition(20, Unit.PERCENTAGE);
        splitPanel.setMaxSplitPosition(80, Unit.PERCENTAGE);
        splitPanel.setCaption(caption);

        verticalLayout = new VerticalLayout();
        verticalLayout.setHeight(100, Unit.PERCENTAGE);
        verticalLayout.addComponentsAndExpand(splitPanel);

        availableDragSource = new ArrayList<>();
        buttonStyles = new ArrayList<>();

        addDropTarget(valueLayout, false);
        addDropTarget(availableLayout, true);

        setHeight(500, Unit.PIXELS);
        setDraggableHeight(45);
        valueLayout.setHeight(getDraggableAreaHeight(), Unit.PIXELS);
        setStyle();

        setItems(options);
    }

    @SuppressWarnings("unchecked")
    private void addDropTarget(VerticalLayout layout, boolean remove) {
        // make the layout accept drops
        DropTargetExtension<VerticalLayout> dropTarget = new DropTargetExtension<>(layout);

        // the drop effect must match the allowed effect in the drag source for a successful drop
        dropTarget.setDropEffect(DropEffect.MOVE);

        // catch the drops
        dropTarget.addDropListener(event -> {
            // if the drag source is in the same UI as the target
            Optional<AbstractComponent> dragSource = event.getDragSourceComponent();
            if (dragSource.isPresent() && dragSource.get() instanceof SubSetSelectorDraggable.DraggableButton) {
                if (remove) {
                    event.getDragData().ifPresent(data -> removeSelectedOption((ET) data));
                } else {
                    event.getDragData().ifPresent(data -> addSelectedOption((ET) data));
                }
            }
        });
    }

    private void addSelectedOption(ET data) {
        C newValue = cloneSelected();
        newValue.add(data);
        fireEvent(new ElementAddedEvent<ET>(SubSetSelectorDraggable.this, data));
        setValue(newValue);
    }

    /**
     * @param entity the entity to be removed from the selection
     */
    public void removeSelectedOption(ET entity) {
        C newValue = cloneSelected();
        newValue.remove(entity);
        fireEvent(new ElementRemovedEvent<ET>(this, entity));
        setValue(newValue);
    }

    @Override
    public C getValue() {
        return selected;
    }

    @Override
    protected Component initContent() {
        return verticalLayout;
    }

    @SuppressWarnings("unchecked")
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

        createAvailableButtons(arrayList);

        checkLimit();

        createValueButtons();

    }

    private Class<C> getType() {
        return containerType;
    }

    /**
     * @param options the list of options from which the sub set is selected
     * @return this
     */
    public SubSetSelectorDraggable<ET, C> setItems(List<ET> options) {
        availableOptions = options;
        createAvailableButtons(options);

        return this;
    }

    private void createAvailableButtons(List<ET> options) {
        removeAvailableDragSourceExtensions();
        availableDragSource.clear();
        availableLayout.removeAllComponents();
        if (options != null) {
            for (ET option : options) {
                DraggableButton button = new DraggableButton(option);
                availableLayout.addComponent(button);
                availableDragSource.add(button.getDragSourceExtension());
            }
        }
    }

    private void createValueButtons() {
        valueLayout.removeAllComponents();
        for (ET option : selected) {
            DraggableButton button = new DraggableButton(option);
            valueLayout.addComponent(button);
        }
        if (valueLayout.getHeight() != -1) {
            if (valueLayout.getHeight() < valueLayout.getComponentCount() * buttonHeight) {
                valueLayout.setHeightUndefined();
            } else if (valueLayout.getHeight() != getDraggableAreaHeight()) {
                valueLayout.setHeight(getDraggableAreaHeight(), Unit.PIXELS);
            }
        } else {
            if (getDraggableAreaHeight() > valueLayout.getComponentCount() * buttonHeight) {
                valueLayout.setHeight(getDraggableAreaHeight(), Unit.PIXELS);
            }
        }
    }

    /**
     * Disable the left column elements for drag and drop.
     */
    public void removeAvailableDragSourceExtensions() {
        availableDragSource.stream().forEach(source -> {
            if (source.getParent() != null) {
                source.remove();
            }
        });
    }

    public void setCaptionGenerator(ItemCaptionGenerator<ET> cg) {
        itemCaptionGenerator = cg;
    }

    public void setDescriptionGenerator(ItemCaptionGenerator<ET> cg) {
        descriptionGenerator = cg;
    }

    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit of elements added to the SubSetSelectorDraggable. Setting the limit
     * or adding elements will trigger the selector availability.
     *
     * @param limit the maximum number of selected elements (collection size)
     */
    public void setLimit(int limit) {
        if (limit < 0) {
            this.limit = Integer.MAX_VALUE;
        } else {
            this.limit = limit;
        }

        checkLimit();
    }

    private void checkLimit() {
        if (selected != null && selected.size() >= limit) {
            removeAvailableDragSourceExtensions();
        }
    }
    
    @Override
    public void setCaption(String caption) {
        splitPanel.setCaption(caption);
    }

    @SuppressWarnings("unchecked")
    private C cloneSelected() {
        Class<C> clazz = getType();
        C collection = null;
        if (clazz != null && List.class.isAssignableFrom(clazz)) {
            collection = (C) new ArrayList<ET>(selected);
        } else {
            collection = (C) new HashSet<ET>();
        }
        return collection;
    }

    /**
     * Add some styles to each draggable element.
     * @param style
     */
    public void setDraggableStyle(String... style) {
        buttonStyles.addAll(Arrays.asList(style));
    }

    /**
     * Set the draggable element height in pixels.
     * @param height
     */
    public void setDraggableHeight(int height) {
        if (height > 0) {
            buttonHeight = height;
        } else {
            buttonHeight = -1; //undefined
        }
    }

    private float getDraggableAreaHeight() {
        return getHeight() - (2 * 37);
    }

    private void setStyle() {
        Page.Styles styles = Page.getCurrent().getStyles();

        styles.add(".v-verticallayout-drag-center {background-color: rgba(169,209,255,.6);}");
    }

    private class DraggableButton extends Button {

        private static final long serialVersionUID = -4312111919635549026L;

        private DragSourceExtension<DraggableButton> dragSource;

        public DraggableButton(ET pojo) {
            super();
            setCaption(itemCaptionGenerator.apply(pojo));
            setWidth("100%");
            if (!buttonStyles.isEmpty()) {
                setStyleName(buttonStyles.stream().collect(Collectors.joining(" ")));
            } else {
                addStyleName(ValoTheme.BUTTON_LARGE);
                addStyleName(ValoTheme.BUTTON_QUIET);
            }
            setData(pojo);
            setDescription(descriptionGenerator.apply(pojo));
            setHeight(buttonHeight, Unit.PIXELS);

            dragSource = new DragSourceExtension<>(this);

            // set the allowed effect
            dragSource.setEffectAllowed(EffectAllowed.MOVE);

            dragSource.addDragStartListener(event -> dragSource.setDragData(pojo));
            dragSource.addDragEndListener(event -> dragSource.setDragData(null));
        }

        @SuppressWarnings("unchecked")
        @Override
        public ET getData() {
            return (ET) super.getData();
        }

        public DragSourceExtension<DraggableButton> getDragSourceExtension() {
            return dragSource;
        }
    }

    private static final Method addedMethod;
    private static final Method removedMethod;

    static {
        addedMethod = ReflectTools.findMethod(ElementAddedListener.class, "elementAdded", ElementAddedEvent.class);
        removedMethod = ReflectTools.findMethod(ElementRemovedListener.class, "elementRemoved", ElementRemovedEvent.class);
    }

    public void addElementAddedListener(ElementAddedListener<ET> listener) {
        addListener(ElementAddedEvent.class, listener, addedMethod);
    }

    public void addElementRemovedListener(ElementRemovedListener<ET> listener) {
        addListener(ElementRemovedEvent.class, listener, removedMethod);
    }

}
