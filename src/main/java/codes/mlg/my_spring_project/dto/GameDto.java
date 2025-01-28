package codes.mlg.my_spring_project.dto;

import codes.mlg.my_spring_project.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private int stage;
    private int balance;
    private Player player;
}
