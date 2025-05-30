package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.Inventory;
import codes.mlg.my_spring_project.entity.InventoryItem;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.repository.InventoryItemRepository;
import codes.mlg.my_spring_project.service.InventoryItemService;
import codes.mlg.my_spring_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {

    private InventoryItemRepository inventoryItemRepository;
    private InventoryService inventoryService;

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
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InventoryItem does not exist with this given id: " + inventoryItemId));

        return InventoryItemMapper.mapToInventoryItemDto(inventoryItem);
    }

    @Override
    public InventoryItemDto updateInventoryItemCount(Long inventoryItemId, int count) {

        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InventoryItem does not exist with this given id: " + inventoryItemId));

        inventoryItem.setCount(count);

        InventoryItem updatedInventoryItem = inventoryItemRepository.save(inventoryItem);
        return InventoryItemMapper.mapToInventoryItemDto(updatedInventoryItem);
    }

    @Override
    public void deleteInventoryItem(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InventoryItem does not exist with this given id: " + inventoryItemId));

        inventoryItemRepository.deleteById(inventoryItemId);
    }

    @Override
    public InventoryItemDto findInventoryItemByItemIdFromInventory(Long inventoryId, Long itemId) {

        Inventory pInventory = InventoryMapper.mapToInventory(inventoryService.getInventoryById(inventoryId));

        Set<InventoryItem> inventoryItems = pInventory.getInventoryItems();

        for (InventoryItem item : inventoryItems) {
            if (Objects.equals(item.getItemId(), itemId)) {
                return InventoryItemMapper.mapToInventoryItemDto(item);
            }
        }

        return null;
    }

    @Override
    public Boolean itemExistsInPlayerInventory(Long playerId, Long ItemId) {

        Inventory inventory = InventoryMapper.mapToInventory(inventoryService.getInventoryByPlayerId(playerId));

        Set<InventoryItem> inventoryItems = inventory.getInventoryItems();

        for (InventoryItem item : inventoryItems) {
            if (item.getItemId().equals(ItemId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean addInventoryItemToInventory(Long playerId, Long itemId) {

        inventoryService.searchItemFromInventoryByItemId(playerId, itemId);

        // add item to inventory if item is not already in inventory
        if (!itemExistsInPlayerInventory(playerId, itemId)) {
            InventoryItemDto inventoryItemDto = new InventoryItemDto();
            inventoryItemDto.setCount(1);
            inventoryItemDto.setItemId(itemId);
            createInventoryItem(inventoryItemDto,
                    InventoryMapper.mapToInventory(inventoryService.getInventoryByPlayerId(playerId)));
            return true;
        } else { // increment item count by 1 if item is already in inventory
            updateInventoryItemCount(inventoryService.searchItemFromInventoryByItemId(playerId, itemId).getId(),
                    inventoryService.searchItemFromInventoryByItemId(playerId, itemId).getCount() + 1);
            return true;
        }
    }
}
