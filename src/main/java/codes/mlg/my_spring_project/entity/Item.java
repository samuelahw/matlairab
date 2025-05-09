package codes.mlg.my_spring_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String itemName;

    @Column(name = "item_slot_number")
    private int itemSlotNumber;

    @Column(name = "min_item_power")
    private int minItemPower;

    @Column(name = "max_item_power")
    private int maxItemPower;

    @Column(name = "defence")
    private int defence;

    @Column(name = "viable_character_class")
    private int viableCharacterClass;

    @Column(name = "description")
    private String description;
}
