package com.acme.games.rock.paper.scissors.mapper;

import com.acme.games.rock.paper.scissors.dto.GameDto;
import com.acme.games.rock.paper.scissors.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameDto map(Game game);
}
