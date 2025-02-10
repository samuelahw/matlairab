package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.ItemDropDto;
import codes.mlg.my_spring_project.entity.ItemDrop;

public class ItemDropMapper {

    public static ItemDropDto mapToItemDropDto(ItemDrop itemDrop){
        return new ItemDropDto(
                itemDrop.getId(),
                itemDrop.getItemId(),
                itemDrop.getDropChance(),
                itemDrop.getEnemy()
        );
    }

    public static ItemDrop mapToItemDrop(ItemDropDto itemDropDto){
        return new ItemDrop(
                itemDropDto.getId(),
                itemDropDto.getItemId(),
                itemDropDto.getDropChance(),
                itemDropDto.getEnemy()
        );
    }
}
