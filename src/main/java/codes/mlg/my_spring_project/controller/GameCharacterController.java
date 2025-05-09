package codes.mlg.my_spring_project.controller;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.GameCharacter;
import codes.mlg.my_spring_project.mapper.GameCharacterMapper;
import codes.mlg.my_spring_project.mapper.GameMapper;
import codes.mlg.my_spring_project.service.GameCharacterService;
import codes.mlg.my_spring_project.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/gamecharacters")
public class GameCharacterController {

    private GameCharacterService gameCharacterService;

    private GameService gameService;

    // Post new GameCharacter
    @PostMapping("{id}")
    public ResponseEntity<GameCharacter> createGameCharacter(@PathVariable("id") Long gameId,
                                                             @RequestBody GameCharacter gameCharacterRequest){
        GameCharacterDto gameCharacterDto = gameCharacterService.createGameCharacter(GameCharacterMapper.mapToGameCharacterDto(gameCharacterRequest), GameMapper.mapToGame(gameService.getGameById(gameId)));
        GameCharacter gameCharacter = GameCharacterMapper.mapToGameCharacter(gameCharacterDto);

        return new ResponseEntity<>(gameCharacter, HttpStatus.CREATED);
    }

    // Get GameCharacter by id
    @GetMapping("{id}")
    public ResponseEntity<GameCharacterDto> getGameCharacterById(@PathVariable("id") Long gameCharacterId) {
        GameCharacterDto gameCharacterDto = gameCharacterService.getGameCharacterById(gameCharacterId);
        return ResponseEntity.ok(gameCharacterDto);
    }

    // Updated GameCharacter
    @PutMapping("{id}")
    public ResponseEntity<GameCharacterDto> updateGameCharacter(@PathVariable("id")Long gameCharacterId,
                                                                @RequestBody GameCharacterDto updatedGameCharacter){
        GameCharacterDto updatedGameCharacterDto = gameCharacterService.updateGameCharacter(gameCharacterId, updatedGameCharacter);
        return ResponseEntity.ok(updatedGameCharacterDto);

    }

    // Delete GameCharacter
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGameCharacter(@PathVariable("id") Long gameCharacterId){
        gameCharacterService.deleteGameCharacter(gameCharacterId);
        return ResponseEntity.ok("GameCharacter successfully removed");
    }

    // Equip item for GameCharacter
    @GetMapping("{id}/player/{gcId}/character/{iId}")
    public ResponseEntity<Boolean> equipItem(@PathVariable("id") Long playerId, @PathVariable("gcId") Long gameCharacterId, @PathVariable("iId") Long itemId) {

        if(gameCharacterService.equipItemForGameCharacter(playerId,gameCharacterId,itemId)) return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);

    }
}
