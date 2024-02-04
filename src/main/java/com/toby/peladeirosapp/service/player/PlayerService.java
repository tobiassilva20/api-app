package com.toby.peladeirosapp.service.player;

import com.toby.peladeirosapp.domain.player.Player;
import com.toby.peladeirosapp.exception.BadRequestExpection;
import com.toby.peladeirosapp.repository.player.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PlayerService {

    void savePlayer(Player player) throws BadRequestExpection;
    List<Player> getAllPlayers();
    Optional<Player> getPlayerById(String id);
    Player updateTotal(String id, Player player) throws BadRequestExpection;
    Player updatePartial(String id, Player player) throws BadRequestExpection;
    void blockOrUnblockPlayer(String id);
}
