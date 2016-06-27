package com.dungeonstory.samples.backend;

import javax.security.auth.login.LoginException;

import com.dungeonstory.samples.backend.data.User;

public class LoginService {

    public User login(String username, String password) throws LoginException {
        if (!username.isEmpty() && !password.isEmpty()) {
            return new User(username);
        }
        throw new LoginException();
        
        //TODO : eventuellement aller chercher le user dans la bd
    }
}
