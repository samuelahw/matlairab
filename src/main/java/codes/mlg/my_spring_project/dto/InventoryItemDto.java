package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Inventory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @Min(value = 1)
    private Long itemId;

    @Min(value = 0)
    private int count;

    @NotNull
    private Inventory inventory;

    @NotNull
    private Boolean equipped;
}
