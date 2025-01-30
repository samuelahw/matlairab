package codes.mlg.my_spring_project.controller;


import codes.mlg.my_spring_project.dto.InventoryItemDto;
import codes.mlg.my_spring_project.entity.InventoryItem;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.service.InventoryItemService;
import codes.mlg.my_spring_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/inventoryitems")
public class InventoryItemController {

    private InventoryService inventoryService;

    private InventoryItemService inventoryItemService;

    // Post new inventory item
    @PostMapping("{id}")
    public ResponseEntity<InventoryItem> createInventoryItem(@PathVariable("id") Long inventoryId,
                                                       @RequestBody InventoryItem inventoryItemRequest){

        InventoryItemDto inventoryItemDto = inventoryItemService.createInventoryItem(InventoryItemMapper.mapToInventoryItemDto(inventoryItemRequest), InventoryMapper.mapToInventory(inventoryService.getInventoryById(inventoryId)));
        InventoryItem inventoryItem = InventoryItemMapper.mapToInventoryItem(inventoryItemDto);

        return new ResponseEntity<>(inventoryItem, HttpStatus.CREATED);
    }

    // Get inventory item by id
    @GetMapping("{id}")
    public ResponseEntity<InventoryItemDto> getInventoryItemById(@PathVariable("id") Long inventoryItemId) {
        InventoryItemDto inventoryItemDto = inventoryItemService.getInventoryItemById(inventoryItemId);
        return ResponseEntity.ok(inventoryItemDto);
    }

    // Delete inventory item
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInventoryItem(@PathVariable("id") Long inventoryItemId) {
        inventoryItemService.deleteInventoryItem(inventoryItemId);
        return ResponseEntity.ok("InventoryItem successfully removed");
    }
}
