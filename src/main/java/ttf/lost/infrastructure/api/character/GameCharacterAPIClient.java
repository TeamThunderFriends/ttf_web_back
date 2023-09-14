package ttf.lost.infrastructure.api.character;

import java.util.List;

public interface GameCharacterAPIClient {
	/**
	 * Lost Ark Open API 에서 캐릭터들 정보 가져오기
	 * @param nickname
	 */
	List<GameCharacterDto> findCharacters(String nickname);
}
