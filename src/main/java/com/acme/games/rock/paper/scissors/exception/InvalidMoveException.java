package com.acme.games.rock.paper.scissors.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(){
        super("Invalid move! Please choose either 'rock', 'paper', or 'scissors'.");
    }
    public InvalidMoveException(String message) {
        super(message);
    }
}
