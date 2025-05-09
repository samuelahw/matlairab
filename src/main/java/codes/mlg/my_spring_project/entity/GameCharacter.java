package codes.mlg.my_spring_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @Column(name = "character_class")
    private int characterClass;

    @Column(name =" total_health")
    private int totalHealth;

    @Column(name =" max_power")
    private int maxPower;

    @Column(name =" min_power")
    private int minPower;

    @JsonIgnore
    @OneToMany(mappedBy = "gameCharacter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemSlot> itemSlots;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Game game;
}
