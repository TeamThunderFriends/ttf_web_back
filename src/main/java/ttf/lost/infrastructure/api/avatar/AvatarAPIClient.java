package ttf.lost.infrastructure.api.avatar;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AvatarAPIClient {
	/**
	 * Lost Ark Open API 에서 아바타 정보 가져오기
	 * @param nickname
	 */
	List<AvatarDto> findAvatar(String nickname);

	/**
	 * Lost Ark Open API Markets 에서 각 아바타의 가격과 정보 가져오기
	 * @param apiList List로 가져온 아바타 정보 중 아바타 정보
	 */
	List<AvatarAndPriceDto> findAvatarPrice(List<AvatarDto> apiList)
		throws JsonProcessingException;
}
