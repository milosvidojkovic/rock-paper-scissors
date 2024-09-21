package com.acme.games.rock.paper.scissors.controller;

import com.acme.games.rock.paper.scissors.exception.GameNotFoundException;
import com.acme.games.rock.paper.scissors.exception.GameNotStartedException;
import com.acme.games.rock.paper.scissors.exception.InvalidMoveException;
import com.acme.games.rock.paper.scissors.model.Game;
import com.acme.games.rock.paper.scissors.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    private Game currentGame;

    @PostMapping("/start")
    public ResponseEntity<String> startGame() {
        currentGame = new Game(0, 0, 0, "");
        return ResponseEntity.ok("Game started! Make your move with /play");
    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestParam String userMove) {
        if (currentGame == null) {
            throw new GameNotStartedException();
        }

        if (!gameService.isValidMove(userMove)) {
            throw new InvalidMoveException("Invalid move! Please choose either 'rock', 'paper', or 'scissors'.");
        }

        String serverMove = gameService.generateServerMove(currentGame.getLastUserMove());
        gameService.updateGameStats(currentGame, userMove, serverMove);
        gameService.saveGame(currentGame); // Save to H2

        return ResponseEntity.ok("You played: " + userMove + ", Server played: " + serverMove);
    }

    @GetMapping("/stats")
    public ResponseEntity<String> getStats() {
        if (currentGame == null) {
            throw new GameNotStartedException();
        }

        return ResponseEntity.ok("User Wins: " + currentGame.getUserWins() +
                ", Server Wins: " + currentGame.getServerWins() +
                ", Draws: " + currentGame.getDraws());
    }

    @PostMapping("/end")
    public ResponseEntity<String> endGame() {
        if (currentGame == null) {
            throw new GameNotStartedException();
        }

        gameService.saveGame(currentGame);  // Save the final game state
        currentGame = null;
        return ResponseEntity.ok("Game ended! Final stats saved.");
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGame(id);
        return game.map(ResponseEntity::ok)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found."));
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        Optional<Game> game = gameService.getGame(id);
        if (game.isPresent()) {
            gameService.deleteGame(id);
            return ResponseEntity.ok("Game deleted with id: " + id);
        } else {
            throw new GameNotFoundException("Game with ID " + id + " not found.");
        }
    }
}
