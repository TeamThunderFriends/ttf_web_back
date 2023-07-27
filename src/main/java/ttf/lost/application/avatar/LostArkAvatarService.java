package ttf.lost.application.avatar;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.infrastructure.api.avatar.AvatarAPIClient;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.infrastructure.api.avatar.LostArkUserAvatarAPI;
import ttf.lost.infrastructure.repository.avatar.AvatarRepository;

@Service
@RequiredArgsConstructor
public class LostArkAvatarService implements AvatarService {
	private final AvatarAPIClient avatarAPIClient;
	private final AvatarRepository avatarRepository;

	@Override
	public List<AvatarDto> findUserAvatar(String nickname) {
		return avatarAPIClient.findAvatar(nickname);
	}

	@Override
	@Transactional
	public List<AvatarAndPriceDto> avatarPriceSave(
		List<AvatarDto> apiList, String nickname) {
		List<AvatarAndPriceDto> avatarPrice = null;
		try {
			avatarPrice = avatarAPIClient.findAvatarPrice(apiList);
		} catch (JsonProcessingException e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e);
		}
		// DB 저장
		for (AvatarAndPriceDto avatar : avatarPrice) {
			String name = avatar.getName();
			String classNameFont = avatar.getClassNameFont();
			String className = LostArkUserAvatarAPI
				.characterClassNameSubString(classNameFont);
			String tooltip = avatar.getTooltip();
			Avatar build = Avatar.builder()
				.name(name)
				.characterClass(className)
				.tooltip(tooltip)
				// .gameCharacter() 나중에 GameCharacter 로직 생기면 수정
				.build();
			avatarRepository.save(build);
		}
		return avatarPrice;
	}
}
