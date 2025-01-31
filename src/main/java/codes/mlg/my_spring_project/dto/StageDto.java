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
public class StageDto {
    private Long id;
    private String name;
    private String description;
    private int stageNumber;
    private Enemy enemy;
}
