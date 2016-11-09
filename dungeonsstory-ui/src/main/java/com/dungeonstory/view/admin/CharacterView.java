package com.dungeonstory.view.admin;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.CharacterGrid;

@ViewConfig(uri = "characters", displayName = "Personnages")
public class CharacterView extends AbstractCrudView<Character> {

    private static final long serialVersionUID = 2774259451633303742L;

    @Override
    public DSAbstractForm<Character> getForm() {
        return null;
    }

    @Override
    public BeanGrid<Character> getGrid() {
        return new CharacterGrid();
    }

    @Override
    public DataService<Character, Long> getDataService() {
        return CharacterService.getInstance();
    }

}
