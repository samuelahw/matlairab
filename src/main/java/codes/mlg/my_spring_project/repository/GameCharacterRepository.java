package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {
}
