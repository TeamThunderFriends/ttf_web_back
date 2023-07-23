package ttf.lost.application.avatar;

import ttf.lost.domain.avatar.Avatar;

public interface AvatarService {
	/**
	 * 착용 중인 아바타 정보 가져오기
	 * @param nickname 캐릭터 닉네임
	 */
	Avatar findUserAvatar(String nickname);
}
