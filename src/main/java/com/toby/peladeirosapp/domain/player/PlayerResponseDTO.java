package com.toby.peladeirosapp.domain.player;

public record PlayerResponseDTO(String id, String name, String username, String email,
                                String password, int number, PlayerPosition position, PlayerRole role,
                                boolean status) {
    public PlayerResponseDTO(Player player){
        this(player.getId(), player.getName(), player.getUsername(), player.getEmail(), player.getPassword(),
                player.getNumber(), player.getPosition(), player.getRole(), player.isStatus());
    }
}