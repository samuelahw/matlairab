package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDto {
    private Long id;
    private Long itemId;
    private int count;
    private Inventory inventory;
    private Boolean equipped;
}
