package codes.mlg.my_spring_project.dto;


import codes.mlg.my_spring_project.entity.Enemy;
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
    private Long itemId;
    private double dropChance;
    private Enemy enemy;
}
