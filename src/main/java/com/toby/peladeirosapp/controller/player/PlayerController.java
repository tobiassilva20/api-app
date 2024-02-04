package com.toby.peladeirosapp.controller.player;

import com.toby.peladeirosapp.domain.player.Player;
import com.toby.peladeirosapp.domain.player.PlayerRequestDTO;
import com.toby.peladeirosapp.domain.player.PlayerResponseDTO;
import com.toby.peladeirosapp.service.player.PlayerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {
    @Autowired
    PlayerServiceImpl playerService;
    @PostMapping
    public ResponseEntity<String> newPlayer(@RequestBody @Valid PlayerRequestDTO playerRequestDTO){
        String msg = null;
        Player player = new Player(playerRequestDTO);
        try {
            playerService.savePlayer(player);
            return new ResponseEntity<String>("Jogador salvo com sucesso.", HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<PlayerResponseDTO> getAllPlayers(){
        List<PlayerResponseDTO> players = playerService.getAllPlayers().
                stream().
                map(PlayerResponseDTO::new).
                toList();
        return players;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable String id){
        Player player = new Player();
        Optional<Player> optionalPlayer = this.playerService.getPlayerById(id);

        if(optionalPlayer.isPresent()){
            player = optionalPlayer.get();
            PlayerResponseDTO playerResponseDTO = new PlayerResponseDTO(player);
            return new ResponseEntity<>(playerResponseDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updateTotal(@RequestBody @Valid PlayerRequestDTO playerRequestDTO,
                                                          @PathVariable String id){
        Player player = new Player(playerRequestDTO);
        try{
            Player savedPlayer = this.playerService.updateTotal(id, player);
            if (savedPlayer != null){
                PlayerResponseDTO responseDTO = new PlayerResponseDTO(savedPlayer);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updatePartial(@RequestBody @Valid PlayerRequestDTO playerRequestDTO,
                                                          @PathVariable String id){
        Player player = new Player(playerRequestDTO);
        try{
            Player savedPlayer = this.playerService.updatePartial(id, player);
            if (savedPlayer != null){
                PlayerResponseDTO responseDTO = new PlayerResponseDTO(savedPlayer);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public void blockOrUnblockPlayer(@PathVariable String id){
        this.playerService.blockOrUnblockPlayer(id);
    }
}
