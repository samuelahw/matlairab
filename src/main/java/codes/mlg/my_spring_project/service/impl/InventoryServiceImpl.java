package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.entity.Inventory;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.repository.InventoryRepository;
import codes.mlg.my_spring_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

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

}
