package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.backend.service.mock.MockRegionService;

public class TestRegionService {

    @Test
    public void testMockRegionService() {
        MockRegionService service = MockRegionService.getInstance();
        
        Collection<Region> allRegions = service.findAll();
        assertNotNull(allRegions);
        int size = allRegions.size();
		assertTrue(size > 0);
        
        Region region = allRegions.toArray(new Region[0])[0];
        region.setName("My Test Name");
        service.update(region);
        
        Region region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allRegions = service.findAll();
        assertNotNull(allRegions);
        assertEquals(size - 1, allRegions.size());
        
        System.out.println(allRegions);
    }
    
    @Test
    public void testRegionService() throws Exception {
        RegionService service = RegionService.getInstance();
        
        service.create(new Region("My region"));
        
        Collection<Region> allRegions = service.findAll();
        assertNotNull(allRegions);
        assertTrue(allRegions.size() > 0);
        
        Region region = allRegions.toArray(new Region[0])[0];
        region.setName("My Test Name");
        service.update(region);
        
        Region region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allRegions = service.findAll();
        assertNotNull(allRegions);
        assertTrue(allRegions.size() == 0);
    }

}
