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
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stage")
    private int stage;

    @Column(name = "balance")
    private int balance;

    @JsonIgnore
    @OneToOne(mappedBy = "game")
    private Player player;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "game_inventory",
            joinColumns =
                    { @JoinColumn(name = "game_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "inventory_id", referencedColumnName = "id") })
    private Inventory inventory;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GameCharacter> characters;
}
