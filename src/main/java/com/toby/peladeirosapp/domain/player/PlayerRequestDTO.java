package com.toby.peladeirosapp.domain.player;

public record PlayerRequestDTO(String name, String username, String password, String email, PlayerRole role,
                               PlayerPosition position, int number, boolean status) {
}
