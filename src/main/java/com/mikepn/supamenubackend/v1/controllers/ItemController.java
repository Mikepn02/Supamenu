package com.mikepn.supamenubackend.v1.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikepn.supamenubackend.v1.dto.request.item.CreateItemDTO;
import com.mikepn.supamenubackend.v1.dto.request.item.UpdateItemDTO;
import com.mikepn.supamenubackend.v1.dto.response.item.ItemResponseDTO;
import com.mikepn.supamenubackend.v1.payload.ApiResponse;
import com.mikepn.supamenubackend.v1.services.ITemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
@Tag(name = "Item")
public class ItemController {

    private final ITemService itemService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Add Item with Image to Menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> addItemWithImage(
            @Parameter(
                    description = "Item data in JSON format",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = """
                            {
                              "name": "Chicken Burger",
                              "description": "Grilled chicken patty with lettuce and tomato",
                              "price": 5.99,
                              "quantityAvailable": 10,
                              "menuId": "a3b4b687-40a0-443c-a5b5-4e73160384b4"
                            }
                            """))
            )
            @RequestPart("item") String itemJson,

            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            CreateItemDTO itemDTO = new ObjectMapper().readValue(itemJson, CreateItemDTO.class);
            ItemResponseDTO response = itemService.createItem(itemDTO, imageFile);
            return ApiResponse.success("Item Created Successfully", HttpStatus.CREATED, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to add item", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }





    @GetMapping("/{id}")
    @Operation(summary = "Get Item By ID")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> getItemById(@PathVariable UUID id){
        try{
            ItemResponseDTO response = itemService.getItemById(id);
            return ApiResponse.success("Item Retrieved Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get Item", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Get All Items")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getAllItems(){
        try{
            List<ItemResponseDTO> response = itemService.getAllItems();
            return ApiResponse.success("Item Retrieved Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get All Items", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/name")
    @Operation(summary = "Get Item By Name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getItemByName(@RequestParam String name){
        try{
            List<ItemResponseDTO> response = itemService.getItemByName(name);
            return ApiResponse.success("Item Retrieved Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get Item", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/menu/{menuId}")
    @Operation(summary = "Get Menu Items")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<ItemResponseDTO>>> getMenuItems(@PathVariable UUID menuId){
        try{
            List<ItemResponseDTO> response = itemService.getItemsByMenu( menuId);
            return ApiResponse.success("Item Retrieved Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get Menu Items", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    @Operation(summary = "Update Item")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> updateItem(@PathVariable UUID id,@Valid @RequestBody UpdateItemDTO dto){
        try {
            ItemResponseDTO response = itemService.updateItem(id, dto);
            return ApiResponse.success("Item Updated Successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update Item", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping
    @Operation(summary = "Delete Item")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ItemResponseDTO>> deleteItem(@PathVariable UUID id){
        try{
            itemService.deleteItem(id);
            return ApiResponse.success("Item Deleted Successfully", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to delete Item", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
