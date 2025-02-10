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
@Table(name = "Enemies")
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="health")
    private int health;

    @Column(name="damage")
    private int damage;

    @Column(name="defence")
    private int defence;

    @Column(name="min_coin_drop")
    private int minCoinDrop;

    @Column(name="max_coin_drop")
    private int maxCoinDrop;

    @JsonIgnore
    @OneToMany(mappedBy = "enemy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemDrop> itemDrops;

    @JsonIgnore
    @OneToOne(mappedBy = "enemy")
    private Stage stage;
}
