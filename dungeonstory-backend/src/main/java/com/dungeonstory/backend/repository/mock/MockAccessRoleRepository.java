package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockAccessRoleRepository extends MockAbstractRepository<AccessRole> {

    private static Long idRole = 1L;

    public MockAccessRoleRepository() {
        super();
    }

    @Override
    public void init() {
        List<AccessRole> list = MockDataGenerator.createRoles();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(AccessRole entity) {
        if (entity.getId() == null) {
            entity.setId(idRole++);
        }
    }

}
