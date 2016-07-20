package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;

public class TestDamageTypeService {

    @Test
    public void testMockDamageTypeService() {
        MockDamageTypeService service = MockDamageTypeService.getInstance();
        
        Collection<DamageType> allDamageTypes = service.findAll();
        assertNotNull(allDamageTypes);
        int size = allDamageTypes.size();
		assertTrue(size > 0);
        
        DamageType ability = allDamageTypes.toArray(new DamageType[0])[0];
        ability.setName("My Test Name");
        service.update(ability);
        
        DamageType region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allDamageTypes = service.findAll();
        assertNotNull(allDamageTypes);
        assertEquals(size - 1, allDamageTypes.size());
        
        System.out.println(allDamageTypes);
    }
    
    @Test
    public void testDamageTypeService() throws Exception {
        DamageTypeService service = DamageTypeService.getInstance();
        
        service.create(new DamageType("test"));
        
        Collection<DamageType> allDamageTypes = service.findAll();
        assertNotNull(allDamageTypes);
        assertTrue(allDamageTypes.size() > 0);
        
        DamageType dm = allDamageTypes.toArray(new DamageType[0])[0];
        dm.setName("My Test Name");
        service.update(dm);
        
        DamageType dm2 = service.findAll().iterator().next();
        assertEquals("My Test Name", dm2.getName());
        
        service.delete(dm2);
        allDamageTypes = service.findAll();
        assertNotNull(allDamageTypes);
        assertTrue(allDamageTypes.size() == 0);
    }

}
