package codes.mlg.my_spring_project.dto;


import codes.mlg.my_spring_project.entity.Enemy;
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
public class ItemDropDto {
    private Long id;

    @Min(value = 0)
    private Long itemId;

    @Min(value = 0)
    private double dropChance;

    private Enemy enemy;
}
