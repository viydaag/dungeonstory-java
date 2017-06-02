package com.dungeonstory.ui.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.data.SpellEffect;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.SpellDataService;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.SpellForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.SpellGrid;

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
    public SpellDataService getDataService() {
        return Services.getSpellService();
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
