package com.mikepn.supamenubackend.v1.services.impl;


import com.mikepn.supamenubackend.v1.dto.request.menu.CreateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.request.menu.UpdateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.response.menu.MenuResponseDTO;
import com.mikepn.supamenubackend.v1.exceptions.AppException;
import com.mikepn.supamenubackend.v1.exceptions.NotFoundException;
import com.mikepn.supamenubackend.v1.models.Menu;
import com.mikepn.supamenubackend.v1.models.Restaurant;
import com.mikepn.supamenubackend.v1.repositories.IMenuRepository;
import com.mikepn.supamenubackend.v1.repositories.IRestaurantRepository;
import com.mikepn.supamenubackend.v1.services.IMenuService;
import com.mikepn.supamenubackend.v1.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final IMenuRepository menuRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public MenuResponseDTO createMenu(CreateMenuDTO dto) {
        try{
            Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                    .orElseThrow(() -> new NotFoundException("Restaurant Not Found"));

            Menu menu = Menu.builder()
                    .restaurant(restaurant)
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();

            menu = menuRepository.save(menu);

            MenuResponseDTO response = Mapper.getMapper().map(menu, MenuResponseDTO.class);

            return response;
        }catch (Exception e){
            throw new AppException("Failed to create menu: " + e.getMessage());
        }
    }

    @Override
    public MenuResponseDTO getMenuById(UUID menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu Not Found"));
        return Mapper.getMapper().map(menu, MenuResponseDTO.class);
    }

    @Override
    public List<MenuResponseDTO> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(menu -> Mapper.getMapper().map(menu, MenuResponseDTO.class))
                .toList();
    }

    @Override
    public List<MenuResponseDTO> getMenusByRestaurantId(UUID restaurantId) {
        return menuRepository.findAllByRestaurant_Id(restaurantId)
                .stream()
                .map(menu -> Mapper.getMapper().map(menu, MenuResponseDTO.class))
                .toList();
    }

    @Override
    public MenuResponseDTO updateMenu(UUID menuId, UpdateMenuDTO dto) {

        try{
            Menu menu = menuRepository.findById(menuId)
                    .orElseThrow(() -> new NotFoundException("Menu Not Found"));

            if(dto.getName() != null){
                menu.setName(dto.getName());
            }
            if(dto.getDescription() != null){
                menu.setDescription(dto.getDescription());
            }

            menu = menuRepository.save(menu);

            return Mapper.getMapper().map(menu, MenuResponseDTO.class);

        } catch (Exception e) {
            throw new AppException("Failed to update menu: " + e.getMessage());
        }
    }

    @Override
    public void deleteMenu(UUID menuId) {
        try{
            Menu menu = menuRepository.findById(menuId)
                    .orElseThrow(() -> new NotFoundException("Menu Not Found"));
            menuRepository.delete(menu);
        }catch (Exception e) {
            throw new AppException("Failed to delete menu: " + e.getMessage());
        }
    }
}
