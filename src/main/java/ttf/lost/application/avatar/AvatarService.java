package ttf.lost.application.avatar;

import java.util.List;

import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.presentation.api.avatar.response.AvatarAndTotalPriceResponse;

public interface AvatarService {
	/**
	 * 착용 중인 아바타 정보 가져오기
	 * @param nickname 캐릭터 닉네임
	 */
	List<AvatarDto> findUserAvatar(String nickname);

	/**
	 * 리스트에서 뽑아온 아바타 이름으로 거래소 검색 후 아바타 리스트와 가격 가져오기(Repository 저장)
	 * @param apiList Open API로 받아온 AvatarList
	 * @param nickname 캐릭터 닉네임
	 */
	List<AvatarAndPriceDto> avatarPriceSave(List<AvatarDto> apiList, String nickname);

	/**
	 * 리스트의 각 Price들을 더해 Total을 받고 나머지 정보들과 묶어서 Return
	 * @param avatarAndPriceDtoList 이전 List에서 받아온 데이터들
	 */
	AvatarAndTotalPriceResponse avatarTotalPriceAndInfo(List<AvatarAndPriceDto> avatarAndPriceDtoList, String nickname);
}
