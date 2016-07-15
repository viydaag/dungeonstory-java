package com.dungeonstory.backend;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.mock.MockRegionService;

public class TestRegionService {

    @Test
    public void testMockRegionService() {
        MockRegionService service = new MockRegionService();
        
        Collection<Region> allRegions = service.findAll();
        assertNotNull(allRegions);
        assertTrue(allRegions.size() > 0);
        
        System.out.println(allRegions);
    }

}
