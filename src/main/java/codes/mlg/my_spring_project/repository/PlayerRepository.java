package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
