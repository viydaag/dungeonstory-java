package com.dungeonstory.authentication;

import javax.security.auth.login.LoginException;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.impl.LoginService;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class DsAccessControl implements AccessControl {

    private static final long serialVersionUID = -5090993487106313401L;

    private LoginService      loginService     = new LoginService();

    @Override
    public boolean signIn(String username, String password) {
        try {
            User user = loginService.login(username, password);
            CurrentUser.set(user);
        } catch (LoginException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return CurrentUser.get() != null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return getRoleName().equals(role);
    }

    @Override
    public String getRoleName() {
        if (isUserSignedIn()) {
            if (CurrentUser.get().getRole() != null) {
                return CurrentUser.get().getRole().getType().name();
            }
        }
        return "";
    }

}
