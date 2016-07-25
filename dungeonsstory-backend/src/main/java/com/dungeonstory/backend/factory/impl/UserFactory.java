package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
import com.dungeonstory.backend.factory.Factory;

public class UserFactory implements Factory<User> {

    @Override
    public User create() {
        return new User("", "", null, "", "", UserStatus.WAITING_FOR_APPROBATION);
    }
}
