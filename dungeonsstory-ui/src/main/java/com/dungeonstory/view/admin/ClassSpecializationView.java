package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelSpell;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpecializationSpellSlots;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ClassSpecializationService;
import com.dungeonstory.form.ClassSpecializationForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.ClassSpecializationGrid;

@ViewConfig(uri = "classSpecs", displayName = "Spécialisations de classe")
public class ClassSpecializationView extends AbstractCrudView<ClassSpecialization> {

    private static final long serialVersionUID = 8343156191914542929L;

    public ClassSpecializationView() {
        super();
    }

    @Override
    public DSAbstractForm<ClassSpecialization> getForm() {
        return new ClassSpecializationForm();
    }

    @Override
    public BeanGrid<ClassSpecialization> getGrid() {
        return new ClassSpecializationGrid();
    }

    @Override
    public DataService<ClassSpecialization, Long> getDataService() {
        return ClassSpecializationService.getInstance();
    }

    @Override
    public void entrySaved(ClassSpecialization entity) {

        //set class for each nested objects
        List<ClassSpecLevelFeature> feats = new ArrayList<ClassSpecLevelFeature>(entity.getClassSpecFeatures());
        feats.stream().forEach(feat -> feat.setClassSpec(entity));
        entity.setClassSpecFeatures(feats);

        List<ClassSpecializationSpellSlots> levelSpells = new ArrayList<ClassSpecializationSpellSlots>(entity.getSpellSlots());
        levelSpells.stream().forEach(levelSpell -> levelSpell.setClassSpec(entity));
        entity.setSpellSlots(levelSpells);
        
        List<ClassSpecLevelSpell> spells = new ArrayList<ClassSpecLevelSpell>(entity.getClassSpecSpells());
        spells.stream().forEach(spell -> spell.setClassSpec(entity));
        entity.setClassSpecSpells(spells);

        //nested entities are automatically removed with the annotation @PrivateOwned

        //save to database with nested objects
        super.entrySaved(entity);
    }

}
