package ttf.lost.application.character;

import java.util.List;

import ttf.lost.infrastructure.api.character.GameCharacterDto;

public interface GameCharacterService {
	List<GameCharacterDto> findUserGameCharacters(String nickname);
}