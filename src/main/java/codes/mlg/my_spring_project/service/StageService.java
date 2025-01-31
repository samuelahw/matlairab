package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.dto.StageDto;

public interface StageService {
    StageDto createStage(StageDto stageDto);

    StageDto getStageById(Long stageId);

    StageDto updateStage(Long stageId, StageDto updatedStage);

    StageDto setEnemyToStage(Long stageId, EnemyDto enemyDto);

    void deleteStage(Long stageId);
}
