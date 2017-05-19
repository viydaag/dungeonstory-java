package com.dungeonstory.backend.service;

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

        User user1 = allUsers.toArray(new User[0])[0];
        user1.setName("My Test Name");
        service.update(user1);

        User user2 = service.read(user1.getId());
        assertEquals("My Test Name", user2.getName());

        service.delete(user2);
        allUsers = service.findAll();
        assertNotNull(allUsers);
        assertEquals(size - 1, allUsers.size());

        System.out.println(allUsers);
    }

    @Test
    public void testUserService() throws Exception {
        UserService service = UserService.getInstance();

        Collection<User> allUsers = service.findAll();
        assertNotNull(allUsers);
        int size = allUsers.size();
        assertTrue(size > 0);

        User user1 = allUsers.toArray(new User[0])[0];
        user1.setName("My Test Name");
        service.update(user1);

        User user2 = service.read(user1.getId());
        assertEquals("My Test Name", user2.getName());

        service.delete(user2);
        allUsers = service.findAll();
        assertNotNull(allUsers);
        assertEquals(size - 1, allUsers.size());

        System.out.println(allUsers);
    }

}
