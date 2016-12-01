package com.dungeonstory.view.user;

import java.text.DateFormat;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.util.ViewConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;

@ViewConfig(uri = UserView.USER_URI, displayName = "Utilisateur")
public class UserView extends FormLayout implements View {

    private static final long serialVersionUID = -3654948295501447567L;

    public final static String USER_URI = "user";

    public UserView() {
        super();
    }

    /* (non-Javadoc)
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(ViewChangeEvent event) {
        User user = CurrentUser.get();
        MLabel username = new MLabel("Nom d'utilisateur :", user.getUsername());
        MLabel name = new MLabel("Nom :", user.getName());
        MLabel email = new MLabel("Courriel :", user.getEmail());
        MLabel role = new MLabel("Role :", user.getRole().getName());
        MLabel status = new MLabel("Statut :", user.getStatus().toString());
        MLabel created = new MLabel("Date de création :", DateFormat.getInstance().format(user.getCreated()));
        addComponents(username, name, email, role, status, created);
    }

}
