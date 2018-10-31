package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenu(String idRestoran);

    void createMenu(Menu menu);
}
