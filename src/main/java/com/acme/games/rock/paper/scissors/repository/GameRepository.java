package com.acme.games.rock.paper.scissors.repository;

import com.acme.games.rock.paper.scissors.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
