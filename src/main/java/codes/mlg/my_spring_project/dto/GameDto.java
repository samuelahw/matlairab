package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.GameCharacter;
import codes.mlg.my_spring_project.entity.Inventory;
import codes.mlg.my_spring_project.entity.Player;
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
public class GameDto {
    private Long id;

    @Min(value = 1)
    private int stage;

    @Min(value = 1)
    private int balance;

    @NotNull
    private Player player;
    private Inventory inventory;
    private Set<GameCharacter> characters;
}
