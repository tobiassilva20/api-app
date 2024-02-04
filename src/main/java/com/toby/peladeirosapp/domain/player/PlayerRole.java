package com.toby.peladeirosapp.domain.player;

public enum PlayerRole {
    ADMIN("ADMIN"), NORMAL("NORMAL");
    public String role;
    PlayerRole(String role){
        this.role = role;
    }
}
