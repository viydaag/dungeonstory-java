package com.dungeonstory.ui.view.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.field.LabelField;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.i18n.Translatable;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class UserForm extends AbstractForm<User> implements Translatable {

    private static final long serialVersionUID = 1062880819880337025L;

    private LabelField<String>        username;
    private LabelField<String>        name;
    private LabelField<AccessRole>   role;
    private LabelField<UserStatus>    status;
    private LabelField<String>        email;
    private LabelField<LocalDateTime> created;

    public UserForm() {
        super(User.class);
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        username = new LabelField<>();
        name = new LabelField<>();
        role = new LabelField<>();
        status = new LabelField<>();
        email = new LabelField<>();
        created = new LabelField<>();

        layout.addComponents(username, name, email, role, status, created);

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        created.setCaptionGenerator(dt -> {
            return dt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT));
        });
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
