package com.dungeonstory.authentication;

import com.dungeonstory.backend.data.User;

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
        User user = new User(username);
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
        return true;
    }

    @Override
    public String getRoleName() {
    	if (isUserSignedIn()) {
    		return CurrentUser.get().getUsername();
    	}
    	return "";
    }

}
