package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
