package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AccessRoleRepository extends AbstractRepository<AccessRole, Long> {

    private static final long serialVersionUID = -4451030772782301270L;

    @Override
    protected Class<AccessRole> getEntityClass() {
        return AccessRole.class;
    }

}
