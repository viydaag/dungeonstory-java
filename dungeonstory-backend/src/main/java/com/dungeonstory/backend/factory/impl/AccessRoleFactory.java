package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.factory.Factory;

public class AccessRoleFactory implements Factory<AccessRole> {

    private static final long serialVersionUID = -4305778711907195883L;

    @Override
    public AccessRole create() {
        return new AccessRole();
    }

}
