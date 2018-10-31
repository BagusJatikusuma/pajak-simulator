package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.MenuDao;
import com.bekasidev.app.dao.impl.MenuDaoImpl;
import com.bekasidev.app.model.Menu;
import com.bekasidev.app.service.backend.MenuService;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> getMenu(String idRestoran) {
        return menuDao.getAllMenu(idRestoran);
    }

    @Override
    public void createMenu(Menu menu) {
        menuDao.createMenu(menu);
    }
}
