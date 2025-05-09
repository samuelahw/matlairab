package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.GameCharacter;
import codes.mlg.my_spring_project.entity.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSlotDto {

    private Long id;

    private String name;

    private int slotNumber;

    private GameCharacter gameCharacter;

    private InventoryItem inventoryItem;
}
