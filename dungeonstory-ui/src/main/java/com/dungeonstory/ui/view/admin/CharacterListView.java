package com.dungeonstory.ui.view.admin;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.service.CharacterDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.event.CharacterUpdatedEvent;
import com.dungeonstory.ui.event.EventBus;
import com.dungeonstory.ui.event.ViewAddedEvent;
import com.dungeonstory.ui.event.ViewAddedEvent.ViewDestination;
import com.dungeonstory.ui.event.ViewRemovedEvent;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.CharacterEditForm;
import com.dungeonstory.ui.view.admin.grid.CharacterGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.character.CharacterView;
import com.dungeonstory.ui.view.character.NewCharacterView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(uri = "characters", displayName = "Personnages")
public class CharacterListView extends AbstractCrudView<Character> {

    private static final long serialVersionUID = 2774259451633303742L;

    @Override
    public DSAbstractForm<Character> getForm() {
        return new CharacterEditForm();
    }

    @Override
    public DSGrid<Character> getGrid() {
        return new CharacterGrid();
    }

    @Override
    public CharacterDataService getDataService() {
        return Services.getCharacterService();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);

        grid.addColumn(character -> "Supprimer", new ButtonRenderer<Character>(clickEvent -> {
            ConfirmDialog.show(getUI(), "Supprimer", "Êtes-vous certain?", "OK", "Annuler", new Runnable() {
                @Override
                public void run() {
                    Character character = clickEvent.getItem();
                    deleteSelected(character);
                    EventBus.post(new ViewAddedEvent(NewCharacterView.class, ViewDestination.NAVBAR));
                    EventBus.post(new ViewRemovedEvent(CharacterView.URI));
                }
            });
        }));

    }

    @Override
    public void entrySaved(Character entity) {
        super.entrySaved(entity);
        EventBus.post(new CharacterUpdatedEvent());
    }

}
