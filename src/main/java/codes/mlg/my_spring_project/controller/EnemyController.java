package codes.mlg.my_spring_project.controller;

import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.service.EnemyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/enemies")
public class EnemyController {

    private EnemyService enemyService;

    // Post new enemy
    @PostMapping
    public ResponseEntity<EnemyDto> createEnemy(@RequestBody EnemyDto enemyDto) {

        EnemyDto savedEnemy = enemyService.createEnemy(enemyDto);

        return new ResponseEntity<>(savedEnemy, HttpStatus.CREATED);
    }

    // Get enemy by id
    @GetMapping("{id}")
    public ResponseEntity<EnemyDto> getEnemyById(@PathVariable("id") Long enemyId) {
        EnemyDto enemyDto = enemyService.getEnemyById(enemyId);

        return ResponseEntity.ok(enemyDto);
    }

    // Update enemy
    @PutMapping("{id}")
    public ResponseEntity<EnemyDto> updateEnemy(@PathVariable("id") Long enemyId, @RequestBody EnemyDto enemyDto) {
        EnemyDto updatedEnemyDto = enemyService.updateEnemy(enemyId, enemyDto);

        return ResponseEntity.ok(updatedEnemyDto);
    }

    // Delete enemy
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEnemy(@PathVariable("id") Long enemyId){
        enemyService.deleteEnemy(enemyId);
        return ResponseEntity.ok("Enemy successfully removed");
    }

}
