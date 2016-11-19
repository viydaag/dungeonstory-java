package com.dungeonstory.authentication;

import org.vaadin.viritin.fields.EmailField;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class NewUserForm extends FormLayout {

    private static final long serialVersionUID = -2396287642826582387L;

    private TextField         username         = new TextField("Nom d'utilisateur");
    private PasswordField     password         = new PasswordField("Mot de passe");
    private TextField         name             = new TextField("Nom");
    private EmailField        email            = new EmailField("Email");

    public NewUserForm() {
        super();

        addStyleName("login-form");
        setSizeUndefined();
        setMargin(false);
        setSpacing(true);
        
        username.setNullRepresentation("");
        password.setNullRepresentation("");
        name.setNullRepresentation("");
        email.setNullRepresentation("");
        
        setValidationVisible(false);

        addComponents(username, password, name, email);
    }
    
    public void clear() {
        username.clear();
        password.clear();
        name.clear();
        email.clear();
    }
    
    public void setValidationVisible(boolean visible) {
        username.setValidationVisible(visible);
        password.setValidationVisible(visible);
        name.setValidationVisible(visible);
        email.setValidationVisible(visible);
    }

}