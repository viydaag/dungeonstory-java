package com.dungeonstory.view.user;

import java.util.Date;

import org.vaadin.viritin.fields.LabelField;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.i18n.Translatable;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class UserForm extends AbstractForm<User> implements Translatable {

    private static final long serialVersionUID = 1062880819880337025L;

    private LabelField<String>     username;
    private LabelField<String>     name;
    private LabelField<AccessRole> role;
    private LabelField<UserStatus> status;
    private LabelField<String> email;
    private LabelField<Date> created;
    
    public UserForm() {
        super();

    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        username = new LabelField<>();
        name = new LabelField<>();
        role = new LabelField<>(AccessRole.class);
        status = new LabelField<>(UserStatus.class);
        email = new LabelField<>();
        created = new LabelField<>(Date.class);

        layout.addComponents(username, name, email, role, status, created);

        return layout;
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        username.setCaption(messages.getMessage("userView.label.username"));
        name.setCaption(messages.getMessage("userView.label.name"));
        role.setCaption(messages.getMessage("userView.label.role"));
        status.setCaption(messages.getMessage("userView.label.status"));
        email.setCaption(messages.getMessage("userView.label.email"));
        created.setCaption(messages.getMessage("userView.label.created"));
    }
}
