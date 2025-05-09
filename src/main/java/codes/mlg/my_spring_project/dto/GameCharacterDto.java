package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Game;
import codes.mlg.my_spring_project.entity.ItemSlot;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterDto {
    private Long id;

    @Size(min = 3, max = 20, message = "Min letters are 3 and max are 20")
    private String name;

    private Set<ItemSlot> itemSlots;

    //3 different character class. 1 = tank, 2 = damager, 3 = healer
    private int characterClass;
    private int totalHealth;
    private int maxPower;
    private int minPower;

    @NotNull
    private Game game;
}
