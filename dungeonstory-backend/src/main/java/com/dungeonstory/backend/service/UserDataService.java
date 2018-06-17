package com.dungeonstory.backend.service;

import com.dungeonstory.backend.data.User;

public interface UserDataService extends DataService<User, Long> {

    public User findByUsername(String username);

    public User updatePassword(User user);
	
}
