package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.ItemSlotDto;
import codes.mlg.my_spring_project.entity.ItemSlot;

import java.util.Set;

public interface ItemSlotService {

    ItemSlotDto createItemSlot();

    ItemSlotDto getItemSlotById(Long itemSlotId);

    void deleteItemSlot(Long itemSlotId);

    Boolean unequipItem(Long itemSlotId);

    Boolean equipItem(Long itemSlotId, Long itemId, Long inventoryId);

    Set<ItemSlot> generateGameCharacterItemSlots();
}
