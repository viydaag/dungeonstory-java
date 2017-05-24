package com.dungeonstory.view.adventure;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.AdventureDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.UserDataService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.event.ViewAddedEvent;
import com.dungeonstory.event.ViewAddedEvent.ViewDestination;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(displayName = "Aventures", uri = AdventureListView.URI)
public class AdventureListView extends AbstractCrudView<Adventure> {

    private static final long serialVersionUID = -2282322292623232916L;

    public static final String URI = "adventures";

    private UserDataService userService = Services.getUserService();

    public AdventureListView() {
        super();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        getAddButton().setCaption("Nouvelle aventure");
        setFilterAllowed(false);

        if (CurrentUser.get().getAdventure() == null) {

            // TODO : replace with Component Renderer from Vaadin 8.1
            // TODO : button is visible only if adventure is OPENED
            grid.addColumn(adventure -> "Joindre", new ButtonRenderer<Adventure>(clickEvent -> {
                ConfirmDialog.show(getUI(), "Joindre l'aventure", "Êtes-vous certain de joindre cette aventure", "Oui", "Non", new Runnable() {
                    @Override
                    public void run() {
                        User user = CurrentUser.get();
                        Adventure adventure = clickEvent.getItem();
                        user.setAdventure(adventure);
                        user = userService.update(user);
                        CurrentUser.set(user);
                        grid.removeColumn("join");
                        EventBus.post(new ViewAddedEvent(AdventureView.class, ViewDestination.MENUBAR, "Mon aventure",
                                AdventureView.URI + "/" + adventure.getId()));
                    }
                });
            })).setId("join");

        }

        super.enter(event);
    }

    @Override
    public DSAbstractForm<Adventure> getForm() {
        return new AdventureForm();
    }

    @Override
    public DSGrid<Adventure> getGrid() {
        return new AdventureGrid();
    }

    @Override
    public AdventureDataService getDataService() {
        return Services.getAdventureService();
    }

    @Override
    public void entrySaved(Adventure entity) {
        if (entity.getCreator() == null) {
            entity.setCreator(CurrentUser.get());
        }
        super.entrySaved(entity);
    }

    @Override
    public void entrySelected(Adventure entity) {
        User user = CurrentUser.get();
        if (entity != null) {
            if (entity.getCreator().equals(user)) {
                super.entrySelected(entity);
            } else {
                // go to messages
                EventBus.post(new NavigationEvent(AdventureView.URI + "/" + entity.getId()));
                grid.deselectAll();
            }
        }
        form.setEntity(entity);
    }
}
