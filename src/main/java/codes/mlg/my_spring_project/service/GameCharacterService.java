package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.Game;
import codes.mlg.my_spring_project.entity.GameCharacter;

public interface GameCharacterService {
    GameCharacterDto createGameCharacter(GameCharacterDto gameCharacterDto, Game game);

    GameCharacterDto getGameCharacterById(Long gameCharacterId);

    GameCharacterDto updateGameCharacter(Long gameCharacterId, GameCharacterDto updatedGameCharacter);

    void deleteGameCharacter(Long gameCharacterId);

    Boolean equipItemForGameCharacter(Long PlayerId,Long gameCharacterId, Long itemId);

    GameCharacterDto setItemSlotsToGameCharacter(GameCharacter gameCharacter);
}
