package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.mock.MockAbilityService;

public class TestAbilityService {

    @Test
    public void testMockAbilityService() {
        MockAbilityService service = MockAbilityService.getInstance();

        Collection<Ability> allAbilitys = service.findAll();
        assertNotNull(allAbilitys);
        int size = allAbilitys.size();
        assertTrue(size > 0);

        Ability ability = allAbilitys.toArray(new Ability[0])[0];
        ability.setName("My Test Name");
        service.update(ability);

        Ability ability2 = service.findAll().iterator().next();
        assertEquals("My Test Name", ability2.getName());

        service.delete(ability2);
        allAbilitys = service.findAll();
        assertNotNull(allAbilitys);
        assertEquals(size - 1, allAbilitys.size());

        System.out.println(allAbilitys);
    }

    @Test
    public void testAbilityService() throws Exception {
        AbilityService service = AbilityService.getInstance();

        Ability newAbility = service.create();
        newAbility.setName("new ability");
        newAbility.setAbbreviation("NA");
        service.create(newAbility);

        Collection<Ability> allAbilitys = service.findAll();
        assertNotNull(allAbilitys);
        assertTrue(allAbilitys.size() > 0);

        Ability ability = service.read(newAbility.getId());
        ability.setName("My Test Name");
        service.update(ability);

        Ability ability2 = service.read(ability.getId());
        assertEquals("My Test Name", ability2.getName());

        int size = allAbilitys.size();
        service.delete(ability2);
        allAbilitys = service.findAll();
        assertNotNull(allAbilitys);
        assertEquals(size - 1, allAbilitys.size());
    }

}
