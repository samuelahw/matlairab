package codes.mlg.my_spring_project.mapper;

import codes.mlg.my_spring_project.dto.StageDto;
import codes.mlg.my_spring_project.entity.Stage;

public class StageMapper {

    public static StageDto mapToStageDto(Stage stage){
        return new StageDto(
                stage.getId(),
                stage.getName(),
                stage.getDescription(),
                stage.getStageNumber(),
                stage.getEnemy()
        );
    }

    public static Stage mapToStage(StageDto stageDto){
        return new Stage(
                stageDto.getId(),
                stageDto.getName(),
                stageDto.getDescription(),
                stageDto.getStageNumber(),
                stageDto.getEnemy()
        );
    }
}
