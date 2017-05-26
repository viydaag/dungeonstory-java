package com.dungeonstory.ui.authentication;

import java.io.Serializable;

import com.dungeonstory.backend.data.AccessRole;

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

    public boolean signIn(String username, String password);

    public boolean isUserSignedIn();

    public boolean isUserInRole(AccessRole role);

    public AccessRole getRole();
}
