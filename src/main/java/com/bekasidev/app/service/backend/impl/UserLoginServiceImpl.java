package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.UserLoginDao;
import com.bekasidev.app.dao.impl.UserLoginDaoImpl;
import com.bekasidev.app.model.User;
import com.bekasidev.app.service.backend.UserLoginService;
import com.bekasidev.app.util.EncryptDecrypt;

public class UserLoginServiceImpl implements UserLoginService {

    UserLoginDao userLoginDao = new UserLoginDaoImpl();

    @Override
    public boolean login(String username, String password) {
        User user = userLoginDao.getUserLogin(setNip(username));
        if(user == null) return false;
        String[] pass = user.getPassword().split("<b>");

        return EncryptDecrypt.verifyUserPassword(password, pass[0], pass[1]);
    }

    @Override
    public void updateUser(String username, String password) {
        String salt = EncryptDecrypt.getSalt(30);
        password = EncryptDecrypt.generateSecurePassword(password, salt) + "<b>" + salt;
        userLoginDao.updateUserLogin(new User(username, password));
    }

    @Override
    public void createUser(String username, String password) {
        String salt = EncryptDecrypt.getSalt(30);
        password = EncryptDecrypt.generateSecurePassword(password, salt) + "<b>" + salt;
        userLoginDao.createUser(new User(username, password));
    }

    private String setNip(String nip){
        StringBuilder sb = new StringBuilder();
        sb.append(nip);
        sb.insert(8," ");
        sb.insert(15," ");
        sb.insert(17," ");
        return sb.toString();
    }
}
