package codes.mlg.my_spring_project.dto;


import codes.mlg.my_spring_project.entity.Enemy;
import jakarta.validation.constraints.Min;
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
public class StageDto {
    private Long id;

    @Size(min = 3, max = 20, message = "Min letters are 3 and max are 20")
    private String name;

    @Size(min = 3, max = 80, message = "Min letters are 3 and max are 80")
    private String description;

    @Min(value = 1)
    private int stageNumber;

    @NotNull
    private Enemy enemy;
}
