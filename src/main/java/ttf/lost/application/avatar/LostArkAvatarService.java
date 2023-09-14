package ttf.lost.application.avatar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import ttf.lost.application.character.GameCharacterService;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.domain.recommendation.AvatarInquiry;
import ttf.lost.infrastructure.api.avatar.AvatarAPIClient;
import ttf.lost.infrastructure.api.avatar.AvatarAndPriceDto;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.infrastructure.api.avatar.LostArkUserAvatarAPI;
import ttf.lost.infrastructure.api.character.GameCharacterDto;
import ttf.lost.infrastructure.repository.AvatarInquiryRepository;
import ttf.lost.infrastructure.repository.avatar.AvatarRepository;
import ttf.lost.presentation.api.avatar.response.AvatarAndTotalPriceResponse;
import ttf.lost.presentation.api.avatar.response.AvatarResponse;

@Service
@RequiredArgsConstructor
public class LostArkAvatarService implements AvatarService {
	private final AvatarAPIClient avatarAPIClient;
	private final AvatarRepository avatarRepository;
	private final GameCharacterService gameCharacterService;
	private final AvatarInquiryRepository avatarInquiryRepository;

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

	@Override
	@Transactional
	public AvatarAndTotalPriceResponse avatarTotalPriceAndInfo(
		List<AvatarAndPriceDto> avatarAndPriceDtoList, String nickname
	) {
		// List를 순회하면서 ResponseDtoList에 다시 값들 넣어주기.
		List<AvatarResponse> responseDtoList = new ArrayList<>();
		String classNameString = null;
		convertAvatarDto(avatarAndPriceDtoList, responseDtoList);
		// todo : 아바타를 낀 className 을 찾기
		for (int i = 0; i < responseDtoList.size(); i++) {
			String classNameFont = responseDtoList.get(0).getClassNameFont();
			if (classNameFont.contains("전용")) {
				classNameString = classNameSubString(classNameFont);
				break;
			}
		}
		// 만약에 className이 ex) "<FONT SIZE='12'>건슬링어 전용</FONT>" 이 아닐(없을) 경우
		// -> 해당 캐릭터 간단 정보 API 쏘기
		if (classNameString == null) {
			List<GameCharacterDto> userGameCharacters = gameCharacterService.findUserGameCharacters(nickname);
			// for 문을 돌면서 parameter 와 같은 닉네임을 가진 캐릭터 찾기
			for (int i = 0; i < userGameCharacters.size(); i++) {
				String characterName = userGameCharacters.get(i).getCharacterName();
				// 같은 값을 찾았다면 초기화 진행
				if (characterName.equals(nickname)) {
					classNameString = userGameCharacters.get(i).getCharacterClassName();
				}
			}
		}
		// className 를 맞는 클래스 찾아서 삽입
		List<AvatarInquiry> byUserNickname = avatarInquiryRepository
			.findByUserNickname(nickname);
		if (byUserNickname.size() == 0) {
			for (int i = 0; i < responseDtoList.size(); i++) {
				AvatarInquiry build = AvatarInquiry.builder()
					.userNickname(nickname)
					.name(responseDtoList.get(i).getName())
					.characterClass(classNameString)
					.grade(responseDtoList.get(i).getGrade())
					.part(responseDtoList.get(i).getType())
					.build();
				avatarInquiryRepository.save(build);
			}
		}
		// Data 있을 경우 이름 변경
		for (int i = 0; i < byUserNickname.size(); i++) {
			byUserNickname.get(i).setUserNickname(nickname);
			byUserNickname.get(i).setName(responseDtoList.get(i).getName());
		}
		// ResponseDtoList 를 순회하면서 각 Price들을 가져와 더해주기.
		int totalPrice = responseDtoList.stream()
			.mapToInt(responseDto -> responseDto.getPrice() != null ? responseDto.getPrice() : 0)
			.sum();
		// 더해준 값들을 total값에 넣어주기.
		// TotalPriceAndAvatarListDto 객체 생성하여 반환하기.
		return new AvatarAndTotalPriceResponse(totalPrice, responseDtoList);
	}

	private void convertAvatarDto(List<AvatarAndPriceDto> avatarAndPriceDtoList,
		List<AvatarResponse> responseDtoList) {
		for (AvatarAndPriceDto dto : avatarAndPriceDtoList) {
			AvatarResponse responseDto = AvatarResponse.builder()
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

	private String classNameSubString(String input) {
		int startIndex = input.indexOf("<FONT SIZE='12'>");
		int endIndex = input.indexOf(" 전용</FONT>");

		if (startIndex != -1 && endIndex != -1) {
			startIndex += "<FONT SIZE='12'>".length(); // 시작 태그 이후부터 추출
			return input.substring(startIndex, endIndex);
		} else {
			// 태그를 찾을 수 없는 경우 처리
			return "태그를 찾을 수 없습니다.";
		}
	}
}
