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

    @Column(name = "itemSlot")
    private String itemSlot;

    @Column(name = "power")
    private int itemPower;

    @Column(name = "defence")
    private int defence;

    @Column(name = "description")
    private String description;
}
