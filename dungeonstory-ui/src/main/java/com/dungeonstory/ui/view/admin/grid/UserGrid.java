package com.dungeonstory.ui.view.admin.grid;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class UserGrid extends DSGrid<User> {

    private static final long serialVersionUID = -6906747725928178053L;

    public UserGrid() {
        super(User.class);

        removeAllColumns();

        Binder<User> binder = new BeanValidationBinder<>(User.class);

        getEditor().setEnabled(true);
        getEditor().setBinder(binder);
        getEditor().setCancelCaption(Messages.getInstance().getMessage("button.cancel"));
        getEditor().setSaveCaption(Messages.getInstance().getMessage("button.save"));

        EnumComboBox<AccessRole> roleEditor = new EnumComboBox<>(AccessRole.class);
        binder.forField(roleEditor).bind(User::getRole, User::setRole);

        addColumn(User::getUsername).setCaption("Utilisateur").setId("username");
        addColumn(User::getRole).setCaption("Role").setId("role").setEditorComponent(roleEditor, User::setRole);
        addColumn(User::getName).setCaption("Nom").setId("name");
        addColumn("email").setCaption("Courriel").setEditorComponent(new TextField());

        setRowHeight(40);
    }
}
