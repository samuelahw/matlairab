package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Game;
import codes.mlg.my_spring_project.entity.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;
    private Game game;
    private Set<InventoryItem> inventoryItems;
}
