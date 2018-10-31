package com.bekasidev.app.dao;

import com.bekasidev.app.model.Menu;

import java.util.List;

public interface MenuDao {

    List<Menu> getAllMenu(String idRestoran);

    void createMenu(Menu menu);
}
