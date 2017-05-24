package com.dungeonstory.ui.view.user;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.i18n.Translatable;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@ViewConfig(uri = UserView.USER_URI, displayName = "userView.caption")
public class UserView extends VerticalLayout implements View, Translatable {

    private static final long serialVersionUID = -3654948295501447567L;

    public final static String USER_URI = "user";

    private UserForm form;

    public UserView() {
        super();
        form = new UserForm();
        addComponent(form);
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

}
