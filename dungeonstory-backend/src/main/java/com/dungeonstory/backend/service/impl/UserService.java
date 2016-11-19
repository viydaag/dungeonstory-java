package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.factory.impl.UserFactory;
import com.dungeonstory.backend.repository.impl.UserRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.UserDataService;

public class UserService extends AbstractDataService<User, Long> implements UserDataService {

	private static final long serialVersionUID = 2368180652957632605L;
	
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

    @Override
    public User findByUsername(String username) {
        return ((UserRepository) entityRepository).findByUsername(username);
    }

}