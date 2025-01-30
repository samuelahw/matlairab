package codes.mlg.my_spring_project.repository;

import codes.mlg.my_spring_project.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
