package com.mikepn.supamenubackend.v1.controllers;

import com.mikepn.supamenubackend.v1.dto.request.restaurant.RegisterRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.request.restaurant.UpdateRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.response.restaurant.RestaurantResponseDTO;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import com.mikepn.supamenubackend.v1.payload.ApiResponse;
import com.mikepn.supamenubackend.v1.services.IRestaurantService;
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
@RequestMapping("restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant")
public class RestaurantController {

    private final IRestaurantService restaurantService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RestaurantResponseDTO>> registerRestaurant(@Valid @RequestBody RegisterRestaurantDTO dto){
        try{
            RestaurantResponseDTO response = restaurantService.registerRestaurant(dto);
            return ApiResponse.success("Restaurant registered successfully", HttpStatus.CREATED, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to register restaurant", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RestaurantResponseDTO>> findRestaurantById(@PathVariable UUID id){
        try{
            RestaurantResponseDTO response = restaurantService.getRestaurantById(id);
            return ApiResponse.success("Restaurant found successfully", HttpStatus.OK, response);
        }catch (Exception e) {
            return ApiResponse.fail("Failed to find restaurant", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RestaurantResponseDTO>> findRestaurantByName(@RequestParam String name){
        try{
            RestaurantResponseDTO response = restaurantService.getRestaurantByName(name);
            return ApiResponse.success("Restaurant found successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to find restaurant", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<RestaurantResponseDTO>>> findAllRestaurants(){
        try{
            List<RestaurantResponseDTO> response = restaurantService.getAllRestaurants();
            return ApiResponse.success("Restaurants found successfully", HttpStatus.OK, response);
        }catch (Exception e) {
            return ApiResponse.fail("Failed to find restaurants", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{type}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<RestaurantResponseDTO>>> findRestaurantsByType(@PathVariable ERestaurantType type){
        try{
            List <RestaurantResponseDTO> response = restaurantService.getRestaurantByType(type);
            return ApiResponse.success("Restaurants found successfully", HttpStatus.OK, response);

        } catch (Exception e) {
            return ApiResponse.fail("Failed to find restaurants", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/orders/{restaurantId}")
    public ResponseEntity<ApiResponse<List<?>>> findRestaurantsByType(@PathVariable UUID restaurantId){
        try{
            List <?> response = restaurantService.getAllOrdersForRestaurant(restaurantId);
            return ApiResponse.success("Restaurants found successfully", HttpStatus.OK, response);

        } catch (Exception e) {
            return ApiResponse.fail("Failed to find restaurants", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ApiResponse<RestaurantResponseDTO>> updateRestaurant(@PathVariable UUID id, @Valid @RequestBody UpdateRestaurantDTO dto){
        try{
            RestaurantResponseDTO response = restaurantService.updateRestaurant(id , dto);
            return ApiResponse.success("Restaurant updated successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update restaurant", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteRestaurant(@PathVariable UUID id){
        try{
            restaurantService.deleteRestaurant(id);
            return ApiResponse.success("Restaurant deleted successfully", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to delete restaurant", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
