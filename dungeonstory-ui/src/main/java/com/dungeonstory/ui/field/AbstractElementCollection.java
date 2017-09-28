package com.dungeonstory.ui.field;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dungeonstory.ui.field.event.ElementAddedEvent;
import com.dungeonstory.ui.field.event.ElementRemovedEvent;
import com.dungeonstory.ui.field.listener.ElementAddedListener;
import com.dungeonstory.ui.field.listener.ElementRemovedListener;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatusHandler;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.HasValue;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.ValidationResult;
import com.vaadin.fluent.ui.FLabel;
import com.vaadin.fluent.ui.FVerticalLayout;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.util.SharedUtil;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

/**
 * A superclass for fields suitable for editing collection of referenced objects tied to parent
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
 * @author Jean-Christophe Fortier
 * @param <ET> The type in the entity collection. The type must have empty
 * parameter constructor or you have to provide Instantiator.
 */
public abstract class AbstractElementCollection<ET, CT extends Collection<ET>> extends CustomField<CT> {

    private static final long serialVersionUID = 3667284589764781794L;

    private static final Method addedMethod;
    private static final Method removedMethod;

    static {
        addedMethod = ReflectTools.findMethod(ElementAddedListener.class, "elementAdded", ElementAddedEvent.class);
        removedMethod = ReflectTools.findMethod(ElementRemovedListener.class, "elementRemoved", ElementRemovedEvent.class);
    }

    public AbstractElementCollection<ET, CT> addElementAddedListener(ElementAddedListener<ET> listener) {
        addListener(ElementAddedEvent.class, listener, addedMethod);
        return this;
    }

    public AbstractElementCollection<ET, CT> addElementRemovedListener(ElementRemovedListener<ET> listener) {
        addListener(ElementRemovedEvent.class, listener, removedMethod);
        return this;
    }

    private static ValidatorFactory factory;
    private transient Validator     javaxBeanValidator;

    private Instantiator<ET>          instantiator;
    private Instantiator<?>           oldEditorInstantiator;
    private EditorInstantiator<?, ET> newEditorInstantiator;

    private final Class<ET> elementType;

    private final Class<?> editorType;
    protected ET           newInstance;

    private List<String> visibleProperties;
    private boolean      allowNewItems      = true;
    private boolean      allowRemovingItems = true;
    private boolean      allowEditItems     = true;

    protected CT value;

    protected FVerticalLayout statusLayout;
    private boolean           showStatusLabel = false;

    StatusChangeListener scl = new StatusChangeListener() {

        private static final long serialVersionUID = 2255160434584741700L;

        @SuppressWarnings("unchecked")
        @Override
        public void statusChange(StatusChangeEvent event) {
            ET bean = (ET) event.getBinder().getBean();
            final Binder<?> binder = event.getBinder();
            final boolean valid = binder.isValid();
            if (bean == newInstance) {

                if (!valid || !isValidBean(bean)) {
                    return;
                }
                getAndEnsureValue().add(newInstance);
                fireEvent(new ElementAddedEvent<ET>(AbstractElementCollection.this, newInstance));
                setPersisted(newInstance, true);
                onElementAdded();
            }

            fireValueChange();
        }

        private void fireValueChange() {
            fireEvent(new ValueChangeEvent<CT>(AbstractElementCollection.this, null, true));
        }
    };

    public AbstractElementCollection(Class<ET> elementType, Class<?> formType) {
        this(elementType, null, formType);
    }

    public AbstractElementCollection(Class<ET> elementType, Instantiator<ET> i, Class<?> formType) {
        this.elementType = elementType;
        this.instantiator = i;
        this.editorType = formType;
        this.statusLayout = new FVerticalLayout().withSpacing(false);
    }


    public boolean isAllowNewItems() {
        return allowNewItems;
    }

    public boolean isAllowRemovingItems() {
        return allowRemovingItems;
    }

    public boolean isAllowEditItems() {
        return allowEditItems;
    }

    public boolean isShowStatusLabel() {
        return showStatusLabel;
    }

    public AbstractElementCollection<ET, CT> setAllowEditItems(boolean allowEditItems) {
        this.allowEditItems = allowEditItems;
        return this;
    }

    public AbstractElementCollection<ET, CT> setAllowRemovingItems(boolean allowRemovingItems) {
        this.allowRemovingItems = allowRemovingItems;
        return this;
    }

    public AbstractElementCollection<ET, CT> withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    public AbstractElementCollection<ET, CT> setShowStatusLabel(boolean visible) {
        this.showStatusLabel = visible;
        return this;
    }

    @SuppressWarnings("unchecked")
    private CT getAndEnsureValue() {
        CT value = getValue();
        if (value == null) {
            Class<CT> fieldType = getContainerType();
            if (fieldType.isInterface()) {
                if (fieldType.isAssignableFrom(List.class)) {
                    value = (CT) new ArrayList<ET>();
                } else { // Set
                    value = (CT) new HashSet<ET>();
                }
            } else {
                try {
                    value = fieldType.newInstance();
                } catch (IllegalAccessException | InstantiationException ex) {
                    throw new RuntimeException("Could not instantiate the used colleciton type", ex);
                }
            }
            setValue(value);
        }
        return value;
    }

    /**
     * @param allowNewItems true if a new element row should be automatically 
     * added
     * @return the configured field instance
     */
    public AbstractElementCollection<ET, CT> setAllowNewElements(boolean allowNewItems) {
        this.allowNewItems = allowNewItems;
        return this;
    }

    public interface Instantiator<ET> extends Serializable {
        ET create();
    }

    public interface EditorInstantiator<T, ET> extends Serializable {
        T create(ET entity);
    }

    public Class<ET> getElementType() {
        return elementType;
    }

    protected ET createInstance() {
        if (instantiator != null) {
            return instantiator.create();
        } else {
            try {
                return elementType.newInstance();
            } catch (IllegalAccessException | InstantiationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    protected Object createEditorInstance(ET pojo) {
        if (newEditorInstantiator != null) {
            return newEditorInstantiator.create(pojo);
        } else {
            if (oldEditorInstantiator != null) {
                return oldEditorInstantiator.create();
            } else {
                try {
                    return editorType.newInstance();
                } catch (IllegalAccessException | InstantiationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public EditorInstantiator<?, ET> getNewEditorInstantiator() {
        return newEditorInstantiator;
    }

    public void setNewEditorInstantiator(EditorInstantiator<?, ET> editorInstantiator) {
        this.newEditorInstantiator = editorInstantiator;
    }

    public Instantiator<?> getEditorInstantiator() {
        return oldEditorInstantiator;
    }

    public void setEditorInstantiator(Instantiator<?> editorInstantiator) {
        this.oldEditorInstantiator = editorInstantiator;
    }

    private class ElementEditor implements Serializable {
        private static final long serialVersionUID = 5132645136059482705L;
        Binder<ET>                beanBinder;
        Object                    editor;

        private ElementEditor(Binder<ET> editor, Object o) {
            this.beanBinder = editor;
            this.editor = o;
        }
    }

    private final Map<ET, ElementEditor> pojoToEditor = new IdentityHashMap<>();

    protected final Binder<ET> getBinderFor(ET pojo) {
        ElementEditor es = pojoToEditor.get(pojo);
        if (es == null) {
            es = createBoundEditor(pojo);
        }
        return es.beanBinder;
    }

    @SuppressWarnings("unchecked")
    private ElementEditor createBoundEditor(ET pojo) {
        ElementEditor es;
        Object o = createEditorInstance(pojo);
        BeanValidationBinder<ET> beanBinder = new BeanValidationBinder<ET>((Class<ET>) pojo.getClass());

        if (isShowStatusLabel()) {
            BinderValidationStatusHandler<ET> defaultHandler = beanBinder.getValidationStatusHandler();
            FLabel formStatusLabel = new FLabel().withContentMode(ContentMode.HTML).withStyleName(ValoTheme.LABEL_FAILURE);
            statusLayout.addComponent(formStatusLabel);
            formStatusLabel.setVisible(false);

            beanBinder.setValidationStatusHandler(status -> {
                List<BindingValidationStatus<?>> fieldErrors = status.getFieldValidationErrors();
                List<ValidationResult> beanErrors = status.getBeanValidationErrors();

                // collect all bean level error messages into a single string,
                // separating each message with a <br> tag
                String errorMessage = fieldErrors.stream()
                                                 .map(BindingValidationStatus::getMessage)
                                                 .map(Optional::get)
                                                 .collect(Collectors.joining("<br>"));

                String errorMessage2 = beanErrors.stream().map(ValidationResult::getErrorMessage).collect(Collectors.joining("<br>"));

                List<String> errorList = Arrays.asList(errorMessage, errorMessage2);
                String errors = errorList.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("<br>"));

                // finally, display all bean level validation errors in a single label
                formStatusLabel.setValue(errors);
                formStatusLabel.setVisible(!errors.isEmpty());

                // Let the default handler show messages for each field
                defaultHandler.statusChange(status);
            });
        }

        beanBinder.bindInstanceFields(o);
        beanBinder.setBean(pojo);
        beanBinder.addStatusChangeListener(scl);

        es = new ElementEditor(beanBinder, o);
        pojoToEditor.put(pojo, es);

        return es;
    }

    protected final Component getComponentFor(ET pojo, String property) {
        ElementEditor editorsstuff = pojoToEditor.get(pojo);
        if (editorsstuff == null) {
            editorsstuff = createBoundEditor(pojo);
        }
        Component c = null;
        Optional<Binder.Binding<ET, ?>> binding = editorsstuff.beanBinder.getBinding(property);
        if (binding.isPresent()) {
            c = (Component) binding.get().getField();
        } else {
            try {
                // property that is not a property editor field but custom UI "column"
                java.lang.reflect.Field f = editorType.getDeclaredField(property);
                f.setAccessible(true);
                c = (Component) f.get(editorsstuff.editor);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(AbstractElementCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (c == null) {
                c = new Label("");
            }
        }

        return c;
    }

    public void addElement(ET instance) {
        getAndEnsureValue().add(instance);
        addInternalElement(instance);
        if (getBinderFor(instance).isValid() && isValidBean(instance)) {
            fireEvent(new ValueChangeEvent<CT>(this, null, true));
        }
        
        fireEvent(new ElementAddedEvent<>(this, instance));
    }

    public void removeElement(ET elemnentToBeRemoved) {
        getAndEnsureValue().remove(elemnentToBeRemoved);
        removeInternalElement(elemnentToBeRemoved);
        fireEvent(new ValueChangeEvent<CT>(this, null, true));
        fireEvent(new ElementRemovedEvent<>(this, elemnentToBeRemoved));
    }

    public AbstractElementCollection<ET, CT> setVisibleProperties(List<String> properties) {
        visibleProperties = properties;
        return this;
    }

    public List<String> getVisibleProperties() {
        if (visibleProperties == null) {

            visibleProperties = new ArrayList<>();

            for (java.lang.reflect.Field f : editorType.getDeclaredFields()) {
                // field order can be counted since jdk6 
                if (HasValue.class.isAssignableFrom(f.getType())) {
                    visibleProperties.add(f.getName());
                }
            }

        }
        return visibleProperties;
    }

    public AbstractElementCollection<ET, CT> setVisibleProperties(List<String> properties, List<String> propertyHeaders) {
        visibleProperties = properties;
        Iterator<String> it = propertyHeaders.iterator();
        for (String prop : visibleProperties) {
            setPropertyHeader(prop, it.next());
        }
        return this;
    }

    private final Map<String, String> propertyToHeader = new HashMap<>();

    public AbstractElementCollection<ET, CT> setPropertyHeader(String propertyName, String propertyHeader) {
        propertyToHeader.put(propertyName, propertyHeader);
        return this;
    }

    protected String getPropertyHeader(String propertyName) {
        String header = propertyToHeader.get(propertyName);
        if (header == null) {
            header = SharedUtil.propertyIdToHumanFriendly(propertyName);
        }
        return header;
    }

    @Override
    protected Component initContent() {
        return getLayout();
    }

    @Override
    protected void doSetValue(CT newValue) {
        clear();
        value.addAll(newValue);
        if (value != null) {
            for (ET v : newValue) {
                addInternalElement(v);
            }
        }
        onElementAdded();
    }

    @Override
    public void clear() {
        value.clear();
    }

    abstract protected void addInternalElement(ET v);

    abstract protected void setPersisted(ET v, boolean persisted);

    abstract protected void removeInternalElement(ET v);

    abstract protected Layout getLayout();

    abstract protected void onElementAdded();

    abstract protected Class<CT> getContainerType();

    @Override
    public CT getValue() {
        return value;
    }

    public boolean isValueValid() {
        boolean isValid = true;
        if (value != null && !value.isEmpty()) {
            for (ET element : value) {
                Binder<ET> binder = getBinderFor(element);
                isValid &= binder.isValid() & isValidBean(element);
            }
        }
        return isValid;
    }

    public ValidationResult isValid() {
        if (isValueValid()) {
            return ValidationResult.ok();
        } else {
            return ValidationResult.error("Veuillez compléter tous les champs");
        }
    }

    public boolean isValidBean(ET bean) {
        Set<ConstraintViolation<ET>> violations = getJavaxBeanValidator().validate(bean);
        return violations.isEmpty();
    }

    public ValidationResult validateBean(ET bean) {
        ValidationResult result = ValidationResult.ok();
        Set<ConstraintViolation<ET>> violations = getJavaxBeanValidator().validate(bean);
        if (!violations.isEmpty()) {
            result = ValidationResult.error("L'entrée contient des erreurs");
        }
        return result;
    }

    /**
     * Returns the underlying JSR-303 bean validator factory used. A factory is
     * created using {@link Validation} if necessary.
     *
     * @return {@link ValidatorFactory} to use
     */
    protected static ValidatorFactory getJavaxBeanValidatorFactory() {
        if (factory == null) {
            factory = Validation.buildDefaultValidatorFactory();
        }

        return factory;
    }

    /**
     * Returns a shared Validator instance to use. An instance is created using
     * the validator factory if necessary and thereafter reused by the
     * {@link BeanValidator} instance.
     *
     * @return the JSR-303 {@link javax.validation.Validator} to use
     */
    protected Validator getJavaxBeanValidator() {
        if (javaxBeanValidator == null) {
            javaxBeanValidator = getJavaxBeanValidatorFactory().getValidator();
        }

        return javaxBeanValidator;
    }

}
