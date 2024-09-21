package com.acme.games.rock.paper.scissors.dto;

public class GameDto {

    private Long id;
    private int userWins;
    private int serverWins;
    private int draws;
    private String lastUserMove;

    public GameDto(Long id, int userWins, int serverWins, int draws, String lastUserMove) {
        this.id = id;
        this.userWins = userWins;
        this.serverWins = serverWins;
        this.draws = draws;
        this.lastUserMove = lastUserMove;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
