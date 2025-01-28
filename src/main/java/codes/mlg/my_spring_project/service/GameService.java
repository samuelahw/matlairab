package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto createGame(GameDto gameDto);

    GameDto getGameById(Long gameId);

    List<GameDto> getAllGames();

    GameDto updateGame(Long gameId, GameDto updatedGame);

    void deleteGame(Long gameId);
}
