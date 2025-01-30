package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.InventoryItem;

public class InventoryItemMapper {

    public static InventoryItemDto mapToInventoryItemDto(InventoryItem inventoryItem) {

        return new InventoryItemDto(
                inventoryItem.getId(),
                inventoryItem.getItemId(),
                inventoryItem.getCount(),
                inventoryItem.getInventory()
        );
    }

    public static InventoryItem mapToInventoryItem(InventoryItemDto inventoryItemDto) {
        return new InventoryItem(
                inventoryItemDto.getId(),
                inventoryItemDto.getItemId(),
                inventoryItemDto.getCount(),
                inventoryItemDto.getInventory()
        );
    }
}
