package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.Inventory;
import codes.mlg.my_spring_project.entity.InventoryItem;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.repository.InventoryItemRepository;
import codes.mlg.my_spring_project.service.InventoryItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {

    private InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItemDto createInventoryItem(InventoryItemDto inventoryItemDto, Inventory inventory) {
        inventoryItemDto.setInventory(inventory);
        InventoryItem inventoryItem = InventoryItemMapper.mapToInventoryItem(inventoryItemDto);
        InventoryItem savedInventoryItem = inventoryItemRepository.save(inventoryItem);

        return InventoryItemMapper.mapToInventoryItemDto(savedInventoryItem);
    }

    @Override
    public InventoryItemDto getInventoryItemById(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("InventoryItem does not exist with this given id: " + inventoryItemId));

        return InventoryItemMapper.mapToInventoryItemDto(inventoryItem);
    }

    @Override
    public InventoryItemDto updateInventoryItemCount(Long inventoryItemId, int count) {

        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("InventoryItem does not exist with this given id: " + inventoryItemId));

        inventoryItem.setCount(count);

        InventoryItem updatedInventoryItem = inventoryItemRepository.save(inventoryItem);
        return InventoryItemMapper.mapToInventoryItemDto(updatedInventoryItem);
    }

    @Override
    public void deleteInventoryItem(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("InventoryItem does not exist with this given id: " + inventoryItemId));

        inventoryItemRepository.deleteById(inventoryItemId);
    }
}
