package com.bekasidev.app.dao;

import com.bekasidev.app.model.User;

public interface UserLoginDao {

    User getUserLogin(String username);

    void updateUserLogin(User user);

    void createUser(User user);

    void updateUsername(String newUser, String oldUser);
}
