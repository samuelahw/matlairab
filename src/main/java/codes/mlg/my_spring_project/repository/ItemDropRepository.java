package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.ItemDrop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDropRepository extends JpaRepository<ItemDrop, Long> {
}
