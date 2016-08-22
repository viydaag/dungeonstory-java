package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.form.SpellForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.SpellGrid;
import com.dungeonstory.view.component.BeanGrid;

@ViewConfig(uri = "spells", displayName = "Sorts")
public class SpellView extends AbstractCrudView<Spell> {

    private static final long serialVersionUID = -4720530682027416628L;

    @Override
    public DSAbstractForm<Spell> getForm() {
        return new SpellForm();
    }

    @Override
    public BeanGrid<Spell> getGrid() {
        return new SpellGrid();
    }

    @Override
    public DataService<Spell, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockSpellService.getInstance();
        }
        return SpellService.getInstance();
    }

}
