package com.dungeonstory.backend.service;

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

        Alignment newAlign = service.create();
        newAlign.setName("new align");
        newAlign.setAbbreviation("NA");
        service.create(newAlign);

        Collection<Alignment> allAlignments = service.findAll();
        assertNotNull(allAlignments);
        assertTrue(allAlignments.size() > 0);

        Alignment alignment = service.read(newAlign.getId());
        alignment.setName("My Test Name");
        service.update(alignment);

        Alignment alignment2 = service.read(alignment.getId());
        assertEquals("My Test Name", alignment2.getName());

        int size = allAlignments.size();
        service.delete(alignment2);
        allAlignments = service.findAll();
        assertNotNull(allAlignments);
        assertEquals(size - 1, allAlignments.size());
    }

}
