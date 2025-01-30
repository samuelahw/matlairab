package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.InventoryDto;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto getInventoryById(Long inventoryId);

    void deleteInventory(Long inventoryId);



}
