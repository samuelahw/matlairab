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
@Table(name = "item_drops")
public class ItemDrop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_id")
    private Long itemId;

    @Column(name="drop_chance")
    private double dropChance;

    @ManyToOne
    @JoinColumn(name="item_drop_id", nullable=false)
    @JsonIgnore
    private Enemy enemy;

    public ItemDrop() {}
}
