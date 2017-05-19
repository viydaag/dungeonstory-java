package com.dungeonstory.view.admin;

import org.apache.commons.codec.digest.DigestUtils;
import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockUserService;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.grid.UserGrid;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(uri = "users", displayName = "Utilisateurs")
public class UserListView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1085138977601539109L;

    private Label    titre;
    private UserGrid grid;

    private DataService<User, Long> service;

    public UserListView() {
        grid = new UserGrid();
        titre = new Label("Utilisateurs");
        if (Configuration.getInstance().isMock()) {
            service = MockUserService.getInstance();
        } else {
            service = UserService.getInstance();
        }

        grid.getEditor().addSaveListener(event -> service.saveOrUpdate(event.getBean()));

        grid.addColumn(user -> "Réinitialiser Mot de passe", new ButtonRenderer<User>(clickEvent -> {
            ConfirmDialog.show(getUI(), "Réinitiliser mot de passe", "Êtes-vous certain?", "OK", "Annuler", new Runnable() {
                @Override
                public void run() {
                    User user = clickEvent.getItem();
                    user.setPassword(DigestUtils.md5Hex(user.getUsername()));
                    service.update(user);
                }
            });
        }));

        addComponents(titre, grid);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        grid.setItems(service.findAll());
    }

}
