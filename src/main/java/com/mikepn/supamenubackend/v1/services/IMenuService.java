package com.mikepn.supamenubackend.v1.services;

import com.mikepn.supamenubackend.v1.dto.request.menu.CreateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.request.menu.UpdateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.response.menu.MenuResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IMenuService {


    MenuResponseDTO createMenu(CreateMenuDTO dto);
    MenuResponseDTO getMenuById(UUID menuId);
    List<MenuResponseDTO> getAllMenus();
    List<MenuResponseDTO> getMenusByRestaurantId(UUID restaurantId);
    MenuResponseDTO updateMenu(UUID menuId, UpdateMenuDTO dto);
    void deleteMenu(UUID menuId);
}
