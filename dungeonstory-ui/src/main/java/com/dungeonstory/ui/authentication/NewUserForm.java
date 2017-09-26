package com.dungeonstory.ui.authentication;

import org.vaadin.viritin.fields.EmailField;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.i18n.Translatable;
import com.dungeonstory.ui.util.DSTheme;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.fluent.ui.FPasswordField;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class NewUserForm extends FormLayout implements Translatable {

    private static final long serialVersionUID = -2396287642826582387L;

    private TextField     username = new TextField();
    private PasswordField password = new PasswordField();
    private TextField     name     = new TextField();
    private EmailField    email    = new EmailField();

    private BeanValidationBinder<User> binder = null;

    public NewUserForm() {
        super();

        addStyleName(DSTheme.LOGIN_FORM);
        setSizeUndefined();
        setMargin(false);
        setSpacing(true);
        setId("newUserForm");

        username = new FTextField().withIcon(VaadinIcons.USER).withId("newUsername");
        password = new FPasswordField().withIcon(VaadinIcons.PASSWORD).withId("newPassword");
        name = new FTextField().withIcon(VaadinIcons.USER_CARD).withId("newName");
        email = new EmailField().withIcon(VaadinIcons.ENVELOPE_O).withId("newEmail");

        addComponents(username, password, name, email);

        binder = new BeanValidationBinder<>(User.class);
        binder.forMemberField(username).asRequired("Nom d'utilisateur requis");
        binder.forMemberField(password).asRequired("Mot de passe requis");
        binder.forMemberField(name).asRequired("Nom requis");
        binder.bindInstanceFields(this);
    }

    public void clear() {
        username.clear();
        password.clear();
        name.clear();
        email.clear();
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        username.setCaption(messages.getMessage("newUserForm.textfield.user"));
        password.setCaption(messages.getMessage("newUserForm.textfield.password"));
        name.setCaption(messages.getMessage("newUserForm.textfield.name"));
        email.setCaption(messages.getMessage("newUserForm.textfield.email"));
    }

    public BeanValidationBinder<User> getBinder() {
        return binder;
    }

}
