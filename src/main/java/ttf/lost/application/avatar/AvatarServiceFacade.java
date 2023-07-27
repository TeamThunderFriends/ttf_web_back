package ttf.lost.application.avatar;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.presentation.api.avatar.response.AvatarDtoResponse;

@Service
@RequiredArgsConstructor
public class AvatarServiceFacade {
	private final AvatarService avatarService;

	public AvatarDtoResponse findUserAvatarAndPrice(String nickname) {
		// TODO : Open API 호출 후 DTO에 저장,
		List<AvatarDto> userAvatarList = avatarService.findUserAvatar(nickname);
		// TODO : Repository에 정보 및 Open API (거래소 검색) 호출 후 가격 저장 (DTO)
		List<AvatarAndPriceDto> avatarAndPriceDtoList =
			avatarService.avatarPriceSave(userAvatarList, nickname);
		// TODO : 가격을 모두 더해주고 위의 정보들과 같이 Return

		return null;
	}
}
