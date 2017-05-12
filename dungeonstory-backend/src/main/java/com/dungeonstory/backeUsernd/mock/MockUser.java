package com.dungeonstory.backeUsernd.mock;

import javax.persistence.Entity;

import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.AccessRole.RoleType;
import com.dungeonstory.backend.data.User;

@Entity
public class MockUser extends User {

    private static final long serialVersionUID = -4487713155190893164L;

    public MockUser() {
        this("test");
    }

    public MockUser(String username) {
        super(username, "test", new AccessRole("player", RoleType.PLAYER), "test", "test@test.com", UserStatus.ACTIVE);
    }

}
