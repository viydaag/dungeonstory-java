package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.factory.impl.UserFactory;
import com.dungeonstory.backend.repository.impl.UserRepository;
import com.dungeonstory.backend.repository.mock.MockUserRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockUserService extends AbstractDataService<User, Long> {

    private static MockUserService instance = null;

    public static synchronized MockUserService getInstance() {
        if (instance == null) {
            instance = new MockUserService();
        }
        return instance;
    }
    
    private MockUserService() {
        super();
        setEntityFactory(new UserFactory());
        setRepository(new MockUserRepository());
    }

    public User findByUsername(String username) {
        return ((MockUserRepository) entityRepository).findByUsername(username);
    }
    
    
}
