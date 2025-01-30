package codes.mlg.my_spring_project.controller;

import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private InventoryService inventoryService;

    // Post new inventory
    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
        InventoryDto savedInventory = inventoryService.createInventory(inventoryDto);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

    // Get inventory by id
    @GetMapping("{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable("id") Long inventoryId) {
        InventoryDto inventoryDto = inventoryService.getInventoryById(inventoryId);
        return ResponseEntity.ok(inventoryDto);
    }

    // Delete inventory
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.ok("Inventory successfully removed");
    }
}
