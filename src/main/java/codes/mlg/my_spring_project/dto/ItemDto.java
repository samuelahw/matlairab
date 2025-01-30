package codes.mlg.my_spring_project.dto;

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
    private String name;
    private String itemSlot;
    private int itemPower;
    private int defence;
    private String description;

}
