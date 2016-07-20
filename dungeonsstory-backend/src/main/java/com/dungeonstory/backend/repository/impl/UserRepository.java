package com.dungeonstory.backend.repository.impl;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.repository.AbstractRepository;

public class UserRepository extends AbstractRepository<User, Long> {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        TypedQuery<User> query = entityManager.createNamedQuery(User.findByUsername, User.class);
        query = query.setParameter("username", username);
        User user = query.getSingleResult();
        return user;
    }

}
