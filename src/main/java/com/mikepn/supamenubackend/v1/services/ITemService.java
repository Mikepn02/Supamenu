package com.mikepn.supamenubackend.v1.services;

import com.mikepn.supamenubackend.v1.dto.request.item.CreateItemDTO;
import com.mikepn.supamenubackend.v1.dto.request.item.UpdateItemDTO;
import com.mikepn.supamenubackend.v1.dto.response.item.ItemResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ITemService {

    ItemResponseDTO createItem(CreateItemDTO dto, MultipartFile imageFile);
    ItemResponseDTO getItemById(UUID itemId);
    List<ItemResponseDTO> getItemByName(String name);
    ItemResponseDTO updateItem(UUID id , UpdateItemDTO dto);
    List<ItemResponseDTO> getAllItems();
    List<ItemResponseDTO> getItemsByMenu(UUID menuId);
    void deleteItem(UUID itemId);

}
