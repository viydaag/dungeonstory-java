package com.dungeonstory.ui.view.user;


import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.i18n.Translatable;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = UserView.USER_URI, displayName = "userView.caption")
public class UserView extends VerticalLayout implements View, Translatable {

    private static final long serialVersionUID = -3654948295501447567L;

    public final static String USER_URI = "user";

    private UserForm form;
    private Button   editButton;

    public UserView() {
        super();
        form = new UserForm();
        form.setSavedHandler(this::save);
        form.setCancelHandler(this::cancel);
        editButton = new Button("Modifier", e -> form.showEditableFields(true));
        addComponents(editButton, form);
    }

    /* (non-Javadoc)
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(ViewChangeEvent event) {
        User user = CurrentUser.get();
        form.setEntity(user);
    }

    @Override
    public void updateMessageStrings() {
        form.updateMessageStrings();
    }

    private void save(User user) {
        User updated = Services.getUserService().update(user);
        form.showEditableFields(false);
        form.setEntity(updated);
    }

    private void cancel(User user) {
        form.showEditableFields(false);
    }

}
