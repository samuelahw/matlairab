package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.PlayerDto;
import codes.mlg.my_spring_project.entity.Player;

public class PlayerMapper {

    public static PlayerDto mapToPlayerDto(Player player){
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getGame()
        );
    }

    public static Player mapToPlayer(PlayerDto playerDto) {
        return new Player(
                playerDto.getId(),
                playerDto.getName(),
                playerDto.getGame()
        );
    }
}
