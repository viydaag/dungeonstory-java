package com.dungeonstory.authentication;

import javax.security.auth.login.LoginException;

import com.dungeonstory.backend.LoginService;
import com.dungeonstory.backend.data.User;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {

	private static final long serialVersionUID = -5090993487106313401L;
	
	private LoginService loginService = new LoginService();

    @Override
    public boolean signIn(String username, String password) {
//        if (username == null || username.isEmpty())
//            return false;
    	
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
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
    	if (isUserSignedIn()) {
    		return CurrentUser.get().getUsername();
    	}
    	return "";
    }

}
