package codes.mlg.my_spring_project.service.impl;

import codes.mlg.my_spring_project.dto.GameDto;
import codes.mlg.my_spring_project.dto.PlayerDto;
import codes.mlg.my_spring_project.entity.Player;
import codes.mlg.my_spring_project.exception.ResourceNotFoundException;
import codes.mlg.my_spring_project.mapper.GameMapper;
import codes.mlg.my_spring_project.repository.PlayerRepository;
import codes.mlg.my_spring_project.mapper.PlayerMapper;
import codes.mlg.my_spring_project.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {

        Player player = PlayerMapper.mapToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);

        return PlayerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player does not exist with this given id: " + playerId));
        return PlayerMapper.mapToPlayerDto(player);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map((player) -> PlayerMapper.mapToPlayerDto(player))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerDto updatedPlayer) {

        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new ResourceNotFoundException("Player does not exist with this given id: " + playerId)
        );

        player.setName(updatedPlayer.getName());

        Player updatedPlayerObj = playerRepository.save(player);

        return PlayerMapper.mapToPlayerDto(updatedPlayerObj);
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new ResourceNotFoundException("Player does not exist with this given id: " + playerId)
        );

        playerRepository.deleteById(playerId);
    }

    @Override
    public PlayerDto setGameToPlayer(Long playerId, GameDto gameDto) {
        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new ResourceNotFoundException("Player does not exist with this given id: " + playerId)
        );

        player.setGame(GameMapper.mapToGame(gameDto));

        Player updatedPlayerObj = playerRepository.save(player);

        return PlayerMapper.mapToPlayerDto(updatedPlayerObj);
    }

}
