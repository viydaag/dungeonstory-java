package com.dungeonstory.backend.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
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
        setEntityFactory(() -> new User());
        setRepository(new UserRepository());
    }

    @Override
    public User findByUsername(String username) {
        return ((UserRepository) entityRepository).findByUsername(username);
    }

    @Override
    public void create(User entity) {
        entity.setPassword(DigestUtils.sha1Hex(entity.getPassword()));
        entity.setRole(AccessRole.PLAYER);
        entity.setStatus(UserStatus.WAITING_FOR_APPROBATION);
        super.create(entity);
    }
    
    @Override
    public User update(User entity) {
        entity.setPassword(DigestUtils.sha1Hex(entity.getPassword()));
        return super.update(entity);
    }

}
