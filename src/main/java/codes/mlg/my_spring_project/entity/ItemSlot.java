package codes.mlg.my_spring_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "item_slots")
public class ItemSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_name")
    private String name;

    //Slot numbers: 1 = Head, 2 = Body, 3 = Legs, 4 = Weapon, 5 = Accessory
    @Column(name = "slot_number")
    private int slotNumber;


    @ManyToOne
    @JoinColumn(name = "items_lot_id")
    @JsonIgnore
    private GameCharacter gameCharacter;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "item_slot_inventory_item",
            joinColumns =
                    { @JoinColumn(name = "item_slot_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "inventory_item_id", referencedColumnName = "id") })
    private InventoryItem inventoryItem;

}
