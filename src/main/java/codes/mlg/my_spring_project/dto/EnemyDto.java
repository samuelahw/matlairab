package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.ItemDrop;
import codes.mlg.my_spring_project.entity.Stage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnemyDto {
    private Long id;
    private int health;
    private int minDamage;
    private int maxDamage;
    private int defence;
    private int minCoinDrop;
    @Min(value = 1)
    private int maxCoinDrop;
    private Set<ItemDrop> itemDrops;
    @Min(value = 1)
    @NotNull
    private Stage stage;
}
