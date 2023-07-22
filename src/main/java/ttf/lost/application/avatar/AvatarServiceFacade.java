package ttf.lost.application.avatar;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.presentation.api.avatar.response.AvatarDtoResponse;

@Service
@RequiredArgsConstructor
public class AvatarServiceFacade {
	private final AvatarService avatarService;

	public AvatarDtoResponse findUserAvatarAndPrice(String nickname) {
		// TODO : Open API 호출 후 DTO에 저장,
		avatarService.findUserAvatar(nickname);
		// TODO : Repository에 정보 및 가격 저장
		// TODO : 가격을 모두 더해주기
		return null;
	}
}
