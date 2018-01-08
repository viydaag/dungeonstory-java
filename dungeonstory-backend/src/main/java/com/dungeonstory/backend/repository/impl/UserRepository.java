package com.dungeonstory.backend.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.repository.AbstractRepository;

public class UserRepository extends AbstractRepository<User, Long> {

    private static final long serialVersionUID = 2373570896727026456L;

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
        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    public int updatePassword(Long userId, String password) {
        int result = 0;
        if (userId == null || password == null) {
            throw new IllegalArgumentException("userId and password cannot be null while updating password");
        }
        TypedQuery<User> query = entityManager.createNamedQuery(User.updatePassword, User.class);
        query = query.setParameter("password", password).setParameter("userId", userId);

        try {
            entityManager.getTransaction().begin();
            result = query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager.getTransaction());
        }
        return result;
    }

}
