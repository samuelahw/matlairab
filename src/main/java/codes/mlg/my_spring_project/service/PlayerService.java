package codes.mlg.my_spring_project.service;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(Long playerId);

    List<PlayerDto> getAllPlayers();

    PlayerDto updatePlayer(Long playerId, PlayerDto updatedPlayer);

    void deletePlayer(Long playerId);

    PlayerDto setGameToPlayer(Long playerId, GameDto gameDto);
}
