package com.dungeonstory.view.admin;

import org.vaadin.dialogs.ConfirmDialog;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.mock.MockCharacterService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.CharacterGrid;
import com.dungeonstory.view.grid.DSGrid;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ButtonRenderer;

@ViewConfig(uri = "characters", displayName = "Personnages")
public class CharacterListView extends AbstractCrudView<Character> {

    private static final long serialVersionUID = 2774259451633303742L;

    @Override
    public DSAbstractForm<Character> getForm() {
        return null;
    }

    @Override
    public DSGrid<Character> getGrid() {
        return new CharacterGrid();
    }

    @Override
    public DataService<Character, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockCharacterService.getInstance();
        }
        return CharacterService.getInstance();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);

        // Render a button that deletes the data row (item)
        Column deleteColumn = grid.getColumn("delete");
        deleteColumn.setRenderer(new ButtonRenderer(e -> {
            ConfirmDialog.show(getUI(), "Supprimer", "Êtes-vous certain?", "OK", "Annuler", new Runnable() {
                @Override
                public void run() {
                    Character character = (Character) e.getItemId();
                    deleteSelected(character);
                }
            });
        }));

    }

}
