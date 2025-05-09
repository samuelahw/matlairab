package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.dto.InventoryItemDto;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto getInventoryById(Long inventoryId);

    void deleteInventory(Long inventoryId);

    InventoryItemDto searchItemFromInventoryByItemId(Long playerId, Long ItemId);

    InventoryDto getInventoryByPlayerId(Long playerId);
}
