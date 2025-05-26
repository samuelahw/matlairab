package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.entity.*;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.gameplay.FightCalculator;
import codes.mlg.my_spring_project.mapper.GameCharacterMapper;
import codes.mlg.my_spring_project.mapper.GameMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.mapper.PlayerMapper;
import codes.mlg.my_spring_project.mapper.StageMapper;
import codes.mlg.my_spring_project.repository.*;
import codes.mlg.my_spring_project.service.GameCharacterService;
import codes.mlg.my_spring_project.service.GameService;
import codes.mlg.my_spring_project.service.PlayerService;
import codes.mlg.my_spring_project.service.StageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GameCharacterRepository gameCharacterRepository;
    private PlayerRepository playerRepository;
    private InventoryRepository inventoryRepository;
    private PlayerService playerService;
    private GameCharacterService gameCharacterService;
    private FightCalculator fightCalculator;
    private StageService stageService;

    @Override
    public GameDto createGame(GameDto gameDto) {

        Game game = GameMapper.mapToGame(gameDto);
        Game savedGame = gameRepository.save(game);

        return GameMapper.mapToGameDto(savedGame);
    }

    @Override
    public GameDto getGameById(Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId));

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
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId));

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
                () -> new ResourceNotFoundException("Game does not exist with this given id: " + gameId));

        game.setInventory(InventoryMapper.mapToInventory(inventoryDto));
        Game updatedGameObj = gameRepository.save(game);

        return GameMapper.mapToGameDto(updatedGameObj);
    }

    // Creating new game for player
    @Override
    public GameDto createNewGameByPlayerId(Long playerId) {
        Game gameInit = new Game();
        Inventory inventoryInit = new Inventory();

        Game game = gameRepository.save(gameInit);

        Player player = PlayerMapper.mapToPlayer(playerService.getPlayerById(playerId));

        Inventory inventory = inventoryRepository.save(inventoryInit);

        GameCharacter tank = new GameCharacter();
        GameCharacter healer = new GameCharacter();
        GameCharacter damager = new GameCharacter();

        tank.setCharacterClass(1);
        healer.setCharacterClass(2);
        damager.setCharacterClass(3);

        tank.setGame(game);
        healer.setGame(game);
        damager.setGame(game);

        tank.setName("Tank");
        healer.setName("Healer");
        damager.setName("Damager");

        tank = GameCharacterMapper.mapToGameCharacter(gameCharacterService.setItemSlotsToGameCharacter(tank));
        healer = GameCharacterMapper.mapToGameCharacter(gameCharacterService.setItemSlotsToGameCharacter(healer));
        damager = GameCharacterMapper.mapToGameCharacter(gameCharacterService.setItemSlotsToGameCharacter(damager));

        Set<GameCharacter> gameCharacters = new HashSet<>();

        gameCharacters.add(tank);
        gameCharacters.add(healer);
        gameCharacters.add(damager);

        game.setCharacters(gameCharacters);

        player.setGame(game);
        game.setPlayer(player);
        inventory.setGame(game);
        game.setInventory(inventory);

        playerRepository.save(player);
        inventoryRepository.save(inventory);
        gameCharacterRepository.save(tank);
        gameCharacterRepository.save(healer);
        gameCharacterRepository.save(damager);

        Game savedGame = gameRepository.save(game);

        return GameMapper.mapToGameDto(savedGame);
    }

    @Override
    public String fightStage(Long playerId, Long stageId) {

        Player player = PlayerMapper.mapToPlayer(playerService.getPlayerById(playerId));

        Stage stage = StageMapper.mapToStage(stageService.getStageById(stageId));

        String fightResult = fightCalculator.calculateFight(player, stage);

        if (fightResult.charAt(fightResult.length() - 1) == 'W') {
            String coinString = fightResult.substring(fightResult.lastIndexOf(":") + 1, fightResult.lastIndexOf("]"));
            editBalanceAddition(playerId, Integer.parseInt(coinString));
        } else if (fightResult.charAt(fightResult.length() - 1) == 'L') {
            // Losing will reduce the balance by 20%
            editBalanceMultiplication(playerId, 0.8);
        }
        return fightResult;
    }

    @Override
    public int editBalanceAddition(Long playerId, int amount) {

        Game game = GameMapper.mapToGame(getGameByPlayerId(playerId));

        int currentBalance = game.getBalance();
        int newBalance = currentBalance + amount;

        game.setBalance(newBalance);

        gameRepository.save(game);

        return newBalance;

    }

    @Override
    public int editBalanceMultiplication(Long playerId, double rate) {

        Game game = GameMapper.mapToGame(getGameByPlayerId(playerId));

        double currentBalance = game.getBalance();
        int newBalance = (int) (currentBalance * rate);

        game.setBalance(newBalance);

        gameRepository.save(game);

        return newBalance;
    }

    @Override
    public GameDto getGameByPlayerId(Long playerId) {
        return GameMapper.mapToGameDto(playerService.getPlayerById(playerId).getGame());
    }
}
