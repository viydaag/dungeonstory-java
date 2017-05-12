package com.dungeonstory.backend.mock;

import javax.persistence.Entity;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.AccessRole.RoleType;

@Entity
public class MockAdminUser extends User {

    private static final long serialVersionUID = -4304245147161838757L;

    public MockAdminUser() {
        this("admin");
    }

    public MockAdminUser(String username) {
        super(username, "admin", new AccessRole("administrator", RoleType.ADMIN), "admin", "admin@test.com", UserStatus.ACTIVE);
    }

}