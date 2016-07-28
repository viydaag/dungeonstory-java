package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelBonusFeat;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.form.ClassForm;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.samples.crud.BeanGrid;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.CrudView;
import com.dungeonstory.view.component.ClassGrid;

@ViewConfig(uri = "classes", displayName = "Classes")
public class ClassView extends AbstractCrudView<DSClass> implements CrudView<DSClass> {

	private static final long serialVersionUID = 5117755861151432771L;
	
	public ClassView() {
	    super();
        setFormPopup(true);
    }

    @Override
    public DSAbstractForm<DSClass> getForm() {
        return new ClassForm();
    }

    @Override
    public BeanGrid<DSClass> getGrid() {
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
        List<ClassLevelBonusFeat> feats = new ArrayList<ClassLevelBonusFeat>(entity.getFeatBonuses());
        feats.stream().forEach(feat -> feat.setClasse(entity));
        entity.setFeatBonuses(feats);
        
        List<ClassLevelBonus> levelBonuses = new ArrayList<ClassLevelBonus>(entity.getLevelBonuses());
        levelBonuses.stream().forEach(levelBonus -> levelBonus.setClasse(entity));
        entity.setLevelBonuses(levelBonuses);
        
        //remove the class bonus feats manually if some were removed in the form
//        if (entity.getId() != null) {
//            
//            DSClass currentClass = service.read(entity.getId());
//            service.refresh(currentClass);
//            List<ClassLevelBonusFeat> currentFeats = new ArrayList<ClassLevelBonusFeat>(currentClass.getFeatBonuses());
//            //calculate the difference between persisted feats and new feats
//            //remaining feats will be removed since they are not in the new feat list
//            currentFeats.removeAll(feats);
//            
//            currentFeats.stream().forEach(feat -> ((ClassService) service).deleteClassLevelBonusFeat(feat));
//        }
        
        //save to database with nested objects
        super.entrySaved(entity);
    }

}
