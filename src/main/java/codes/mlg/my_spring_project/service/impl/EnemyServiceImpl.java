package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.entity.Enemy;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.EnemyMapper;
import codes.mlg.my_spring_project.repository.EnemyRepository;
import codes.mlg.my_spring_project.service.EnemyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnemyServiceImpl implements EnemyService {

    private EnemyRepository enemyRepository;

    @Override
    public EnemyDto createEnemy(EnemyDto enemyDto) {

        Enemy enemy = EnemyMapper.mapToEnemy(enemyDto);
        Enemy savedEnemy = enemyRepository.save(enemy);

        return EnemyMapper.mapToEnemyDto(savedEnemy);
    }

    @Override
    public EnemyDto getEnemyById(Long enemyId) {

        Enemy enemy = enemyRepository.findById(enemyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enemy does not exist with this given id: " + enemyId));

        return EnemyMapper.mapToEnemyDto(enemy);
    }

    @Override
    public EnemyDto updateEnemy(Long enemyId, EnemyDto updatedEnemy) {
        Enemy enemy = enemyRepository.findById(enemyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enemy does not exist with this given id: " + enemyId));

        enemy.setHealth(updatedEnemy.getHealth());
        enemy.setDamage(updatedEnemy.getDamage());
        enemy.setDefence(updatedEnemy.getDefence());
        enemy.setMinCoinDrop(updatedEnemy.getMinCoinDrop());
        enemy.setMaxCoinDrop(updatedEnemy.getMaxCoinDrop());

        Enemy updatedEnemyObj = enemyRepository.save(enemy);

        return EnemyMapper.mapToEnemyDto(updatedEnemyObj);
    }

    @Override
    public void deleteEnemy(Long enemyId) {
        Enemy enemy = enemyRepository.findById(enemyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enemy does not exist with this given id: " + enemyId));
        enemyRepository.deleteById(enemyId);
    }
}
