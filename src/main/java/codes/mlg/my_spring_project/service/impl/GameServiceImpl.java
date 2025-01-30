package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.entity.Game;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.GameMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.repository.GameRepository;
import codes.mlg.my_spring_project.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Override
    public GameDto createGame(GameDto gameDto) {

        Game game = GameMapper.mapToGame(gameDto);
        Game savedGame = gameRepository.save(game);

        return GameMapper.mapToGameDto(savedGame);
    }

    @Override
    public GameDto getGameById(Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId)
        );

        return GameMapper.mapToGameDto(game);
    }

    @Override
    public List<GameDto> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map((game) -> GameMapper.mapToGameDto(game))
                .collect(Collectors.toList());
    }

    @Override
    public GameDto updateGame(Long gameId, GameDto updatedGame) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId)
        );

        game.setBalance(updatedGame.getBalance());
        game.setStage(updatedGame.getStage());

        Game updatedGameObj = gameRepository.save(game);

        return GameMapper.mapToGameDto(updatedGameObj);
    }

    @Override
    public void deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId));

        gameRepository.deleteById(gameId);
    }

    @Override
    public GameDto setInventoryToGame(Long gameId, InventoryDto inventoryDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId)
        );

        game.setInventory(InventoryMapper.mapToInventory(inventoryDto));
        Game updatedGameObj = gameRepository.save(game);

        return GameMapper.mapToGameDto(updatedGameObj);
    }
}
