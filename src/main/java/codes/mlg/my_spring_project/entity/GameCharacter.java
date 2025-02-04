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
@Table(name = "game_characters")
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weapon_slot_item")
    private int weapon_slot_item;

    @Column(name = "head_slot_item")
    private int head_slot_item;

    @Column(name = "chest_slot_item")
    private int chest_slot_item;

    @Column(name = "legs_slot_item")
    private int leg_slot_item;

    @Column(name = "accessory_item_slot")
    private int accessory_slot_item;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Game game;
}
