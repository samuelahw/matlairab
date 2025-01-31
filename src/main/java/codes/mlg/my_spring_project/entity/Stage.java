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
@Table(name = "stages")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stage_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stage_number")
    private int stageNumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "stage_enemy",
            joinColumns =
                    {@JoinColumn(name = "stage_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "enemy_id", referencedColumnName = "id")})
    private Enemy enemy;
}
