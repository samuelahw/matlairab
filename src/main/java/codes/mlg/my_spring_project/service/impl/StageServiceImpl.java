package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.dto.StageDto;
import codes.mlg.my_spring_project.entity.Stage;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.EnemyMapper;
import codes.mlg.my_spring_project.mapper.StageMapper;
import codes.mlg.my_spring_project.repository.StageRepository;
import codes.mlg.my_spring_project.service.StageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StageServiceImpl implements StageService {

    private StageRepository stageRepository;

    @Override
    public StageDto createStage(StageDto stageDto) {

        Stage stage = StageMapper.mapToStage(stageDto);
        Stage savedStage = stageRepository.save(stage);

        return StageMapper.mapToStageDto(savedStage);
    }

    @Override
    public StageDto getStageById(Long stageId) {

        Stage stage = stageRepository.findById(stageId).orElseThrow(
                () -> new ResourceNotFoundException("Stage does not exist with this given id: " + stageId)
        );

        return StageMapper.mapToStageDto(stage);
    }

    @Override
    public StageDto updateStage(Long stageId, StageDto updatedStage) {

        Stage stage = stageRepository.findById(stageId).orElseThrow(
                () -> new ResourceNotFoundException("Stage does not exist with this given id: " + stageId)
        );

        stage.setName(updatedStage.getName());
        stage.setDescription(updatedStage.getDescription());
        stage.setStageNumber(updatedStage.getStageNumber());

        Stage updatedStageObj = stageRepository.save(stage);

        return StageMapper.mapToStageDto(updatedStageObj);
    }

    @Override
    public StageDto setEnemyToStage(Long stageId, EnemyDto enemyDto) {
        Stage stage = stageRepository.findById(stageId).orElseThrow(
                () -> new ResourceNotFoundException("Stage does not exist with this given id: " + stageId)
        );

        stage.setEnemy(EnemyMapper.mapToEnemy(enemyDto));
        Stage updatedStageObj = stageRepository.save(stage);

        return StageMapper.mapToStageDto(updatedStageObj);
    }

    @Override
    public void deleteStage(Long stageId) {

        Stage stage = stageRepository.findById(stageId).orElseThrow(
                () -> new ResourceNotFoundException("Stage does not exist with this given id: " + stageId)
        );

        stageRepository.deleteById(stageId);
    }
}
