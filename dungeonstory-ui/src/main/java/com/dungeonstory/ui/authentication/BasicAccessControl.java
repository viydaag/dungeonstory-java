package com.dungeonstory.ui.authentication;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.enums.AccessRole;
import com.dungeonstory.backend.mock.MockAdminUser;
import com.dungeonstory.backend.mock.MockUser;
import com.dungeonstory.backend.service.mock.MockUserService;
import com.vaadin.server.VaadinSession;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {

    private static final long serialVersionUID = -5090993487106313401L;

    @Override
    public boolean signIn(String username, String password, boolean rememberMe) {
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
        return CurrentUser.isLoggedIn();
    }

    @Override
    public boolean isUserInRole(AccessRole role) {
//        if (AccessRole2.ADMIN == role) {
//            // Only the "admin" user is in the "admin" role
//            return getRole() == AccessRole2.ADMIN;
//        }
//
//        // All users are in all non-admin roles
//        return false;
        return getRole() == role;
    }

    @Override
    public AccessRole getRole() {
        if (isUserSignedIn()) {
            if (CurrentUser.get().getUsername().equals("admin")) {
                return AccessRole.ADMIN;
            }
        }
        return AccessRole.PLAYER;
    }

    @Override
    public void signout() {
        CurrentUser.set(null);

        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }

}
