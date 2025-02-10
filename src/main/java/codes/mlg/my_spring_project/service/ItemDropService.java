package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.ItemDropDto;
import codes.mlg.my_spring_project.entity.Enemy;

public interface ItemDropService {
    ItemDropDto createItemDrop(ItemDropDto itemDropDto, Enemy enemy);

    ItemDropDto getItemDropById(Long itemDropId);

    ItemDropDto updateItemDrop(Long itemDropId, ItemDropDto updatedItemDropDto);

    void deleteItemDrop(Long itemDropId);

}
