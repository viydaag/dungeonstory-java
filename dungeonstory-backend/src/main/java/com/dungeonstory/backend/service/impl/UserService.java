package com.dungeonstory.backend.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
import com.dungeonstory.backend.data.enums.AccessRole;
import com.dungeonstory.backend.repository.impl.UserRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.UserDataService;

public class UserService
        extends AbstractDataService<User, Long>
        implements UserDataService {

    private static final long serialVersionUID = 2368180652957632605L;

    private static UserService instance = null;
    private UserRepository     repository = null;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        super();
        repository = new UserRepository();
        setEntityFactory(() -> new User());
        setRepository(repository);
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
    public User updatePassword(User entity) {
        entity.setPassword(DigestUtils.sha1Hex(entity.getPassword()));
        repository.updatePassword(entity.getId(), entity.getPassword());
        refresh(entity);
        return entity;
    }

}
