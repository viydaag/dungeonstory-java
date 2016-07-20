package com.dungeonstory.backend;

import javax.security.auth.login.LoginException;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.service.mock.MockUserService;

public class LoginService {
    
    private MockUserService service = MockUserService.getInstance();

    public User login(String username, String password) throws LoginException {
//        if (!username.isEmpty() && !password.isEmpty()) {
//            return new User(username);
//        }
        User user = service.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginException();
        }
        return user;
        
    }
}
