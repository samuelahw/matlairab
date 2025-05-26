package codes.mlg.my_spring_project.gameplay;

import java.util.Set;

import org.springframework.stereotype.Service;

import codes.mlg.my_spring_project.entity.Enemy;
import codes.mlg.my_spring_project.entity.ItemDrop;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DropCalculator {

    // Returns the item id and coin drops as a string
    // Return string looks like: "[item_id:coin_drops]"
    public String calculateDrop(Enemy enemy) {
        Set<ItemDrop> itemDrops = enemy.getItemDrops();

        int tickets = 1000;

        int dropTicket = (int) Math.floor(Math.random() * tickets - 1) + 1;

        int itemTicket = 0;
        for (ItemDrop i : itemDrops) {
            itemTicket += i.getDropChance();
            if (dropTicket <= itemTicket) {
                return ",[" + i.getItemId() + ":" + (int) Math.floor(
                        Math.random() * (enemy.getMaxCoinDrop() - enemy.getMinCoinDrop() + 1) + enemy.getMinCoinDrop())
                        + "]";
            }
        }
        return ",[empty" + ":" + (int) Math
                .floor(Math.random() * (enemy.getMaxCoinDrop() - enemy.getMinCoinDrop() + 1) + enemy.getMinCoinDrop())
                + "]";
    }
}
