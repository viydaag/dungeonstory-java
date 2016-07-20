package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockUserService;

public class TestUserService {

    @Test
    public void testMockUserService() {
        MockUserService service = MockUserService.getInstance();
        
        Collection<User> allUsers = service.findAll();
        assertNotNull(allUsers);
        int size = allUsers.size();
		assertTrue(size > 0);
        
        User ability = allUsers.toArray(new User[0])[0];
        ability.setName("My Test Name");
        service.update(ability);
        
        User region2 = service.findAll().iterator().next();
        assertEquals("My Test Name", region2.getName());
        
        service.delete(region2);
        allUsers = service.findAll();
        assertNotNull(allUsers);
        assertEquals(size - 1, allUsers.size());
        
        System.out.println(allUsers);
    }
    
//    @Test
//    public void testUserService() throws Exception {
//        UserService service = UserService.getInstance();
//        
//        service.create(new User("new ability", "NA", ""));
//        
//        Collection<User> allUsers = service.findAll();
//        assertNotNull(allUsers);
//        assertTrue(allUsers.size() > 0);
//        
//        User region = allUsers.toArray(new User[0])[0];
//        region.setName("My Test Name");
//        service.update(region);
//        
//        User region2 = service.findAll().iterator().next();
//        assertEquals("My Test Name", region2.getName());
//        
//        service.delete(region2);
//        allUsers = service.findAll();
//        assertNotNull(allUsers);
//        assertTrue(allUsers.size() == 0);
//    }

}
