package com.dungeonstory.view.adventure;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.authentication.CurrentUser;
import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.UserDataService;
import com.dungeonstory.backend.service.impl.AdventureService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockAdventureService;
import com.dungeonstory.backend.service.mock.MockUserService;
import com.dungeonstory.event.EventBus;
import com.dungeonstory.event.NavigationEvent;
import com.dungeonstory.event.ViewAddedEvent;
import com.dungeonstory.event.ViewAddedEvent.ViewDestination;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(displayName = "Aventures", uri = AdventureListView.URI)
public class AdventureListView extends AbstractCrudView<Adventure> {

    private static final long  serialVersionUID = -2282322292623232916L;

    public static final String URI              = "adventures";

    private UserDataService    userService;

    public AdventureListView() {
        super();

        if (Configuration.getInstance().isMock()) {
            userService = MockUserService.getInstance();
        } else {
            userService = UserService.getInstance();
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        getAddButton().setCaption("Nouvelle aventure");
        setFilterAllowed(false);

        if (CurrentUser.get().getAdventure() == null) {
            Column joinColumn = grid.getColumn("join");
            // TODO : replace with Component Renderer from Vaadin 8.1
            // TODO : button is visible only if adventure is OPENED
            joinColumn.setRenderer(new ButtonRenderer(e -> {
                ConfirmDialog.show(getUI(), "Joindre l'aventure", "Êtes-vous certain de joindre cette aventure", "Oui",
                        "Non", new Runnable() {
                            @Override
                            public void run() {
                                User user = CurrentUser.get();
                                Adventure adventure = (Adventure) e.getItemId();
                                user.setAdventure(adventure);
                                user = userService.update(user);
                                CurrentUser.set(user);
                                grid.removeColumn("join");
                                EventBus.post(new ViewAddedEvent(AdventureView.class, ViewDestination.MENUBAR,
                                        "Mon aventure", AdventureView.URI + "/" + adventure.getId()));
                            }
                        });
            }));
        } else {
            grid.removeColumn("join");
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
    public DataService<Adventure, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockAdventureService.getInstance();
        }
        return AdventureService.getInstance();
    }

    @Override
    public void entrySaved(Adventure entity) {
        if (entity.getCreator() == null) {
            entity.setCreator(CurrentUser.get());
        }
        super.entrySaved(entity);
    }

    @Override
    public void entrySelected() {
        User user = CurrentUser.get();
        Adventure adventure = grid.getSelectedRow();
        if (adventure != null) {
            if (adventure.getCreator().equals(user)) {
                super.entrySelected();
            } else {
                // go to messages
                if (grid.getSelectedRow() != null) {
                    EventBus.post(new NavigationEvent(AdventureView.URI + "/" + grid.getSelectedRow().getId()));
                    grid.deselectAll();
                }
            }
        } else {
            form.setEntity(null);
        }
    }
}
