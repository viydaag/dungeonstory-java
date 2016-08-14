package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Race;
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

        service.create(new Race("new race"));

        Collection<Race> allRaces = service.findAll();
        assertNotNull(allRaces);
        assertTrue(allRaces.size() > 0);

        Race region = allRaces.toArray(new Race[0])[0];
        region.setName("My Test Name");
        service.update(region);

        Race region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());

        service.delete(region2);
        allRaces = service.findAll();
        assertNotNull(allRaces);
        assertTrue(allRaces.size() == 0);
    }

}
