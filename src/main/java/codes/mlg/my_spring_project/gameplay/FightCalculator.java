package codes.mlg.my_spring_project.gameplay;

import codes.mlg.my_spring_project.entity.Stage;
import lombok.AllArgsConstructor;

import java.util.Set;

import org.springframework.stereotype.Service;

import codes.mlg.my_spring_project.entity.Enemy;
import codes.mlg.my_spring_project.entity.GameCharacter;
import codes.mlg.my_spring_project.entity.Player;

@Service
@AllArgsConstructor
public class FightCalculator {

    DropCalculator dropCalculator = new DropCalculator();

    public String calculateFight(Player player, Stage stage) {

        Set<GameCharacter> gameCharacters = player.getGame().getCharacters();
        Enemy enemy = stage.getEnemy();

        int maxDamage = 0;
        int minDamage = 0;
        int maxHealing = 0;
        int minHealing = 0;
        int curPlayerHp = 0;
        int playerMaxHp = 0;
        int enemyHp = enemy.getHealth();
        int enemyMinDmg = enemy.getMinDamage();
        int enemyMaxDmg = enemy.getMaxDamage();
        int currentTick = 0;
        String fightLog = "[T:" + currentTick + "P:" + curPlayerHp + "E:" + enemyHp + "]";

        for (GameCharacter gameCharacter : gameCharacters) {
            if (gameCharacter.getCharacterClass() == 1 || gameCharacter.getCharacterClass() == 3) {
                maxDamage += gameCharacter.getMaxPower();
                minDamage += gameCharacter.getMinPower();
            } else if (gameCharacter.getCharacterClass() == 2) {
                maxHealing += gameCharacter.getMaxPower();
                minHealing += gameCharacter.getMinPower();
            }
            curPlayerHp += gameCharacter.getTotalHealth();
            playerMaxHp = curPlayerHp;
        }

        while (curPlayerHp >= 0 && enemyHp >= 0 && currentTick < 100) {

            int playerDamage = (int) Math.floor(Math.random() * (maxDamage - minDamage + 1) + minDamage);
            int enemyDamage = (int) Math.floor(Math.random() * (enemyMaxDmg - enemyMinDmg + 1) + enemyMinDmg);
            int playerHealing = (int) Math.floor(Math.random() * (maxHealing - minHealing + 1) + minHealing);

            curPlayerHp -= enemyDamage;
            enemyHp -= playerDamage;
            curPlayerHp += playerHealing;

            // Prevent overhealing
            if (curPlayerHp > playerMaxHp)
                curPlayerHp = playerMaxHp;

            fightLog += ",[T:" + currentTick + "P:" + curPlayerHp + "E:" + enemyHp + "]";
            // Lose
            if (curPlayerHp <= 0) {
                return fightLog + "L";
            }

            // Win
            if (enemyHp <= 0) {
                return fightLog + dropCalculator.calculateDrop(enemy) + "W";
            }
            currentTick++;
        }
        return "Something went wrong";
    }
}
