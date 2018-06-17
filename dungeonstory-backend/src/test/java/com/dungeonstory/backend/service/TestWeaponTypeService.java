package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.TestWithBackend;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.data.enums.DamageType;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;

public class TestWeaponTypeService extends TestWithBackend {

    @Test
    public void testMockWeaponTypeService() {
        MockWeaponTypeService service = MockWeaponTypeService.getInstance();

        Collection<WeaponType> allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        int size = allWeaponTypes.size();
        assertTrue(size > 0);

        WeaponType ability = allWeaponTypes.toArray(new WeaponType[0])[0];
        ability.setName("My Test Name");
        service.update(ability);

        WeaponType region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        assertEquals(size - 1, allWeaponTypes.size());

        System.out.println(allWeaponTypes);
    }

    @Test
    public void testWeaponTypeService() throws Exception {
        WeaponTypeService service = WeaponTypeService.getInstance();

        WeaponType newWt = new WeaponType("test weapon", ProficiencyType.SIMPLE, SizeType.MEDIUM, HandleType.TWO_HANDED,
                UsageType.MELEE, DamageType.SLASHING);
        service.create(newWt);

        Collection<WeaponType> allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        assertTrue(allWeaponTypes.size() > 0);

        WeaponType weaponType = service.read(newWt.getId());
        weaponType.setName("My Test Name");
        service.update(weaponType);

        WeaponType weaponType2 = service.read(weaponType.getId());
        assertEquals("My Test Name", weaponType2.getName());

        int size = allWeaponTypes.size();
        service.delete(weaponType2);
        allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        assertEquals(size - 1, allWeaponTypes.size());
    }

}
