package ttf.lost.application.avatar;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.infrastructure.api.avatar.AvatarAPIClient;

@Service
@RequiredArgsConstructor
public class LostArkAvatarService implements AvatarService {
	private final AvatarAPIClient avatarAPIClient;

	@Override
	public Avatar findUserAvatar(String nickname) {
		avatarAPIClient.findAvatar(nickname);
		return null;
	}
}
