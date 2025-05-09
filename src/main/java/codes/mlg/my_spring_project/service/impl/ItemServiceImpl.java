package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.ItemDto;
import codes.mlg.my_spring_project.entity.Item;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.ItemMapper;
import codes.mlg.my_spring_project.repository.ItemRepository;
import codes.mlg.my_spring_project.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public ItemDto createItem(ItemDto itemDto) {

        Item item = ItemMapper.mapToItem(itemDto);
        Item savedItem = itemRepository.save(item);

        return ItemMapper.mapToItemDto(savedItem);
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item does not exist with this given id: " + itemId));
        return ItemMapper.mapToItemDto(item);
    }

    @Override
    public ItemDto updateItem(Long itemId, ItemDto updatedItem) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item does not exist with this given id: " + itemId));

        item.setItemName(updatedItem.getName());
        item.setItemSlotNumber(updatedItem.getItemSlotNumber());

        item.setDefence(updatedItem.getDefence());
        item.setDescription(updatedItem.getDescription());

        Item updatedItemObj = itemRepository.save(item);
        return ItemMapper.mapToItemDto(updatedItemObj);
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item does not exist with this given id: " + itemId));

    itemRepository.deleteById(itemId);
    }
}
