package ttf.lost.application.avatar;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.infrastructure.api.avatar.AvatarAPIClient;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.infrastructure.api.avatar.AvatarSaveDto;
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
		List<AvatarDto> apiList, String nickname) throws JsonProcessingException {
		List<AvatarAndPriceDto> avatarPrice = avatarAPIClient.findAvatarPrice(apiList);
		// DB 저장
		for (int i = 0; i < avatarPrice.size(); i++) {
			String name = avatarPrice.get(i).getName();
			String classNameFont = avatarPrice.get(i).getClassNameFont();
			String className = LostArkUserAvatarAPI
				.characterClassNameSubString(classNameFont);
			String tooltip = avatarPrice.get(i).getTooltip();
			AvatarSaveDto build = AvatarSaveDto.builder()
				.name(name)
				.className(className)
				.tooltip(tooltip)
				// .gameCharacter() 나중에 GameCharacter 로직 생기면 수정
				.build();
			Avatar avatar = build.toEntity();
			avatarRepository.save(avatar);
		}
		return avatarPrice;
	}
}
