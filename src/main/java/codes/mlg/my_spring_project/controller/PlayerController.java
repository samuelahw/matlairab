package codes.mlg.my_spring_project.controller;


import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.PlayerDto;
import codes.mlg.my_spring_project.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private PlayerService playerService;

    // Post new players
    @PostMapping // POST: localhost:8080/api/v1/players
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerDto savedPlayer = playerService.createPlayer(playerDto);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    // Get player by id
    @GetMapping("{id}") // GET: localhost:8080/api/v1/players/<player_id>
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long playerId) {
        PlayerDto playerDto = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(playerDto);
    }

    // Get all players
    @GetMapping // GET: localhost:8080/api/v1/players
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        List<PlayerDto> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    // Update player
    @PutMapping("{id}") // PUT localhost:8080/api/v1/players/<player_id>
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable("id") Long playerId,
                                                  @RequestBody PlayerDto updatedPlayer) {
        PlayerDto playerDto = playerService.updatePlayer(playerId, updatedPlayer);
        return ResponseEntity.ok(playerDto);
    }

    // Delete player
    @DeleteMapping("{id}") // DELETE localhost:8080/api/v1/players/<player_id>
    public ResponseEntity<String> deletePlayer(@PathVariable("id") Long playerId){
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Player successfully removed");
    }

    // Set game to player
    @PutMapping("{id}/game") // PUT localhost:8080/api/v1/players/<player_id>/game
    public ResponseEntity<PlayerDto> setGameToPlayer(@PathVariable("id") Long playerId,
                                                     @RequestBody GameDto gameDto) {
        PlayerDto playerDto = playerService.setGameToPlayer(playerId, gameDto);
        return ResponseEntity.ok(playerDto);
    }

}
