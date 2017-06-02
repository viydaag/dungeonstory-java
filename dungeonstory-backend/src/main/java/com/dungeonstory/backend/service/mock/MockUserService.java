package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.repository.mock.MockUserRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.UserDataService;

public class MockUserService extends AbstractDataService<User, Long> implements UserDataService {

    private static final long serialVersionUID = 6562852964392301569L;

    private static MockUserService instance = null;

    public static synchronized MockUserService getInstance() {
        if (instance == null) {
            instance = new MockUserService();
        }
        return instance;
    }

    private MockUserService() {
        super();
        setEntityFactory(() -> new User());
        setRepository(new MockUserRepository());
    }

    @Override
    public User findByUsername(String username) {
        return ((MockUserRepository) entityRepository).findByUsername(username);
    }

}
