package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.backend.service.mock.MockAbilityService;

public class TestCharacterService {

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
    public void testCharacterService() throws Exception {
        CharacterService service = CharacterService.getInstance();

        Random r = new Random();
        String name = "Character" + r.ints(0, (10000 + 1)).findFirst().getAsInt();

        Character c = service.create();
        c.setName(name);
        c.setGender(Gender.M);
        c.setHeight("5'7\"");
        c.setWeight(150);
        c.setAlignment(AlignmentService.getInstance().read(1L));
        c.setRegion(RegionService.getInstance().read(1L));
        c.setRace(RaceService.getInstance().read(1L));
        c.setImage("/male/abeirL.bmp");

        //        CharacterBackground cBackground = new CharacterBackground();
        //        cBackground.setBackground(BackgroundService.getInstance().read(1L));
        //        c.setBackground(cBackground);

        service.saveOrUpdate(c);

        List<Character> list = service.findAllBy("name", name);
        assertEquals(1, list.size());

        Character saved = list.get(0);
        assertNotNull(saved.getId());
        assertEquals(name, saved.getName());

        service.delete(saved);

    }

}
