package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;

public class TestWeaponTypeService {

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
        DamageTypeService dmService = DamageTypeService.getInstance();

        dmService.create(new DamageType("un type de dommage"));
        List<DamageType> findAll = dmService.findAll();
        DamageType dm = findAll.get(findAll.size() - 1);
        assertNotNull(dm);
        service.create(new WeaponType("test weapon", ProficiencyType.SIMPLE, SizeType.MEDIUM, HandleType.TWO_HANDED,
                UsageType.MELEE, dm));

        Collection<WeaponType> allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        assertTrue(allWeaponTypes.size() > 0);

        WeaponType region = allWeaponTypes.toArray(new WeaponType[0])[0];
        region.setName("My Test Name");
        service.update(region);

        WeaponType region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allWeaponTypes = service.findAll();
        assertNotNull(allWeaponTypes);
        assertTrue(allWeaponTypes.size() == 0);
    }

}
