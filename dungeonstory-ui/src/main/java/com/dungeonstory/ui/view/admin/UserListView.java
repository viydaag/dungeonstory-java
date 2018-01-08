package com.dungeonstory.ui.view.admin;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.UserDataService;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.admin.grid.UserGrid;
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

    private UserDataService service = Services.getUserService();

    public UserListView() {
        grid = new UserGrid();
        titre = new Label("Utilisateurs");

        grid.getEditor().addSaveListener(event -> service.saveOrUpdate(event.getBean()));

        grid.addColumn(user -> "Réinitialiser Mot de passe", new ButtonRenderer<User>(clickEvent -> {
            ConfirmDialog.show(getUI(), "Réinitiliser mot de passe", "Êtes-vous certain?", "OK", "Annuler", new Runnable() {
                @Override
                public void run() {
                    User user = clickEvent.getItem();
                    user.setPassword(user.getUsername());
                    service.updatePassword(user);
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
