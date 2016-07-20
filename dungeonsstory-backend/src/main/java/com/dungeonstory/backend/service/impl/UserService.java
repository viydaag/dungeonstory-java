package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.factory.impl.UserFactory;
import com.dungeonstory.backend.repository.impl.UserRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class UserService extends AbstractDataService<User, Long> {

    private static UserService instance = null;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        super();
        setEntityFactory(new UserFactory());
        setRepository(new UserRepository());
    }
    
    public User findByUsername(String username) {
        return ((UserRepository) entityRepository).findByUsername(username);
    }

}
