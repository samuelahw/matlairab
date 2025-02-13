package codes.mlg.my_spring_project.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    @Size(min = 3, max = 20, message = "Min letters are 3 and max are 20")
    private String name;

    private int itemSlot;
    private int itemPower;
    private int defence;

    @Size(min = 3, max = 100, message = "Min letters are 3 and max are 100")
    private String description;

}
