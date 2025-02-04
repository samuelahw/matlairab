package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Game;
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
    private String name;
    private int weaponSlotItem;
    private int headSlotItem;
    private int chestSlotItem;
    private int legSlotItem;
    private int accessorySlotItem;
    private Game game;
}
