package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.EnemyDto;

public interface EnemyService {
    EnemyDto createEnemy(EnemyDto enemyDto);

    EnemyDto getEnemyById(Long enemyId);

    EnemyDto updateEnemy(Long enemyId, EnemyDto updatedEnemy);

    void deleteEnemy(Long enemyId);
}
