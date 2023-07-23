package ttf.lost.infrastructure.api.avatar;

import java.util.List;

public interface AvatarAPIClient {
	/**
	 * Lost Ark Open API 에서 아바타 정보 가져오기
	 * @param nickname
	 */
	List<AvatarDto> findAvatar(String nickname);
}
