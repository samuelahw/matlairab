package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.ItemSlotDto;
import codes.mlg.my_spring_project.entity.*;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.InventoryItemMapper;
import codes.mlg.my_spring_project.mapper.ItemSlotMapper;
import codes.mlg.my_spring_project.repository.InventoryItemRepository;
import codes.mlg.my_spring_project.repository.ItemSlotRepository;
import codes.mlg.my_spring_project.service.InventoryItemService;
import codes.mlg.my_spring_project.service.ItemSlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ItemSlotServiceImpl implements ItemSlotService {

    private InventoryItemService inventoryItemService;
    private InventoryItemRepository inventoryItemRepository;

    private ItemSlotRepository itemSlotRepository;
    @Override
    public ItemSlotDto createItemSlot() {
        return null;
    }

    @Override
    public ItemSlotDto getItemSlotById(Long itemSlotId) {

        ItemSlot itemSlot = itemSlotRepository.findById(itemSlotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ItemSlot does not exist with this given id: " + itemSlotId
                        ));

        return ItemSlotMapper.mapToItemSlotDto(itemSlot);
    }

    @Override
    public void deleteItemSlot(Long itemSlotId) {

        ItemSlot itemSlot = itemSlotRepository.findById(itemSlotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ItemSlot does not exist with this given id: " + itemSlotId
                        ));

        itemSlotRepository.deleteById(itemSlotId);

    }

    @Override
    public Boolean unequipItem(Long itemSlotId) {

        ItemSlot itemSlot = ItemSlotMapper.mapToItemSlot(getItemSlotById(itemSlotId));

        InventoryItem previousItem = itemSlot.getInventoryItem();

        itemSlot.setInventoryItem(null);

        previousItem.setEquipped(Boolean.FALSE);

        itemSlotRepository.save(itemSlot);
        inventoryItemRepository.save(previousItem);

        return true;
    }

    @Override
    public Boolean equipItem(Long itemSlotId, Long itemId, Long inventoryId) {

        ItemSlot itemSlot = ItemSlotMapper.mapToItemSlot(getItemSlotById(itemSlotId));

        InventoryItem newItem = InventoryItemMapper.mapToInventoryItem(inventoryItemService.findInventoryItemByItemIdFromInventory(inventoryId, itemId));

        //unequip previous item if one is equipped
        if(itemSlot.getInventoryItem() != null) {
            unequipItem(itemSlotId);
        }

        itemSlot.setInventoryItem(newItem);
        newItem.setEquipped(Boolean.TRUE);

        inventoryItemRepository.save(newItem);
        itemSlotRepository.save(itemSlot);

        return true;
    }

    @Override
    public Set<ItemSlot> generateGameCharacterItemSlots() {

        ItemSlot itemSlotOne = new ItemSlot();
        ItemSlot itemSlotTwo = new ItemSlot();
        ItemSlot itemSlotThree = new ItemSlot();
        ItemSlot itemSlotFour = new ItemSlot();
        ItemSlot itemSlotFive = new ItemSlot();

        itemSlotOne.setSlotNumber(1);
        itemSlotTwo.setSlotNumber(2);
        itemSlotThree.setSlotNumber(3);
        itemSlotFour.setSlotNumber(4);
        itemSlotFive.setSlotNumber(5);

        itemSlotOne.setName("Head");
        itemSlotTwo.setName("Body");
        itemSlotThree.setName("Legs");
        itemSlotFour.setName("Weapon");
        itemSlotFive.setName("Accessory");

        Set<ItemSlot> itemSlots = new HashSet<>();

        itemSlots.add(itemSlotOne);
        itemSlots.add(itemSlotTwo);
        itemSlots.add(itemSlotThree);
        itemSlots.add(itemSlotFour);
        itemSlots.add(itemSlotFive);

        return itemSlots;
    }
}
