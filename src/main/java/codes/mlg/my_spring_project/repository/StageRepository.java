package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
