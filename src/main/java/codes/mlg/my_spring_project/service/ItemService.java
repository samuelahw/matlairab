package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.ItemDto;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);

    ItemDto getItemById(Long itemId);

    ItemDto updateItem(Long itemId, ItemDto updatedItem);

    void deleteItem(Long itemId);
}
