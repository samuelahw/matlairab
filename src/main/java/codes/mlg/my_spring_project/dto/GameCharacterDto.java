package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Game;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterDto {
    private Long id;

    @Size(min = 3, max = 20, message = "Min letters are 3 and max are 20")
    private String name;

    private int weaponSlotItem;
    private int headSlotItem;
    private int chestSlotItem;
    private int legSlotItem;
    private int accessorySlotItem;

    @NotNull
    private Game game;
}
