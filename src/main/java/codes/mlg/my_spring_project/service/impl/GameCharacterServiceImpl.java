package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.GameCharacterDto;
import codes.mlg.my_spring_project.entity.*;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.GameCharacterMapper;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.mapper.InventoryMapper;
import codes.mlg.my_spring_project.mapper.ItemMapper;
import codes.mlg.my_spring_project.repository.GameCharacterRepository;
import codes.mlg.my_spring_project.repository.ItemSlotRepository;
import codes.mlg.my_spring_project.service.GameCharacterService;
import codes.mlg.my_spring_project.service.InventoryService;
import codes.mlg.my_spring_project.service.ItemService;
import codes.mlg.my_spring_project.service.ItemSlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class GameCharacterServiceImpl implements GameCharacterService {

    private GameCharacterRepository gameCharacterRepository;
    private InventoryService inventoryService;
    private ItemService itemService;
    private ItemSlotRepository itemSlotRepository;
    private ItemSlotService itemSlotService;

    @Override
    public GameCharacterDto createGameCharacter(GameCharacterDto gameCharacterDto, Game game) {
        gameCharacterDto.setGame(game);
        GameCharacter gameCharacter = GameCharacterMapper.mapToGameCharacter(gameCharacterDto);
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

    @Override
    public Boolean equipItemForGameCharacter(Long playerId, Long gameCharacterId, Long itemId) {


        Inventory inventory = InventoryMapper.mapToInventory(inventoryService.getInventoryByPlayerId(playerId));

        if(inventoryService.searchItemFromInventoryByItemId(playerId, itemId) != null) {
            InventoryItem nInventoryItem = InventoryItemMapper.mapToInventoryItem(inventoryService.searchItemFromInventoryByItemId(playerId, itemId));
            Item item = ItemMapper.mapToItem(itemService.getItemById(itemId));
            GameCharacter gameCharacter = GameCharacterMapper.mapToGameCharacter(getGameCharacterById(gameCharacterId));

            ItemSlot itemSlot = getItemSlotNumberByItem(item, gameCharacter);

            // Item cant be equipped twice
            if(nInventoryItem.getEquipped()) {
                return false;
            }
            else {
                //itemSlot found
                if(itemSlot != null){

                    itemSlotService.equipItem(itemSlot.getId(), itemId, inventory.getId());
                    gameCharacterRepository.save(gameCharacter);

                    return true;
                }
                //itemSlot did not found
                else return false;
            }
        }
        return false;
    }

    //get itemSlotNumber by item
    private ItemSlot getItemSlotNumberByItem(Item item, GameCharacter gameCharacter) {

        Set<ItemSlot> itemSlots = gameCharacter.getItemSlots();

        for(ItemSlot i : itemSlots) {
            if(i.getSlotNumber() == item.getItemSlotNumber()) {
                return i;
            }
        }
        return null;
    }

    public GameCharacterDto setItemSlotsToGameCharacter(GameCharacter gameCharacter) {

        gameCharacter.setItemSlots(itemSlotService.generateGameCharacterItemSlots());
        gameCharacterRepository.save(gameCharacter);
        for(ItemSlot i : gameCharacter.getItemSlots()) {
            i.setGameCharacter(gameCharacter);
            itemSlotRepository.save(i);
        }

        return GameCharacterMapper.mapToGameCharacterDto(gameCharacter);
    }
}
