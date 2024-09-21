package com.acme.games.rock.paper.scissors.exception;

public class GameNotStartedException extends RuntimeException {
    public GameNotStartedException() {
        super("Game has not been started. Please start the game first.");
    }
    public GameNotStartedException(String message) {
        super(message);
    }
}
