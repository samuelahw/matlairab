package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.ItemDto;
import codes.mlg.my_spring_project.entity.Item;

public class ItemMapper {

    public static ItemDto mapToItemDto(Item item){
        return new ItemDto(
                item.getId(),
                item.getItemName(),
                item.getItemSlotNumber(),
                item.getMaxItemPower(),
                item.getMinItemPower(),
                item.getDefence(),
                item.getViableCharacterClass(),
                item.getDescription()
        );
    }

    public static Item mapToItem(ItemDto itemDto){
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getItemSlotNumber(),
                itemDto.getMaxItemPower(),
                itemDto.getMinItemPower(),
                itemDto.getDefence(),
                itemDto.getViableCharacterClass(),
                itemDto.getDescription()
        ) ;
    }
}
