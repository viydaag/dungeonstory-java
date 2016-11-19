package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.mock.MockClassService;

public class TestClassService {

    @Test
    public void testMockClassService() {
        MockClassService service = MockClassService.getInstance();

        Collection<DSClass> allClasss = service.findAll();
        assertNotNull(allClasss);
        int size = allClasss.size();
        assertTrue(size > 0);

        DSClass ability = allClasss.toArray(new DSClass[0])[0];
        ability.setName("My Test Name");
        service.update(ability);

        DSClass region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allClasss = service.findAll();
        assertNotNull(allClasss);
        assertEquals(size - 1, allClasss.size());

        System.out.println(allClasss);
    }

    @Test
    public void testClassService() throws Exception {
        ClassService service = ClassService.getInstance();

        DSClass entity = new DSClass();
        entity.setName("test class");
        service.create(entity);

        Collection<DSClass> allClasss = service.findAll();
        assertNotNull(allClasss);
        assertTrue(allClasss.size() > 0);

        DSClass region = allClasss.toArray(new DSClass[0])[0];
        region.setName("My Test Name");
        service.update(region);

        DSClass region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        int size = allClasss.size();
        service.delete(region2);
        allClasss = service.findAll();
        assertNotNull(allClasss);
        assertTrue(size > allClasss.size());
    }
    
    @Test
    public void testDeleteClassLevelBonusFeat() throws Exception {
        ClassService service = ClassService.getInstance();

        DSClass entity = new DSClass();
        entity.setName("test class 2");
        
        Level level1 = new Level(100, 1000, 1);
        LevelService.getInstance().create(level1);
        Level level2 = new Level(200, 2000, 2);
        LevelService.getInstance().create(level2);
        
        Feat feat1 = new Feat("test feat for class", "", FeatUsage.ACTION);
        FeatService.getInstance().create(feat1);
        
        ClassLevelFeature bonusFeat1 = new ClassLevelFeature(entity, level1, feat1);
        ClassLevelFeature bonusFeat2 = new ClassLevelFeature(entity, level2, feat1);
        
        List<ClassLevelFeature> bonusFeats = new ArrayList<ClassLevelFeature>();
        bonusFeats.add(bonusFeat1);
        bonusFeats.add(bonusFeat2);
        
        entity.setClassFeatures(bonusFeats);
        service.create(entity);
        
        assertEquals(2, entity.getClassFeatures().size());

//        service.deleteClassLevelBonusFeat(bonusFeat1);
        entity.getClassFeatures().remove(bonusFeat1);
        entity = service.update(entity);
        
//        service.refresh(entity);
        
        assertEquals(1, entity.getClassFeatures().size());
    }

}
