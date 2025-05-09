package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.Inventory;
import codes.mlg.my_spring_project.entity.InventoryItem;
import codes.mlg.my_spring_project.entity.Player;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.repository.InventoryRepository;
import codes.mlg.my_spring_project.repository.PlayerRepository;
import codes.mlg.my_spring_project.service.InventoryService;
import codes.mlg.my_spring_project.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {

        Inventory inventory = InventoryMapper.mapToInventory(inventoryDto);
        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.mapToInventoryDto(savedInventory);
    }

    @Override
    public InventoryDto getInventoryById(Long inventoryId) {

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory does not exist with this given id: " + inventoryId));

        return InventoryMapper.mapToInventoryDto(inventory);
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory does not exist with this given id: " + inventoryId));

        inventoryRepository.deleteById(inventoryId);

    }

    @Override
    public InventoryItemDto searchItemFromInventoryByItemId(Long playerId, Long itemId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player does not exist with this given id:" + playerId));

        Set<InventoryItem> inventoryItems = player.getGame().getInventory().getInventoryItems();

        for (InventoryItem item : inventoryItems){
            if(item.getItemId().equals(itemId))
                return InventoryItemMapper.mapToInventoryItemDto(item);
        }

        return null;
    }

    @Override
    public InventoryDto getInventoryByPlayerId(Long playerId) {

        return InventoryMapper.mapToInventoryDto(playerService.getPlayerById(playerId).getGame().getInventory());
    }

}
