package com.dungeonstory.authentication;

import org.vaadin.viritin.fields.EmailField;

import com.dungeonstory.i18n.Messages;
import com.dungeonstory.i18n.Translatable;
import com.dungeonstory.util.DSTheme;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class NewUserForm extends FormLayout implements Translatable {

    private static final long serialVersionUID = -2396287642826582387L;

    private TextField     username = new TextField();
    private PasswordField password = new PasswordField();
    private TextField     name     = new TextField();
    private EmailField    email    = new EmailField();

    public NewUserForm() {
        super();

        addStyleName(DSTheme.LOGIN_FORM);
        setSizeUndefined();
        setMargin(false);
        setSpacing(true);

        addComponents(username, password, name, email);
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

}
