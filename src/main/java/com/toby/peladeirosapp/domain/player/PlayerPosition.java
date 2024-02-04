package com.toby.peladeirosapp.domain.player;

public enum PlayerPosition {
    ATA("ATAQUE"), MEI("MEIO"), ZAG("ZAGUEIRO"), GOL("GOLEIRO");

    public String position;

    PlayerPosition(String position){
        this.position = position;
    }
}
