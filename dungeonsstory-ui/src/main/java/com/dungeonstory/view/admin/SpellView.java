package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.SpellEffect;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.SpellForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.grid.DSGrid;
import com.dungeonstory.view.grid.SpellGrid;

@ViewConfig(uri = "spells", displayName = "Sorts")
public class SpellView extends AbstractCrudView<Spell> {

    private static final long serialVersionUID = -4720530682027416628L;

    @Override
    public DSAbstractForm<Spell> getForm() {
        return new SpellForm();
    }

    @Override
    public DSGrid<Spell> getGrid() {
        return new SpellGrid();
    }

    @Override
    public DataService<Spell, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockSpellService.getInstance();
        }
        return SpellService.getInstance();
    }
    
    @Override
    public void entrySaved(Spell entity) {

        //set class for each nested objects
        List<SpellEffect> effects = new ArrayList<SpellEffect>(entity.getEffects());
        effects.stream().forEach(effect -> effect.setSpell(entity));
        entity.setEffects(effects);
        
        //nested entities are automatically removed with the annotation @PrivateOwned
        
        //save to database with nested objects
        super.entrySaved(entity);
    }

}
