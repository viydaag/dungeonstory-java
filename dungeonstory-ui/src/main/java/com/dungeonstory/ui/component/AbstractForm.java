package com.dungeonstory.ui.component;

import java.io.Serializable;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.shared.Registration;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public abstract class AbstractForm<T> extends CustomComponent {

    private static final long serialVersionUID = 7514468217092960159L;

    public interface SavedHandler<T> extends Serializable {

        void onSave(T entity);
    }

    public interface ResetHandler<T> extends Serializable {

        void onReset(T entity);
    }

    public interface DeleteHandler<T> extends Serializable {

        void onDelete(T entity);
    }

    public interface CancelHandler<T> extends Serializable {

        void onCancel(T entity);
    }

    private boolean   settingBean;
    private boolean   isDoingOperation      = false;
    private boolean   hasChanges            = false;
    private boolean   bindWhenSettingEntity = false;
    private boolean   binding               = true;
    private T         entity;
    private Window    popup;
    private Binder<T> binder;

    private SavedHandler<T>  savedHandler;
    private ResetHandler<T>  resetHandler;
    private DeleteHandler<T> deleteHandler;
    private CancelHandler<T> cancelHandler;

    private String modalWindowTitle = "Edit entry";
    private String saveCaption      = "Save";
    private String deleteCaption    = "Delete";
    private String cancelCaption    = "Cancel";
    private String resetCaption     = "Reset";

    private Button saveButton;
    private Button resetButton;
    private Button deleteButton;
    private Button cancelButton;

    private Registration saveRegistration;
    private Registration resetRegistration;
    private Registration deleteRegistration;
    private Registration cancelRegistration;

    public AbstractForm(Class<T> entityType) {
        addAttachListener(new AttachListener() {

            private static final long serialVersionUID = 3193438171004932112L;

            @Override
            public void attach(AttachEvent event) {
                lazyInit();
            }
        });
        createBinder(entityType);
    }

    private void createBinder(Class<T> entityType) {
        binder = new BeanValidationBinder<>(entityType);
        binder.addValueChangeListener(e -> {
            // binder.hasChanges is not really useful so track it manually
            if (!settingBean) {
                hasChanges = true;
            }
        });
        binder.addStatusChangeListener(e -> {
            // TODO optimize this
            // TODO see if explicitly calling writeBean would write also invalid
            // values -> would make functionality more logical and easier for 
            // users to do validation and error reporting

            // Eh, value change listener is called after status change listener, so
            // ensure flag is on...
            if (!settingBean) {
                hasChanges = true;
            }
            adjustResetButtonState();
            adjustSaveButtonState();
        });
    }

    /**
     * Do some treatment before binding the entity to the form.
     * Override this method to make it effective.
     * @param entity
     */
    public void beforeSetEntity(T entity) {

    }

    /**
     * Do some treatment after binding the entity to the form.
     * Override this method to make it effective.
     */
    public void afterSetEntity() {

    }

    /**
     * Sets the object to be edited by this form. This method binds all fields
     * from this form to given objects.
     * <p>
     * If your form needs to manually configure something based on the state of
     * the edited object, you can override this method to do that either before
     * the object is bound to fields or to do something after the bean binding.
     *
     * @param entity the object to be edited by this form
     */
    public void setEntity(T entity) {
        beforeSetEntity(entity);
        this.entity = entity;
        this.settingBean = true;
        lazyInit();
        if (entity != null) {
            binder.setBean(entity);
            hasChanges = false;
            setVisible(true);
        } else {
            binder.setBean(null);
            hasChanges = false;
            setVisible(false);
        }
        settingBean = false;
        afterSetEntity();
    }

    /**
     * @return true if bean has been changed since last setEntity call.
     */
    public boolean hasChanges() {
        return hasChanges;
    }

    protected boolean isBound() {
        return binder != null && binder.getBean() != null;
    }

    /**
     * @return the currently edited entity or null if the form is currently
     * unbound
     */
    public T getEntity() {
        return entity;
    }

    /**
     * @return A default toolbar containing save/cancel/delete buttons
     */
    public HorizontalLayout getToolbar() {
        return new HorizontalLayout(getSaveButton(), getResetButton(), getCancelButton(), getDeleteButton());
    }

    public Binder<T> getBinder() {
        return binder;
    }

    public void setBinder(Binder<T> binder) {
        this.binder = binder;
    }

    @SuppressWarnings("unchecked")
    protected void lazyInit() {
        if (getCompositionRoot() == null) {
            setCompositionRoot(createContent());
            bind();
        } else if (bindWhenSettingEntity && !isDoingOperation && entity != null) {
            createBinder((Class<T>) entity.getClass());
            bind();
        }
    }

    /**
     * By default just does simple name based binding. Override this method to
     * customize the binding.
     */
    protected void bind() {
        if (binding) {
            binder.bindInstanceFields(this);
        }
    }

    /**
     * This method should return the actual content of the form, including
     * possible toolbar.
     *
     * Use setEntity(T entity) to fill in the data. Am example implementation
     * could look like this:
     * <pre><code>
     * public class PersonForm extends AbstractForm&lt;Person&gt; {
     *
     *     private TextField firstName = new TextField(&quot;First Name&quot;);
     *     private TextField lastName = new TextField(&quot;Last Name&quot;);
     *
     *     {@literal @}Override
     *     protected Component createContent() {
     *         return new VerticalLayout(
     *                 new FormLayout(
     *                         firstName,
     *                         lastName
     *                 ),
     *                 getToolbar()
     *         );
     *     }
     * }
     * </code></pre>
     *
     * @return the content of the form
     *
     */
    protected abstract Component createContent();

    public void adjustButtons() {
        adjustSaveButtonState();
        adjustResetButtonState();
    }

    public void setBindWhenSettingEntity(boolean enabled) {
        this.bindWhenSettingEntity = enabled;
    }

    public void setBinding(boolean enabled) {
        this.binding = enabled;
    }

    /************* Save button ****************/

    public String getSaveCaption() {
        return saveCaption;
    }

    public void setSaveCaption(String saveCaption) {
        this.saveCaption = saveCaption;
        getSaveButton().setCaption(getSaveCaption());
    }

    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = binder.isValid();
            getSaveButton().setEnabled(hasChanges() && valid);
        }
    }

    public Button getSaveButton() {
        if (saveButton == null) {
            setSaveButton(createSaveButton());
        }
        return saveButton;
    }

    public void setSaveButton(Button button) {
        removeSaveClickListener();
        this.saveButton = button;
        saveRegistration = this.saveButton.addClickListener(event -> save(event));
    }

    protected void save(ClickEvent e) {
        isDoingOperation = true;
        savedHandler.onSave(getEntity());
        hasChanges = false;
        adjustSaveButtonState();
        adjustResetButtonState();
        isDoingOperation = false;
    }

    protected Button createSaveButton() {
        return new PrimaryButton(getSaveCaption()).withVisible(false);
    }

    public SavedHandler<T> getSavedHandler() {
        return savedHandler;
    }

    public void setSavedHandler(SavedHandler<T> savedHandler) {
        this.savedHandler = savedHandler;
        getSaveButton().setVisible(this.savedHandler != null);
    }

    public void removeSaveClickListener() {
        if (saveRegistration != null) {
            saveRegistration.remove();
        }
    }

    /************* Reset button ****************/

    public String getResetCaption() {
        return resetCaption;
    }

    public void setResetCaption(String resetCaption) {
        this.resetCaption = resetCaption;
        getResetButton().setCaption(getResetCaption());
    }

    protected Button createResetButton() {
        Button btn = new Button(getResetCaption());
        btn.setVisible(false);
        return btn;
    }

    public Button getResetButton() {
        if (resetButton == null) {
            setResetButton(createResetButton());
        }
        return resetButton;
    }

    public void setResetButton(Button resetButton) {
        this.resetButton = resetButton;
        resetRegistration = this.resetButton.addClickListener(event -> reset(event));
    }

    protected void reset(Button.ClickEvent e) {
        isDoingOperation = true;
        resetHandler.onReset(getEntity());
        hasChanges = false;
        adjustSaveButtonState();
        adjustResetButtonState();
        isDoingOperation = false;
    }

    public ResetHandler<T> getResetHandler() {
        return resetHandler;
    }

    public void setResetHandler(ResetHandler<T> resetHandler) {
        this.resetHandler = resetHandler;
        getResetButton().setVisible(this.resetHandler != null);
    }

    protected void adjustResetButtonState() {
        if (popup != null && popup.getParent() != null) {
            // Assume reset button in a form opened to a popup also closes
            // it, allows closing via reset button by default
            getResetButton().setEnabled(true);
            return;
        }
        if (isBound()) {
            boolean modified = hasChanges();
            getResetButton().setEnabled(modified || popup != null);
        }
    }

    public void removeResetClickListener() {
        if (resetRegistration != null) {
            resetRegistration.remove();
        }
    }

    /************* Delete button ****************/

    public String getDeleteCaption() {
        return deleteCaption;
    }

    public void setDeleteCaption(String deleteCaption) {
        this.deleteCaption = deleteCaption;
        getDeleteButton().setCaption(getDeleteCaption());
    }

    protected Button createDeleteButton() {
        DeleteButton btn = new DeleteButton(getDeleteCaption());
        btn.setVisible(false);
        return btn;
    }

    public void setDeleteButton(final Button deleteButton) {
        this.deleteButton = deleteButton;
        deleteRegistration = this.deleteButton.addClickListener(event -> delete(event));
    }

    protected void delete(Button.ClickEvent e) {
        isDoingOperation = true;
        deleteHandler.onDelete(getEntity());
        hasChanges = false;
        isDoingOperation = false;
    }

    public Button getDeleteButton() {
        if (deleteButton == null) {
            setDeleteButton(createDeleteButton());
        }
        return deleteButton;
    }

    public DeleteHandler<T> getDeleteHandler() {
        return deleteHandler;
    }

    public void setDeleteHandler(DeleteHandler<T> deleteHandler) {
        this.deleteHandler = deleteHandler;
        getDeleteButton().setVisible(this.deleteHandler != null);
    }

    public void removeDeleteClickListener() {
        if (deleteRegistration != null) {
            deleteRegistration.remove();
        }
    }

    /************* Cancel button ****************/

    public String getCancelCaption() {
        return cancelCaption;
    }

    public void setCancelCaption(String cancelCaption) {
        this.cancelCaption = cancelCaption;
        getCancelButton().setCaption(getCancelCaption());
    }

    protected Button createCancelButton() {
        Button btn = new Button(getCancelCaption());
        btn.setVisible(false);
        return btn;
    }

    public Button getCancelButton() {
        if (cancelButton == null) {
            setCancelButton(createCancelButton());
        }
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
        cancelRegistration = this.cancelButton.addClickListener(event -> cancel(event));
    }

    protected void cancel(ClickEvent event) {
        isDoingOperation = true;
        cancelHandler.onCancel(getEntity());
        isDoingOperation = false;
    }

    public CancelHandler<T> getCancelHandler() {
        return cancelHandler;
    }

    public void setCancelHandler(CancelHandler<T> cancelHandler) {
        this.cancelHandler = cancelHandler;
        getCancelButton().setVisible(this.cancelHandler != null);
    }

    public void removeCancelClickListener() {
        if (cancelRegistration != null) {
            cancelRegistration.remove();
        }
    }

    /**
     * Focuses the first field found from the form. It often improves UX to call
     * this method, or focus another field, when you assign a bean for editing.
     */
    public void focusFirst() {
        Component compositionRoot = getCompositionRoot();
        findFieldAndFocus(compositionRoot);
    }

    private boolean findFieldAndFocus(Component compositionRoot) {
        if (compositionRoot instanceof AbstractComponentContainer) {
            AbstractComponentContainer cc = (AbstractComponentContainer) compositionRoot;

            for (Component component : cc) {
                if (component instanceof AbstractTextField) {
                    AbstractTextField abstractTextField = (AbstractTextField) component;
                    abstractTextField.selectAll();
                    return true;
                }
                if (component instanceof AbstractField) {
                    AbstractField<?> abstractField = (AbstractField<?>) component;
                    abstractField.focus();
                    return true;
                }
                if (component instanceof AbstractComponentContainer) {
                    if (findFieldAndFocus(component)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /************* Modal window ****************/

    public String getModalWindowTitle() {
        return modalWindowTitle;
    }

    public void setModalWindowTitle(String modalWindowTitle) {
        this.modalWindowTitle = modalWindowTitle;
    }

    public Window openInModalPopup() {
        popup = new Window(getModalWindowTitle(), this);
        popup.setModal(true);
        UI.getCurrent().addWindow(popup);
        focusFirst();
        return popup;
    }

    /**
     *
     * @return the last Popup into which the Form was opened with
     * #openInModalPopup method or null if the form hasn't been use in window
     */
    public Window getPopup() {
        return popup;
    }

    public void closePopup() {
        if (getPopup() != null) {
            getPopup().close();
        }
    }

}
