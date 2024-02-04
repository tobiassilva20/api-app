package com.toby.peladeirosapp.domain.player;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "player")
@Table(name = "player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Player {

    public Player(PlayerRequestDTO playerRequestDTO){
        this.name = playerRequestDTO.name();
        this.username = playerRequestDTO.username();
        this.email = playerRequestDTO.email();
        this.password = playerRequestDTO.password();
        this.number = playerRequestDTO.number();
        this.position = playerRequestDTO.position();
        this.role = playerRequestDTO.role();
        this.status = playerRequestDTO.status();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    @Size(min = 3, max = 255)
    @Column(unique = true)
    private String username;
    private String email;
    @NotNull
    @Size(min=3, max = 12)
    private String password;
    private PlayerRole role;
    private PlayerPosition position;
    @Column(unique = true)
    private int number;
    private boolean status;
}
