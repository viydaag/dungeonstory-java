package com.dungeonstory.ui.authentication;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.mock.MockAdminUser;
import com.dungeonstory.backend.mock.MockUser;
import com.dungeonstory.backend.service.mock.MockUserService;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {

    private static final long serialVersionUID = -5090993487106313401L;

    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        User user = null;
        if (Configuration.getInstance().isLogAsAdmin()) {
            user = new MockAdminUser();
        } else {
            user = new MockUser(username);
        }
        MockUserService.getInstance().create(user);
        CurrentUser.set(user);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return CurrentUser.get() != null;
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getRoleName().equals("admin");
        }

        // All users are in all non-admin roles
        return false;
    }

    @Override
    public String getRoleName() {
        if (isUserSignedIn()) {
            return CurrentUser.get().getUsername();
        }
        return "";
    }

}
