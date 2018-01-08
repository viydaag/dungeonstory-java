package com.dungeonstory.ui.view.user;

import java.time.LocalDateTime;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.ui.captionGenerator.LocalDateTimeCaptionGenerator;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.field.LabelField;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.i18n.Translatable;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.fluent.ui.FTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class UserForm
        extends DSAbstractForm<User>
        implements Translatable {

    private static final long serialVersionUID = 1062880819880337025L;

    private LabelField<String>        username;
    private LabelField<String>        name;
    private LabelField<String>        email;
    private LabelField<LocalDateTime> created;
    private LabelField<LocalDateTime> updated;

    private TextField nameEdit;
    private TextField emailEdit;

    private HorizontalLayout toolbar;

    public UserForm() {
        super(User.class);
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        username = new LabelField<>();
        name = new LabelField<>();
        email = new LabelField<>();
        created = new LabelField<>();
        updated = new LabelField<>();

        nameEdit = new FTextField().withVisible(false);
        emailEdit = new FTextField().withVisible(false);

        created.setCaptionGenerator(new LocalDateTimeCaptionGenerator());
        updated.setCaptionGenerator(new LocalDateTimeCaptionGenerator());

        toolbar = getToolbar();
        toolbar.setVisible(false);
        layout.addComponents(username, name, nameEdit, email, emailEdit, created, updated, toolbar);

        getDeleteButton().setVisible(false);

        return layout;
    }

    @Override
    protected void bind() {
        // TODO Auto-generated method stub
        super.bind();

        getBinder().forField(nameEdit).withValidator(new BeanValidator(User.class, "name")).bind(User::getName, User::setName);
        getBinder().forField(emailEdit).withValidator(new BeanValidator(User.class, "email")).bind(User::getEmail,
                User::setEmail);
    }

    public void showEditableFields(boolean editable) {
        nameEdit.setVisible(editable);
        emailEdit.setVisible(editable);
        toolbar.setVisible(editable);
        name.setVisible(!editable);
        email.setVisible(!editable);
    }

    @Override
    public void updateMessageStrings() {
        Messages messages = Messages.getInstance();
        username.setCaption(messages.getMessage("userView.label.username"));
        name.setCaption(messages.getMessage("userView.label.name"));
        email.setCaption(messages.getMessage("userView.label.email"));
        created.setCaption(messages.getMessage("userView.label.created"));
        updated.setCaption(messages.getMessage("userView.label.updated"));

        nameEdit.setCaption(messages.getMessage("userView.label.name"));
        emailEdit.setCaption(messages.getMessage("userView.label.email"));
    }
}
