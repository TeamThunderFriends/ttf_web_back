package ttf.lost.application.avatar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.infrastructure.api.avatar.AvatarAPIClient;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarAndTotalPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.infrastructure.api.avatar.AvatarResponseDto;
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
			Integer price = avatarPrice.get(i).getPrice();
			String className = LostArkUserAvatarAPI
				.characterClassNameSubString(classNameFont);
			String tooltip = avatarPrice.get(i).getTooltip();
			AvatarSaveDto build = AvatarSaveDto.builder()
				.name(name)
				.className(className)
				.tooltip(tooltip)
				// .gameCharacter() 나중에 GameCharacter 로직 생기면 수정
				.price(price)
				.build();
			Avatar avatar = build.toEntity();
			avatarRepository.save(avatar);
		}
		return avatarPrice;
	}

	@Override
	public AvatarAndTotalPriceDto avatarTotalPriceAndInfo(
		List<AvatarAndPriceDto> avatarAndPriceDtoList) throws JsonProcessingException {
		// List를 순회하면서 ResponseDtoList에 다시 값들 넣어주기.
		List<AvatarResponseDto> responseDtoList = new ArrayList<>();
		convertAvatarDto(avatarAndPriceDtoList, responseDtoList);
		// ResponseDtoList 를 순회하면서 각 Price들을 가져와 더해주기.
		int totalPrice = responseDtoList.stream()
			.mapToInt(responseDto -> responseDto.getPrice() != null ? responseDto.getPrice() : 0)
			.sum();
		// 더해준 값들을 total값에 넣어주기.
		// TotalPriceAndAvatarListDto 객체 생성하여 반환하기.
		return new AvatarAndTotalPriceDto(totalPrice, responseDtoList);
	}

	private void convertAvatarDto(List<AvatarAndPriceDto> avatarAndPriceDtoList,
		List<AvatarResponseDto> responseDtoList) {
		for (AvatarAndPriceDto dto : avatarAndPriceDtoList) {
			AvatarResponseDto responseDto = AvatarResponseDto.builder()
				.type(dto.getType())
				.name(dto.getName())
				.icon(dto.getIcon())
				.grade(dto.getGrade())
				.isSet(dto.getIsSet())
				.isInner(dto.getIsInner())
				.avatarNameFont(dto.getAvatarNameFont())
				.avatarGradeFont(dto.getAvatarGradeFont())
				.classNameFont(dto.getClassNameFont())
				.baseEffectFont1(dto.getBaseEffectFont1())
				.baseEffectFont2(dto.getBaseEffectFont2())
				.effect1(dto.getEffect1())
				.effect2(dto.getEffect2())
				.tendencyText1(dto.getTendencyText1())
				.tendencyText2(dto.getTendencyText2())
				.tendencyFont1(dto.getTendencyFont1())
				.tendencyFont2(dto.getTendencyFont2())
				.price(dto.getPrice())
				.build();
			responseDtoList.add(responseDto);
		}
	}
}
