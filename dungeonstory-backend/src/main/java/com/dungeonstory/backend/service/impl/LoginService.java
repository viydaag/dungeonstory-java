package com.dungeonstory.backend.service.impl;

import java.io.Serializable;

import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.backend.service.UserDataService;

public class LoginService implements Serializable {
    
    private static final long serialVersionUID = 383328157367737397L;
    
    private UserDataService service = null;
    
    public LoginService() {
        service = Services.getUserService();
    }

    public User login(String username, String password) throws LoginException {

        User user = service.findByUsername(username);
        if (user == null || !user.getPassword().equals(DigestUtils.sha1Hex(password))) {
            throw new LoginException();
        }
        return user;
        
    }
}
