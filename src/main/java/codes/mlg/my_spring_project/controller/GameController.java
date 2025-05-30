package codes.mlg.my_spring_project.controller;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.InventoryDto;
import codes.mlg.my_spring_project.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private GameService gameService;

    // Post new empty game
    @PostMapping // POST: localhost:8080/api/v1/games
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        GameDto savedGame = gameService.createGame(gameDto);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    // Get game by id
    @GetMapping("{id}") // GET: localhost:8080/api/v1/games/<game_id>
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Long gameId) {
        GameDto gameDto = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameDto);
    }

    // Get all games
    @GetMapping // GET: localhost:8080/api/v1/games
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    // Update game data
    @PutMapping("{id}") // PUT localhost:8080/api/v1/games/<game_id>
    public ResponseEntity<GameDto> updateGame(@PathVariable("id") Long gameId,
            @RequestBody GameDto updatedGame) {
        GameDto updatedGameDto = gameService.updateGame(gameId, updatedGame);
        return ResponseEntity.ok(updatedGameDto);

    }

    // Delete game
    @DeleteMapping("{id}") // DELETE localhost:8080/api/v1/games/<game_id>
    public ResponseEntity<String> deleteGame(@PathVariable("id") Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok("Game successfully removed");
    }

    // Set inventory for game
    @PutMapping("{id}/inventory")
    public ResponseEntity<GameDto> setInventoryToGame(@PathVariable("id") Long gameId,
            @RequestBody InventoryDto inventoryDto) {
        GameDto gameDto = gameService.setInventoryToGame(gameId, inventoryDto);
        return ResponseEntity.ok(gameDto);
    }

    // Create new game for player
    @PostMapping("player/{id}")
    public ResponseEntity<GameDto> createGameForPlayer(@PathVariable("id") Long playerId) {
        GameDto savedGame = gameService.createNewGameByPlayerId(playerId);
        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    // Get fight result
    @GetMapping("/player/{id}/fight/{stageId}")
    public ResponseEntity<String> getFightStageResult(@PathVariable("id") Long playerId,
            @PathVariable("stageId") Long stageId) {

        String result = gameService.fightStage(playerId, stageId);

        return ResponseEntity.ok(result);
    }

}
