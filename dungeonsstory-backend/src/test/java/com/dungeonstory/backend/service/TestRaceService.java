package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Race.Size;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.mock.MockRaceService;

public class TestRaceService {

    @Test
    public void testMockRaceService() {
        MockRaceService service = MockRaceService.getInstance();

        Collection<Race> allRaces = service.findAll();
        assertNotNull(allRaces);
        int size = allRaces.size();
        assertTrue(size > 0);

        Race ability = allRaces.toArray(new Race[0])[0];
        ability.setName("My Test Name");
        service.update(ability);

        Race region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allRaces = service.findAll();
        assertNotNull(allRaces);
        assertEquals(size - 1, allRaces.size());

        System.out.println(allRaces);
    }

    @Test
    public void testRaceService() throws Exception {
        RaceService service = RaceService.getInstance();

        Race newRace = service.create();
        newRace.setName("new race");
        newRace.setSize(Size.MEDIUM);
        newRace.setAverageHeight("5'4\"");
        newRace.setAverageWeight(150);
        service.create(newRace);

        Collection<Race> allRaces = service.findAll();
        assertNotNull(allRaces);
        assertTrue(allRaces.size() > 0);

        Race race = allRaces.toArray(new Race[0])[0];
        race.setName("My Test Name");
        service.update(race);

        Race race2 = service.findAll().iterator().next();
        assertEquals("My Test Name", race2.getName());

        int size = allRaces.size();
        service.delete(race2);
        allRaces = service.findAll();
        assertNotNull(allRaces);
        assertEquals(size - 1, allRaces.size());
    }

}
