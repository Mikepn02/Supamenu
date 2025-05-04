package com.mikepn.supamenubackend.v1.controllers;

import com.mikepn.supamenubackend.v1.dto.request.menu.CreateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.request.menu.UpdateMenuDTO;
import com.mikepn.supamenubackend.v1.dto.response.menu.MenuResponseDTO;
import com.mikepn.supamenubackend.v1.payload.ApiResponse;
import com.mikepn.supamenubackend.v1.services.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
@Tag(name = "Menu")
public class MenuController {

    private final IMenuService menuService;



    @PostMapping
    @Operation( summary = "Create a Restaurant Menu")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> createMenu(@Valid @RequestBody CreateMenuDTO dto){
        try{
            MenuResponseDTO response = menuService.createMenu(dto);
            return ApiResponse.success("Successfully created a menu", HttpStatus.CREATED, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to create a restaurant menu", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a Menu By ID")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> getMenuById(@PathVariable UUID id){
        try {
            MenuResponseDTO response = menuService.getMenuById(id);
            return ApiResponse.success("Successfully retrieved a menu by id", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get a menu by id", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping
    @Operation(summary = "Get All Menus")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getAllMenus(){
        try{
            List<MenuResponseDTO> response = menuService.getAllMenus();
            return ApiResponse.success("Successfully retrieved all menus", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to retrieve all menus", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{restaurantId}")
    @Operation(summary = "Get All Menus By a Restaurant ID")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getAllMenusByRestaurantId(@PathVariable UUID restaurantId){
        try{
            List<MenuResponseDTO> response = menuService.getMenusByRestaurantId(restaurantId);
            return ApiResponse.success("Successfully retrieved all menus by restaurant id", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to retrieve all menus by restaurant id", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update Menu")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> updateMenu(@PathVariable UUID id, @RequestBody UpdateMenuDTO dto){
        try{
            MenuResponseDTO response = menuService.updateMenu(id, dto);
            return ApiResponse.success("Successfully updated a menu", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update menu", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Menu")
    public ResponseEntity<ApiResponse<Void>> deleteMenu(@PathVariable UUID id){
        try{
            menuService.deleteMenu(id);
            return ApiResponse.success("Successfully deleted a menu", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to delete menu", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
