package com.dungeonstory.backend.repository.mock;

import java.util.List;
import java.util.Optional;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockUserRepository extends MockAbstractRepository<User> {

    private static Long idUser = 1L;

    public MockUserRepository() {
        super();
    }

    @Override
    public void init() {
        List<User> list = MockDataGenerator.createUsers();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(User entity) {
        if (entity.getId() == null) {
            entity.setId(idUser++);
        }
    }

    public User findByUsername(String username) {
        return entities.values().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

}
