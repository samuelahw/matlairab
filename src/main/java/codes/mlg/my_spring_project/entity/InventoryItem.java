package codes.mlg.my_spring_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name="inventory_id", nullable=false)
    @JsonIgnore
    private Inventory inventory;

    @Column(name = "equipped")
    private Boolean equipped;

    public InventoryItem() {}
}
