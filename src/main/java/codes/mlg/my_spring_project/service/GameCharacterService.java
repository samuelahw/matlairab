package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.Game;

public interface GameCharacterService {
    GameCharacterDto createGameCharacter(GameCharacterDto gameCharacterDto, Game game);

    GameCharacterDto getGameCharacterById(Long gameCharacterId);

    GameCharacterDto updateGameCharacter(Long gameCharacterId, GameCharacterDto updatedGameCharacter);

    void deleteGameCharacter(Long gameCharacterId);
}
