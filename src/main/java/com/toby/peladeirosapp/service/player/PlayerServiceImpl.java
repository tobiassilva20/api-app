package com.toby.peladeirosapp.service.player;

import com.toby.peladeirosapp.domain.player.Player;
import com.toby.peladeirosapp.domain.player.PlayerResponseDTO;
import com.toby.peladeirosapp.exception.BadRequestExpection;
import com.toby.peladeirosapp.repository.player.PlayerRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public void savePlayer(Player player) throws BadRequestExpection {
        this.playerRepository.findByUsername(player.getUsername());
        if(playerRepository.findByUsername(player.getUsername()) != null ||
                playerRepository.findByNumber(player.getNumber()) != null){
            throw new BadRequestExpection("Username ou número já em uso.");
        }
        this.playerRepository.save(player);
    }
    @Override
    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }
    @Override
    public Optional<Player> getPlayerById(String id) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        return optionalPlayer;
    }
    @Transactional
    @Override
    public Player updateTotal(String id, Player player) throws BadRequestExpection {
        if(id == null){
            throw new BadRequestExpection("Id não pode ser nulo");
        }
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        if(!optionalPlayer.isPresent()){
            return null;
        }
        Player existingPlayer = optionalPlayer.get();
        existingPlayer.setName(player.getName());
        existingPlayer.setUsername(player.getUsername());
        existingPlayer.setEmail(player.getEmail());
        existingPlayer.setRole(player.getRole());
        existingPlayer.setPosition(player.getPosition());
        existingPlayer.setNumber(player.getNumber());
        existingPlayer.setStatus(player.isStatus());
        return playerRepository.save(existingPlayer);
    }
    @Transactional
    @Override
    public Player updatePartial(String id, Player player) throws BadRequestExpection {
        if(id == null){
            throw new BadRequestExpection("Id não pode ser nulo");
        }
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        if(!optionalPlayer.isPresent()){
            return null;
        }
        Player existingPlayer = optionalPlayer.get();
        existingPlayer.setName(player.getName());
        existingPlayer.setEmail(player.getEmail());
        return playerRepository.save(existingPlayer);
    }

    @Override
    public void blockOrUnblockPlayer(String id) {
        Player player = new Player();
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        if(optionalPlayer.isPresent()){
            player = optionalPlayer.get();
        }
        boolean status = player.isStatus();
        player.setStatus(!status);
        this.playerRepository.save(player);
    }
}
