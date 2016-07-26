package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.mock.MockFeatService;

public class TestFeatService {

    @Test
    public void testMockFeatService() {
        MockFeatService service = MockFeatService.getInstance();

        Collection<Feat> allFeats = service.findAll();
        assertNotNull(allFeats);
        int size = allFeats.size();
        assertTrue(size > 0);

        Feat feat = allFeats.toArray(new Feat[0])[0];
        feat.setName("My Test Name");
        service.update(feat);

        Feat feat2 = service.findAll().iterator().next();
        assertEquals("My Test Name", feat2.getName());

        service.delete(feat2);
        allFeats = service.findAll();
        assertNotNull(allFeats);
        assertEquals(size - 1, allFeats.size());

        System.out.println(allFeats);
    }

    @Test
    public void testFeatService() throws Exception {
        FeatService service = FeatService.getInstance();

        service.create(new Feat("new feat", "NA", FeatUsage.ACTION));

        Collection<Feat> allFeats = service.findAll();
        assertNotNull(allFeats);
        assertTrue(allFeats.size() > 0);

        Feat feat = allFeats.toArray(new Feat[0])[0];
        feat.setName("My Test Name");
        service.update(feat);

        Feat feat2 = service.findAll().iterator().next();
        assertEquals("My Test Name", feat2.getName());

        service.delete(feat2);
        allFeats = service.findAll();
        assertNotNull(allFeats);
        assertTrue(allFeats.size() == 0);
    }

}
