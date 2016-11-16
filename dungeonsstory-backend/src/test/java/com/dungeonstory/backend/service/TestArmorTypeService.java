package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.ArmorType.ProficiencyType;
import com.dungeonstory.backend.service.impl.ArmorTypeService;
import com.dungeonstory.backend.service.mock.MockArmorTypeService;

public class TestArmorTypeService {

    @Test
    public void testMockArmorTypeService() {
        MockArmorTypeService service = MockArmorTypeService.getInstance();

        Collection<ArmorType> allArmorTypes = service.findAll();
        assertNotNull(allArmorTypes);
        int size = allArmorTypes.size();
        assertTrue(size > 0);

        ArmorType ability = allArmorTypes.toArray(new ArmorType[0])[0];
        ability.setName("My Test Name");
        service.update(ability);

        ArmorType region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allArmorTypes = service.findAll();
        assertNotNull(allArmorTypes);
        assertEquals(size - 1, allArmorTypes.size());

        System.out.println(allArmorTypes);
    }

    @Test
    public void testArmorTypeService() throws Exception {
        ArmorTypeService service = ArmorTypeService.getInstance();

        ArmorType newArmorType = new ArmorType("test armure", "", ProficiencyType.LIGHT, ArmorType.NO_MAX_DEX_BONUS, 12, false, 1,
                1, 1);
        service.create(newArmorType);

        Collection<ArmorType> allArmorTypes = service.findAll();
        assertNotNull(allArmorTypes);
        assertTrue(allArmorTypes.size() > 0);

        ArmorType armorType = service.read(newArmorType.getId());
        armorType.setName("My Test Name");
        service.update(armorType);

        ArmorType armorType2 = service.read(armorType.getId());
        assertEquals("My Test Name", armorType2.getName());

        int size = allArmorTypes.size();
        service.delete(armorType2);
        allArmorTypes = service.findAll();
        assertNotNull(allArmorTypes);
        assertEquals(size - 1, allArmorTypes.size());
    }

}
