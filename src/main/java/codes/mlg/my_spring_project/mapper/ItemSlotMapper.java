package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.ItemSlotDto;
import codes.mlg.my_spring_project.entity.ItemSlot;

public class ItemSlotMapper {

    public static ItemSlotDto mapToItemSlotDto(ItemSlot itemSlot) {
        return new ItemSlotDto(
                itemSlot.getId(),
                itemSlot.getName(),
                itemSlot.getSlotNumber(),
                itemSlot.getGameCharacter(),
                itemSlot.getInventoryItem()
        );
    }

    public static ItemSlot mapToItemSlot(ItemSlotDto itemSlotDto) {
        return new ItemSlot(
                itemSlotDto.getId(),
                itemSlotDto.getName(),
                itemSlotDto.getSlotNumber(),
                itemSlotDto.getGameCharacter(),
                itemSlotDto.getInventoryItem()
        );

    }
}
