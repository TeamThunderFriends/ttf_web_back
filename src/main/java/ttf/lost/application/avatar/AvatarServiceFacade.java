package ttf.lost.application.avatar;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.presentation.api.avatar.response.AvatarAndTotalPriceResponse;

@Service
@RequiredArgsConstructor
public class AvatarServiceFacade {
	private final AvatarService avatarService;

	public AvatarAndTotalPriceResponse findUserAvatarAndPrice(String nickname) {
		List<AvatarDto> userAvatarList = avatarService.findUserAvatar(nickname);
		List<AvatarAndPriceDto> avatarAndPriceDtoList =
			avatarService.avatarPriceSave(userAvatarList, nickname);
		return avatarService.avatarTotalPriceAndInfo(avatarAndPriceDtoList, nickname);
	}
}
