package com.dungeonstory.backend.mock;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.enums.AccessRole;

public class MockAdminUser extends User {

    private static final long serialVersionUID = -4304245147161838757L;

    public MockAdminUser() {
        this("admin");
    }

    public MockAdminUser(String username) {
        super(username, "admin", AccessRole.ADMIN, "admin", "admin@test.com", UserStatus.ACTIVE);
    }

}
