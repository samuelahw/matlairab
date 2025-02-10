package codes.mlg.my_spring_project.controller;


import codes.mlg.my_spring_project.dto.ItemDropDto;
import codes.mlg.my_spring_project.entity.ItemDrop;
import codes.mlg.my_spring_project.mapper.EnemyMapper;
import codes.mlg.my_spring_project.mapper.ItemDropMapper;
import codes.mlg.my_spring_project.service.EnemyService;
import codes.mlg.my_spring_project.service.ItemDropService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/itemdrops")
public class ItemDropController {

    private EnemyService enemyService;

    private ItemDropService itemDropService;

    // Post new item drop
    @PostMapping("{id}")
    public ResponseEntity<ItemDrop> createItemDrop(@PathVariable("id") Long enemyId,
                                                   @RequestBody ItemDrop itemDropRequest) {

        ItemDropDto itemDropDto = itemDropService.createItemDrop(ItemDropMapper.mapToItemDropDto(itemDropRequest),
                EnemyMapper.mapToEnemy(enemyService.getEnemyById(enemyId)));

        ItemDrop itemDrop = ItemDropMapper.mapToItemDrop(itemDropDto);

        return new ResponseEntity<>(itemDrop, HttpStatus.CREATED);
    }

    // Get item drop by id
    @GetMapping("{id}")
    public ResponseEntity<ItemDropDto> getItemDropById(@PathVariable("id") Long itemDropId) {
        ItemDropDto itemDropDto = itemDropService.getItemDropById(itemDropId);
        return ResponseEntity.ok(itemDropDto);
    }

    // Update drop item
    @PutMapping("id")
    public ResponseEntity<ItemDropDto> updateItemDrop(@PathVariable("id") Long itemDropId,
                                                      @RequestBody ItemDropDto updatedItemDrop) {
        ItemDropDto itemDropDto = itemDropService.updateItemDrop(itemDropId, updatedItemDrop);
        return ResponseEntity.ok(itemDropDto);
    }

    // Delete drop item
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItemDrop(@PathVariable("id") Long itemDropId) {
        itemDropService.deleteItemDrop(itemDropId);
        return ResponseEntity.ok("ItemDrop successfully removed");
    }
}
