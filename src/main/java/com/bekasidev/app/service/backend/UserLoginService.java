package com.bekasidev.app.service.backend;

public interface UserLoginService {

    boolean login(String username, String password);

    void updateUser(String username, String password);

    void createUser(String username, String password);
}
