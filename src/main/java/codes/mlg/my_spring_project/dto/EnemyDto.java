package codes.mlg.my_spring_project.dto;


import codes.mlg.my_spring_project.entity.ItemDrop;
import codes.mlg.my_spring_project.entity.Stage;
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
    private int damage;
    private int defence;
    private int minCoinDrop;
    private int maxCoinDrop;
    private Set<ItemDrop> itemDrops;
    private Stage stage;
}
