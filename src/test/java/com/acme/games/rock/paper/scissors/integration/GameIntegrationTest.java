package com.acme.games.rock.paper.scissors.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStartGame() throws Exception {
        mockMvc.perform(post("/game/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("Game started!"));
    }

    @Test
    void testPlayGame() throws Exception {
        // First, start the game
        mockMvc.perform(post("/game/start"))
                .andExpect(status().isOk());

        // Then play the game
        mockMvc.perform(post("/game/play?userMove=rock"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("You played: rock,")));
    }

    @Test
    void testEndGame() throws Exception {
        // Start the game
        mockMvc.perform(post("/game/start"))
                .andExpect(status().isOk());

        // End the game
        mockMvc.perform(post("/game/end"))
                .andExpect(status().isOk())
                .andExpect(content().string("Game ended! Final stats saved."));
    }

    @Test
    void testGetAllGames() throws Exception {
        // Make sure some games are played first (setup)
        mockMvc.perform(post("/game/start"));
        mockMvc.perform(post("/game/play?userMove=rock"));
        mockMvc.perform(post("/game/end"));

        // Now, fetch all games
        mockMvc.perform(get("/game/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()); // Check that it returns an array
    }

//    @Test
//    void testGameLifecycle() throws Exception {
//        // Start a game
//        mockMvc.perform(post("/game/start"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Game started! Make your move with /play"));
//
//        // Play a round
//        mockMvc.perform(post("/game/play").param("userMove", "rock"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("You played: rock, Server played: paper"));
//
//        // Check stats
//        mockMvc.perform(get("/game/stats"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("User Wins: 0, Server Wins: 1, Draws: 0"));
//
//        // End the game
//        mockMvc.perform(post("/game/end"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Game ended! Final stats: User Wins: 0, Server Wins: 1, Draws: 0"));
//    }
//
//    @Test
//    void testSaveAndRetrieveGame() throws Exception {
//        // Start and play a game
//        mockMvc.perform(post("/game/start"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/game/play").param("userMove", "rock"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post("/game/end"))
//                .andExpect(status().isOk());
//
//        // Retrieve all games
//        mockMvc.perform(get("/game/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].userWins").value(0))
//                .andExpect(jsonPath("$[0].serverWins").value(1));
//    }
}
