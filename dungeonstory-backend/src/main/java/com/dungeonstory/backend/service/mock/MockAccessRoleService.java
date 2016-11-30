package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.factory.impl.AccessRoleFactory;
import com.dungeonstory.backend.repository.mock.MockAccessRoleRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockAccessRoleService extends AbstractDataService<AccessRole, Long> {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockAccessRoleService instance = null;

    public static synchronized MockAccessRoleService getInstance() {
        if (instance == null) {
            instance = new MockAccessRoleService();
        }
        return instance;
    }

    private MockAccessRoleService() {
        super();
        setEntityFactory(new AccessRoleFactory());
        setRepository(new MockAccessRoleRepository());
    }

}
