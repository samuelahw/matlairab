package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.entity.Game;

public class GameMapper {

    public static GameDto mapToGameDto(Game game){
        return new GameDto(
                game.getId(),
                game.getStage(),
                game.getBalance(),
                game.getPlayer(),
                game.getInventory()
        );
    }

    public static Game mapToGame(GameDto gameDto){
        return new Game(
                gameDto.getId(),
                gameDto.getStage(),
                gameDto.getBalance(),
                gameDto.getPlayer(),
                gameDto.getInventory()
        );
    }
}
