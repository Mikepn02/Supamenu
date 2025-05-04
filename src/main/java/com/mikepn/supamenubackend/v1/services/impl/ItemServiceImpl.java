package com.mikepn.supamenubackend.v1.services.impl;


import com.mikepn.supamenubackend.v1.dto.request.item.CreateItemDTO;
import com.mikepn.supamenubackend.v1.dto.request.item.UpdateItemDTO;
import com.mikepn.supamenubackend.v1.dto.response.item.ItemResponseDTO;
import com.mikepn.supamenubackend.v1.exceptions.AppException;
import com.mikepn.supamenubackend.v1.exceptions.NotFoundException;
import com.mikepn.supamenubackend.v1.models.Item;
import com.mikepn.supamenubackend.v1.models.Menu;
import com.mikepn.supamenubackend.v1.repositories.IMenuRepository;
import com.mikepn.supamenubackend.v1.repositories.ITemRepository;
import com.mikepn.supamenubackend.v1.services.ITemService;
import com.mikepn.supamenubackend.v1.services.IUserService;
import com.mikepn.supamenubackend.v1.standalone.FileStorageService;
import com.mikepn.supamenubackend.v1.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ITemService {

    private final ITemRepository itemRepository;
    private final IMenuRepository menuRepository;
    private final FileStorageService fileStorageService;
    private final IUserService userService;

    @Transactional
    @Override
    public ItemResponseDTO createItem(CreateItemDTO dto, MultipartFile imageFile) {
        System.out.println("Here is the dto: "+ dto.getMenuId());
        try {

            if (dto.getMenuId() == null) {
                throw new AppException("Menu ID must not be null");
            }


            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new NotFoundException("Menu not found with ID: " + dto.getMenuId()));

            UUID userId = userService.getLoggedInUser().getId();

            String imagePath = fileStorageService.saveFile(imageFile, userId);

            Item item = Item.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .quantityAvailable(dto.getQuantityAvailable())
                    .image(imagePath)
                    .menu(menu)
                    .build();

            item = itemRepository.save(item);
            return Mapper.getMapper().map(item, ItemResponseDTO.class);
        } catch (Exception e) {
            throw new AppException("Error creating item: " + e.getMessage());
        }
    }

    @Override
    public ItemResponseDTO getItemById(UUID itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new AppException("Item not found with ID: " + itemId));
        return Mapper.getMapper().map(item , ItemResponseDTO.class);
    }

    @Override
    public List<ItemResponseDTO> getItemByName(String name) {
        List<Item>  items = itemRepository.findAllByName(name);
        return items.stream()
                .map(item -> Mapper.getMapper().map(item, ItemResponseDTO.class))
                .toList();
    }

    @Override
    public ItemResponseDTO updateItem(UUID id, UpdateItemDTO dto) {
        try{
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new AppException("Item not found with ID: " + id));


            if(dto.getName() != null){
                item.setName(dto.getName());
            }
            if(dto.getDescription() != null){
                item.setDescription(dto.getDescription());
            }
            if(dto.getPrice() != 0){
                item.setPrice(dto.getPrice());
            }

            if(item.getQuantityAvailable() != 0){
                item.setQuantityAvailable(dto.getQuantityAvailable());
            }

            item = itemRepository.save(item);

            return Mapper.getMapper().map(item , ItemResponseDTO.class);


        }catch (Exception e) {
            throw new AppException("Error updating item: " + e.getMessage());
        }
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> Mapper.getMapper().map(item , ItemResponseDTO.class))
                .toList();
    }

    @Override
    public List<ItemResponseDTO> getItemsByMenu(UUID menuId) {
        return itemRepository.findAllByMenu_Id(menuId)
                .stream()
                .map(item -> Mapper.getMapper().map(item, ItemResponseDTO.class))
                .toList();
    }

    @Override
    public void deleteItem(UUID itemId) {
        try {
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new AppException("Item not found with ID: " + itemId));
            itemRepository.delete(item);
        }catch (Exception e) {
            throw new AppException("Error deleting item: " + e.getMessage());
        }
    }
}
