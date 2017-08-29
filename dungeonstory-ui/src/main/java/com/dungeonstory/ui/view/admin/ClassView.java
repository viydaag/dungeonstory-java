package com.dungeonstory.ui.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.ClassEquipment;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.ClassDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.ClassForm;
import com.dungeonstory.ui.view.admin.grid.ClassGrid;
import com.dungeonstory.ui.view.admin.grid.DSGrid;

@ViewConfig(uri = "classes", displayName = "Classes")
public class ClassView extends AbstractCrudView<DSClass> {

    private static final long serialVersionUID = 5117755861151432771L;

    public ClassView() {
        super();
    }

    @Override
    public DSAbstractForm<DSClass> getForm() {
        return new ClassForm();
    }

    @Override
    public DSGrid<DSClass> getGrid() {
        return new ClassGrid();
    }

    @Override
    public ClassDataService getDataService() {
        return Services.getClassService();
    }

    @Override
    public void entrySaved(DSClass entity) {

        //set class for each nested objects
        List<ClassLevelFeature> feats = new ArrayList<ClassLevelFeature>(entity.getClassFeatures());
        feats.stream().forEach(feat -> feat.setClasse(entity));
        entity.setClassFeatures(feats);

        List<ClassLevelBonus> levelBonuses = new ArrayList<ClassLevelBonus>(entity.getLevelBonuses());
        levelBonuses.stream().forEach(levelBonus -> levelBonus.setClasse(entity));
        entity.setLevelBonuses(levelBonuses);
        
        List<ClassSpellSlots> levelSpells = new ArrayList<ClassSpellSlots>(entity.getSpellSlots());
        levelSpells.stream().forEach(levelSpell -> levelSpell.setClasse(entity));
        entity.setSpellSlots(levelSpells);

        for (ClassSpecialization spec : entity.getClassSpecs()) {
            List<ClassSpecLevelFeature> specFeats = new ArrayList<ClassSpecLevelFeature>(spec.getClassSpecFeatures());
            specFeats.stream().forEach(feat -> feat.setClassSpec(spec));
            spec.setClassSpecFeatures(specFeats);
            spec.setParentClass(entity);
        }
        
        List<ClassEquipment> equip = new ArrayList<ClassEquipment>(entity.getStartingEquipment());
        equip.stream().forEach(item -> item.setClasse(entity));
        entity.setStartingEquipment(equip);
        
        //nested entities are automatically removed with the annotation @PrivateOwned

        //save to database with nested objects
        super.entrySaved(entity);
    }

}
