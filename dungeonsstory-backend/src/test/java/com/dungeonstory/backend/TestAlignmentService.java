package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.mock.MockAlignmentService;

public class TestAlignmentService {

    @Test
    public void testMockAlignmentService() {
        MockAlignmentService service = MockAlignmentService.getInstance();
        
        Collection<Alignment> allAlignments = service.findAll();
        assertNotNull(allAlignments);
        int size = allAlignments.size();
		assertTrue(size > 0);
        
        Alignment ability = allAlignments.toArray(new Alignment[0])[0];
        ability.setName("My Test Name");
        service.update(ability);
        
        Alignment region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allAlignments = service.findAll();
        assertNotNull(allAlignments);
        assertEquals(size - 1, allAlignments.size());
        
        System.out.println(allAlignments);
    }
    
    @Test
    public void testAlignmentService() throws Exception {
        AlignmentService service = AlignmentService.getInstance();
        
        service.create(new Alignment("new alignment", "NA", ""));
        
        Collection<Alignment> allAlignments = service.findAll();
        assertNotNull(allAlignments);
        assertTrue(allAlignments.size() > 0);
        
        Alignment region = allAlignments.toArray(new Alignment[0])[0];
        region.setName("My Test Name");
        service.update(region);
        
        Alignment region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allAlignments = service.findAll();
        assertNotNull(allAlignments);
        assertTrue(allAlignments.size() == 0);
    }

}
