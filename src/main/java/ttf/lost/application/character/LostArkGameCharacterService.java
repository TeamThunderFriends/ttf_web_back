package ttf.lost.application.character;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.infrastructure.api.character.GameCharacterAPIClient;
import ttf.lost.infrastructure.api.character.GameCharacterDto;

@Service
@RequiredArgsConstructor
public class LostArkGameCharacterService implements GameCharacterService {
	private final GameCharacterAPIClient gameCharacterAPIClient;

	@Override
	public List<GameCharacterDto> findUserGameCharacters(String nickname) {
		return gameCharacterAPIClient.findCharacters(nickname);
	}
}