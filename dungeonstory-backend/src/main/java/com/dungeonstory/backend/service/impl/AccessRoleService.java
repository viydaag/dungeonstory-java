package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.factory.impl.AccessRoleFactory;
import com.dungeonstory.backend.repository.impl.AccessRoleRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class AccessRoleService extends AbstractDataService<AccessRole, Long> {

    private static final long serialVersionUID = 6468595169787824606L;

    private static AccessRoleService instance = null;

    public static synchronized AccessRoleService getInstance() {
        if (instance == null) {
            instance = new AccessRoleService();
        }
        return instance;
    }

    private AccessRoleService() {
        super();
        setEntityFactory(new AccessRoleFactory());
        setRepository(new AccessRoleRepository());
    }

}
