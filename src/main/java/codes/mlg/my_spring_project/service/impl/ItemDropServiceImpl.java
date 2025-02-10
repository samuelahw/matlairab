package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.ItemDropDto;
import codes.mlg.my_spring_project.entity.Enemy;
import codes.mlg.my_spring_project.entity.ItemDrop;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.ItemDropMapper;
import codes.mlg.my_spring_project.repository.ItemDropRepository;
import codes.mlg.my_spring_project.service.ItemDropService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemDropServiceImpl implements ItemDropService {

    private ItemDropRepository itemDropRepository;

    @Override
    public ItemDropDto createItemDrop(ItemDropDto itemDropDto, Enemy enemy) {
        itemDropDto.setEnemy(enemy);
        ItemDrop itemDrop = ItemDropMapper.mapToItemDrop(itemDropDto);
        ItemDrop savedItemDrop = itemDropRepository.save(itemDrop);

        return ItemDropMapper.mapToItemDropDto(savedItemDrop);
    }

    @Override
    public ItemDropDto getItemDropById(Long itemDropId) {
        ItemDrop itemDrop = itemDropRepository.findById(itemDropId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Item drop does not exist with this given id: " + itemDropId));


        return ItemDropMapper.mapToItemDropDto(itemDrop);
    }

    @Override
    public ItemDropDto updateItemDrop(Long itemDropId, ItemDropDto updatedItemDropDto) {

        ItemDrop itemDrop = itemDropRepository.findById(itemDropId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Item drop does not exist with this given id: " + itemDropId));

        itemDrop.setDropChance(updatedItemDropDto.getDropChance());
        itemDrop.setEnemy(updatedItemDropDto.getEnemy());
        itemDrop.setItemId(updatedItemDropDto.getItemId());

        ItemDrop updatedItemDrop = itemDropRepository.save(itemDrop);

        return ItemDropMapper.mapToItemDropDto(updatedItemDrop);
    }

    @Override
    public void deleteItemDrop(Long itemDropId) {

        ItemDrop itemDrop = itemDropRepository.findById(itemDropId)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Item drop does not exist with this given id: " + itemDropId));

        itemDropRepository.deleteById(itemDropId);
    }
}
