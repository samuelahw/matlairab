package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.Game;
import codes.mlg.my_spring_project.entity.GameCharacter;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.GameCharacterMapper;
import codes.mlg.my_spring_project.repository.GameCharacterRepository;
import codes.mlg.my_spring_project.service.GameCharacterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameCharacterServiceImpl implements GameCharacterService {

    private GameCharacterRepository gameCharacterRepository;

    @Override
    public GameCharacterDto createGameCharacter(GameCharacterDto gameCharacterDto, Game game) {
        gameCharacterDto.setGame(game);
        GameCharacter gameCharacter = GameCharacterMapper.mapToCharacter(gameCharacterDto);
        GameCharacter savedGameCharacter = gameCharacterRepository.save(gameCharacter);

        return GameCharacterMapper.mapToGameCharacterDto(savedGameCharacter);
    }

    @Override
    public GameCharacterDto getGameCharacterById(Long gameCharacterId) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("GameCharacter does not exist with this given id: " + gameCharacterId));

        return GameCharacterMapper.mapToGameCharacterDto(gameCharacter);
    }

    @Override
    public GameCharacterDto updateGameCharacter(Long gameCharacterId, GameCharacterDto updatedGameCharacter) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("GameCharacter does not exist with this given id: " + gameCharacterId));

        gameCharacter.setWeapon_slot_item(gameCharacter.getWeapon_slot_item());
        gameCharacter.setHead_slot_item(updatedGameCharacter.getHeadSlotItem());
        gameCharacter.setChest_slot_item(updatedGameCharacter.getChestSlotItem());
        gameCharacter.setLeg_slot_item(updatedGameCharacter.getLegSlotItem());
        gameCharacter.setAccessory_slot_item(updatedGameCharacter.getAccessorySlotItem());

        GameCharacter savedGameCharacter = gameCharacterRepository.save(gameCharacter);
        return GameCharacterMapper.mapToGameCharacterDto(savedGameCharacter);
    }

    @Override
    public void deleteGameCharacter(Long gameCharacterId) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("GameCharacter does not exist with this given id: " + gameCharacterId));
        gameCharacterRepository.deleteById(gameCharacterId);
    }
}
