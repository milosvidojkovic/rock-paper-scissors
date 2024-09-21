package com.acme.games.rock.paper.scissors.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.acme.games.rock.paper.scissors.model.Game;
import com.acme.games.rock.paper.scissors.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game(2, 1, 1, "rock");
    }

    @Test
    void testSaveGame() {
        when(gameRepository.save(game)).thenReturn(game);
        Game savedGame = gameService.saveGame(game);
        assertNotNull(savedGame);
        assertEquals(2, savedGame.getUserWins());
    }

    @Test
    void testGetGameById() {
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        Optional<Game> retrievedGame = gameService.getGame(1L);
        assertTrue(retrievedGame.isPresent());
        assertEquals(2, retrievedGame.get().getUserWins());
    }

    @Test
    void testGetGameById_NotFound() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Game> retrievedGame = gameService.getGame(1L);
        assertFalse(retrievedGame.isPresent());
    }

    @Test
    void testDeleteGame() {
        gameService.deleteGame(1L);
        verify(gameRepository, times(1)).deleteById(1L);
    }
}
