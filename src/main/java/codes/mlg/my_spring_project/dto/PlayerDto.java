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
public class PlayerDto {
    private Long id;
    private String name;
    private Game game;

}
