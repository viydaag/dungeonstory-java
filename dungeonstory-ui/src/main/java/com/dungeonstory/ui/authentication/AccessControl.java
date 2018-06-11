package com.dungeonstory.ui.authentication;

import java.io.Serializable;

import com.dungeonstory.backend.data.enums.AccessRole;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControl extends Serializable {
    
    enum LoginStatus {
        OK,
        WRONG_USERNAME,
        WRONG_PASSWORD,
        USER_INACTIVE,
        WAITING_FOR_APPROBATION
    }

    public boolean signIn(String username, String password, boolean rememberMe);
    
    public void signout();

    public boolean isUserSignedIn();

    public boolean isUserInRole(AccessRole role);

    public AccessRole getRole();
}
