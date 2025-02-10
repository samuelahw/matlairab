package codes.mlg.my_spring_project.controller;


import codes.mlg.my_spring_project.dto.EnemyDto;
import codes.mlg.my_spring_project.dto.StageDto;
import codes.mlg.my_spring_project.service.StageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/stages")
public class StageController {

    private StageService stageService;

    // Post new stage
    @PostMapping
    public ResponseEntity<StageDto> createStage(@RequestBody StageDto stageDto) {
        StageDto savedStage = stageService.createStage(stageDto);
        return new ResponseEntity<>(savedStage, HttpStatus.CREATED);
    }

    // Get stage by id
    @GetMapping("{id}")
    public ResponseEntity<StageDto> getStageById(@PathVariable("id") Long stageId) {
        StageDto stageDto = stageService.getStageById(stageId);
        return ResponseEntity.ok(stageDto);
    }

    // Update stage
    @PutMapping("{id}")
    public ResponseEntity<StageDto> updateStage(@PathVariable("id") Long stageId,
                                                @RequestBody StageDto updatedStage) {
        StageDto updatedStageDto = stageService.updateStage(stageId, updatedStage);

        return ResponseEntity.ok(updatedStageDto);
    }

    // Delete stage
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStage(@PathVariable("id") Long stageId){
        stageService.deleteStage(stageId);
        return ResponseEntity.ok("Stage successfully removed");
    }

    // Set enemy for stage
    public ResponseEntity<StageDto> setEnemyToStage(@PathVariable("id") Long stageId,
                                                    @RequestBody EnemyDto enemyDto) {
        StageDto stageDto = stageService.setEnemyToStage(stageId, enemyDto);
        return ResponseEntity.ok(stageDto);
    }
}
