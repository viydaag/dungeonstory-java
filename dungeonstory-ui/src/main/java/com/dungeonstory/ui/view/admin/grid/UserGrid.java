package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.ui.component.EnumComboBox;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.TextField;

public class UserGrid extends DSGrid<User> {

    private static final long serialVersionUID = -6906747725928178053L;

    public UserGrid() {
        super();

        Binder<User> binder = getEditor().getBinder();

        getEditor().setEnabled(true);
        getEditor().setCancelCaption("Annuler");
        getEditor().setSaveCaption("Enregistrer");

        EnumComboBox<AccessRole> roleEditor = new EnumComboBox<>(AccessRole.class);
        binder.forField(roleEditor).bind(User::getRole, User::setRole);

        TextField emailEditor = new TextField();
        binder.forField(emailEditor).withValidator(new EmailValidator("Doit être un courriel valide")).bind(User::getEmail, User::setEmail);

        addColumn(User::getUsername).setCaption("Utilisateur").setId("username");
        addColumn(User::getRole).setCaption("Role").setId("role").setEditorComponent(roleEditor, User::setRole);
        addColumn(User::getName).setCaption("Nom").setId("name");
        addColumn(User::getEmail).setCaption("Courriel").setId("email").setEditorComponent(emailEditor, User::setEmail);
    }
}
