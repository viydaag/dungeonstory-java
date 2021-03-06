package com.dungeonstory.backend.mock;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.enums.AccessRole;

public class MockUser extends User {

    private static final long serialVersionUID = -4487713155190893164L;

    public MockUser() {
        this("test");
    }

    public MockUser(String username) {
        super(username, "test", AccessRole.PLAYER, "test", "test@test.com", UserStatus.ACTIVE);
    }

}
