package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.entity.Inventory;

public class InventoryMapper {

    public static InventoryDto mapToInventoryDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getGame(),
                inventory.getInventoryItems()
        );
    }

    public static Inventory mapToInventory(InventoryDto inventoryDto){
        return new Inventory(
                inventoryDto.getId(),
                inventoryDto.getGame(),
                inventoryDto.getInventoryItems()
        );
    }
}
