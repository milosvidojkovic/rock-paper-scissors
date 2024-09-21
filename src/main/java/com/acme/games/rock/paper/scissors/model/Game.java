package com.acme.games.rock.paper.scissors.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_wins")
    private int userWins;

    @Column(name = "server_wins")
    private int serverWins;

    @Column(name = "draws")
    private int draws;

    @Column(name = "last_user_move")
    private String lastUserMove;

    public Game() {}

    public Game(int userWins, int serverWins, int draws, String lastUserMove) {
        this.userWins = userWins;
        this.serverWins = serverWins;
        this.draws = draws;
        this.lastUserMove = lastUserMove;
    }

    public Long getId() {
        return id;
    }

    public int getUserWins() {
        return userWins;
    }

    public void setUserWins(int userWins) {
        this.userWins = userWins;
    }

    public int getServerWins() {
        return serverWins;
    }

    public void setServerWins(int serverWins) {
        this.serverWins = serverWins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getLastUserMove() {
        return lastUserMove;
    }

    public void setLastUserMove(String lastUserMove) {
        this.lastUserMove = lastUserMove;
    }
}

