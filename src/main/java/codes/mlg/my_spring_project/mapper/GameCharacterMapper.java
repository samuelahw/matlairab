package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.GameCharacter;

public class GameCharacterMapper {

    public static GameCharacterDto mapToGameCharacterDto(GameCharacter gameCharacter){
        return new GameCharacterDto(
                gameCharacter.getId(),
                gameCharacter.getName(),
                gameCharacter.getWeapon_slot_item(),
                gameCharacter.getHead_slot_item(),
                gameCharacter.getChest_slot_item(),
                gameCharacter.getLeg_slot_item(),
                gameCharacter.getAccessory_slot_item(),
                gameCharacter.getGame()
        );
    }

    public static GameCharacter mapToCharacter(GameCharacterDto gameCharacterDto) {
        return new GameCharacter(
                gameCharacterDto.getId(),
                gameCharacterDto.getName(),
                gameCharacterDto.getWeaponSlotItem(),
                gameCharacterDto.getHeadSlotItem(),
                gameCharacterDto.getChestSlotItem(),
                gameCharacterDto.getLegSlotItem(),
                gameCharacterDto.getAccessorySlotItem(),
                gameCharacterDto.getGame()
        );
    }
}
