package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.GameCharacter;

public class GameCharacterMapper {

    public static GameCharacterDto mapToGameCharacterDto(GameCharacter gameCharacter){
        return new GameCharacterDto(
                gameCharacter.getId(),
                gameCharacter.getName(),
                gameCharacter.getItemSlots(),
                gameCharacter.getCharacterClass(),
                gameCharacter.getTotalHealth(),
                gameCharacter.getMaxPower(),
                gameCharacter.getMinPower(),
                gameCharacter.getGame()
        );
    }

    public static GameCharacter mapToGameCharacter(GameCharacterDto gameCharacterDto) {
        return new GameCharacter(
                gameCharacterDto.getId(),
                gameCharacterDto.getName(),
                gameCharacterDto.getCharacterClass(),
                gameCharacterDto.getTotalHealth(),
                gameCharacterDto.getMaxPower(),
                gameCharacterDto.getMinPower(),
                gameCharacterDto.getItemSlots(),
                gameCharacterDto.getGame()
        );
    }
}
