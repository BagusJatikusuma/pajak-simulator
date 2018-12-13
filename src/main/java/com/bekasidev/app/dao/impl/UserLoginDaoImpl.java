package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.UserLoginDao;
import com.bekasidev.app.model.User;
import com.bekasidev.app.util.LogException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDaoImpl implements UserLoginDao {
    @Override
    public User getUserLogin(String username) {
        String sql = "SELECT * FROM user_login WHERE username=?";
        User user = null;

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, username);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                user = new User(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }

        return user;
    }

    @Override
    public void updateUserLogin(User user) {
        String sql = "UPDATE user_login SET password=? WHERE username=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUsername());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO user_login VALUES(?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void updateUsername(String newUser, String oldUser) {
        String sql = "UPDATE user_login SET username=? WHERE username=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, newUser);
            pstm.setString(2, oldUser);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }
}
