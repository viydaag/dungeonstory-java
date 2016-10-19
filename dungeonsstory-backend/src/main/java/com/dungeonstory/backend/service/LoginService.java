package com.dungeonstory.backend.service;

import java.io.Serializable;

import javax.security.auth.login.LoginException;

import org.apache.commons.codec.digest.DigestUtils;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockUserService;

public class LoginService implements Serializable {
    
    private static final long serialVersionUID = 383328157367737397L;
    
    private UserDataService service = null;
    
    public LoginService() {
        if (Configuration.getInstance().isMock()) {
            service = MockUserService.getInstance();
        } else {
            service = UserService.getInstance();
        }
    }

    public User login(String username, String password) throws LoginException {

        User user = service.findByUsername(username);
        if (user == null || !user.getPassword().equals(DigestUtils.md5Hex(password))) {
            throw new LoginException();
        }
        return user;
        
    }
}
