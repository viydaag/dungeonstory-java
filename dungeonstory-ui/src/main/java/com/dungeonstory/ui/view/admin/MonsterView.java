package com.dungeonstory.ui.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.MonsterSense;
import com.dungeonstory.backend.data.MonsterSkill;
import com.dungeonstory.backend.service.MonsterDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.MonsterForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.MonsterGrid;

@ViewConfig(uri = "monsters", displayName = "Monstres")
public class MonsterView extends AbstractCrudView<Monster> {

    private static final long serialVersionUID = -4080616229674156134L;

    @Override
    public DSAbstractForm<Monster> getForm() {
        return new MonsterForm();
    }

    @Override
    public DSGrid<Monster> getGrid() {
        return new MonsterGrid();
    }

    @Override
    public MonsterDataService getDataService() {
        return Services.getMonsterService();
    }

    @Override
    public void entrySaved(Monster entity) {
        // set class for each nested objects
        List<MonsterSkill> skills = new ArrayList<>(entity.getSkills());
        skills.stream().forEach(skill -> skill.setMonster(entity));
        entity.setSkills(skills);
        
        List<MonsterSense> senses = new ArrayList<>(entity.getSenses());
        senses.stream().forEach(sens -> sens.setMonster(entity));
        entity.setSkills(skills);
        
        super.entrySaved(entity);
    }

}
