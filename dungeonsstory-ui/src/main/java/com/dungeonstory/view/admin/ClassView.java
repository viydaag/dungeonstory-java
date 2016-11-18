package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ClassEquipment;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.form.ClassForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.CrudView;
import com.dungeonstory.view.grid.ClassGrid;
import com.dungeonstory.view.grid.DSGrid;

@ViewConfig(uri = "classes", displayName = "Classes")
public class ClassView extends AbstractCrudView<DSClass> implements CrudView<DSClass> {

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
    public DataService<DSClass, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockClassService.getInstance();
        }
        return ClassService.getInstance();
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
        
        Set<ClassEquipment> equip = new HashSet<ClassEquipment>(entity.getStartingEquipment());
        equip.stream().forEach(item -> item.setClasse(entity));
        entity.setStartingEquipment(equip);
        
        //nested entities are automatically removed with the annotation @PrivateOwned

        //save to database with nested objects
        super.entrySaved(entity);
    }

}
