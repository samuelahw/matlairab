package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.entity.Enemy;

public class EnemyMapper {

    public static EnemyDto mapToEnemyDto(Enemy enemy){
        return new EnemyDto(
                enemy.getId(),
                enemy.getHealth(),
                enemy.getDamage(),
                enemy.getDefence(),
                enemy.getMinCoinDrop(),
                enemy.getMaxCoinDrop(),
                enemy.getItemDrops(),
                enemy.getStage()
        );
    }

    public static Enemy mapToEnemy(EnemyDto enemyDto){
        return new Enemy(
                enemyDto.getId(),
                enemyDto.getHealth(),
                enemyDto.getDamage(),
                enemyDto.getDefence(),
                enemyDto.getMinCoinDrop(),
                enemyDto.getMaxCoinDrop(),
                enemyDto.getItemDrops(),
                enemyDto.getStage()
        );
    }
}
