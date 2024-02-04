package com.toby.peladeirosapp.repository.player;

import com.toby.peladeirosapp.domain.player.Player;
import com.toby.peladeirosapp.domain.player.PlayerPosition;
import com.toby.peladeirosapp.domain.player.PlayerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByUsername(String username);
    Player findByNumber(int number);
}
