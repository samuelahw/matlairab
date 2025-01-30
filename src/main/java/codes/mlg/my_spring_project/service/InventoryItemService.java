package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.Inventory;

public interface InventoryItemService {
    InventoryItemDto createInventoryItem(InventoryItemDto inventoryItemDto, Inventory inventory);

    InventoryItemDto getInventoryItemById(Long inventoryItemId);

    InventoryItemDto updateInventoryItemCount(Long inventoryItemId, int count);

    void deleteInventoryItem(Long inventoryItemId);
}
