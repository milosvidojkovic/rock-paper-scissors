package com.acme.games.rock.paper.scissors.service;

import com.acme.games.rock.paper.scissors.model.Game;
import com.acme.games.rock.paper.scissors.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Optional<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public boolean isValidMove(String move) {
        return move.equals("rock") || move.equals("paper") || move.equals("scissors");
    }

    public String generateServerMove(String lastUserMove) {
        if (lastUserMove.equals("rock")) {
            return "paper";
        } else if (lastUserMove.equals("paper")) {
            return "scissors";
        } else {
            return "rock";
        }
    }

    public void updateGameStats(Game currentGame, String userMove, String serverMove) {
        if (userMove.equals(serverMove)) {
            currentGame.setDraws(currentGame.getDraws() + 1);
        } else if ((userMove.equals("rock") && serverMove.equals("scissors")) ||
                (userMove.equals("paper") && serverMove.equals("rock")) ||
                (userMove.equals("scissors") && serverMove.equals("paper"))) {
            currentGame.setUserWins(currentGame.getUserWins() + 1);
        } else {
            currentGame.setServerWins(currentGame.getServerWins() + 1);
        }
        currentGame.setLastUserMove(userMove);
    }
}
