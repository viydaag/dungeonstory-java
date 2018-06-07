package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.dungeonstory.backend.TestWithBackend;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockUserService;

public class TestUserService extends TestWithBackend {

    @Test
    public void testMockUserService() {
        MockUserService service = MockUserService.getInstance();

        User user1 = createDummyUser(service, "testUserService");

        Collection<User> allUsers = service.findAll();
        assertNotNull(allUsers);
        int size = allUsers.size();
        assertTrue(size > 0);

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

        User user1 = createDummyUser(service, "testUserService");

        Collection<User> allUsers = service.findAll();
        assertNotNull(allUsers);
        int size = allUsers.size();
        assertTrue(size > 0);

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
    public void testUpdatePassword() {

        UserService service = UserService.getInstance();
        User user = createDummyUser(service, "testUpdatePassword");

        user.setPassword("updatedPassword");
        User updated = service.updatePassword(user);

        assertEquals(DigestUtils.sha1Hex("updatedPassword"), updated.getPassword());

        service.delete(updated);
    }

    private User createDummyUser(UserDataService service, String name) {
        User user = service.create();
        Random r = new Random();
        int n = r.ints(1, 100).findFirst().getAsInt();
        user.setUsername(name + n);
        user.setName(name);
        user.setPassword(name);
        user.setEmail(name + "@test.com");
        service.create(user);
        return user;
    }

}
