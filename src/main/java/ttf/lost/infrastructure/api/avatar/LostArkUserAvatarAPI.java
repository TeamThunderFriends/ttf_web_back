package ttf.lost.infrastructure.api.avatar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ttf.lost.application.avatar.ClassNameMapConstant;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.common.utils.LostArkAPIResponseParser;

@Service
@RequiredArgsConstructor
public class LostArkUserAvatarAPI implements AvatarAPIClient {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	@Value("${lost-ark.market-api}")
	private String LOST_ARK_MARKET_API;

	@Override
	public List<AvatarDto> findAvatar(String nickname) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);

		String urlString = "https://developer-lostark.game.onstove.com/armories/characters/{value}?filters=avatars";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(urlString).buildAndExpand(nickname);
		ResponseEntity<String> result =
			restTemplate.exchange(uri.toUriString(), HttpMethod.GET, request, String.class);
		String body = result.getBody();
		return LostArkAPIResponseParser.parseJsonArray(body, AvatarDto.class, objectMapper);
	}

	@Override
	public List<AvatarAndPriceDto> findAvatarPrice(List<AvatarDto> apiList) {

		List<AvatarAndPriceDto> list = new ArrayList<>();
		final String uri = UriComponentsBuilder.fromHttpUrl(LOST_ARK_MARKET_API).build().toUriString();

		for (AvatarDto avatarDto : apiList) {
			// Font 값 가져오기
			String classNameFont = avatarDto.getClassNameFont();
			// 가져온 Font 값 자르기 (블레이드 or 암살자계열)
			String classNameSubString = characterClassNameSubString(classNameFont);
			// 만약 '계열'이라면
			String className = ClassNameMapConstant.isClassName(classNameSubString);
			// ex) 페이튼 새벽빛 악기...
			String itemName = avatarDto.getName();

			ResponseEntity<String> result = getHttpResult(uri, className, itemName);

			String body = result.getBody();

			JsonNode rootNode;
			try {
				rootNode = objectMapper.readTree(body);
			} catch (JsonProcessingException e) {
				throw new GlobalException(ErrorCode.NOT_JSON_TYPE, e);
			}

			Integer itemPrice = null;
			// 만약 body(API List size = 0)가 없다면, 가격의 정보가 없어 0 처리
			if (rootNode.get("TotalCount").asInt() > 0) {
				itemPrice = rootNode.get("Items").get(0).get("CurrentMinPrice").asInt();
			}
			AvatarAndPriceDto avatarAndPriceDto = makeAvatarAndPriceDto(avatarDto, itemPrice);
			list.add(avatarAndPriceDto);
		}
		return list;
	}

	private ResponseEntity<String> getHttpResult(String uri, String className, String itemName) {
		HttpEntity<MarketSearchDto> request = makeHttpRequestOption(className, itemName);
		return restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	}

	private AvatarAndPriceDto makeAvatarAndPriceDto(AvatarDto avatarDto, Integer itemPrice) {
		return AvatarAndPriceDto.builder()
			.type(avatarDto.getType())
			.name(avatarDto.getName())
			.icon(avatarDto.getIcon())
			.grade(avatarDto.getGrade())
			.isSet(avatarDto.getIsSet())
			.isInner(avatarDto.getIsInner())
			.avatarNameFont(avatarDto.getAvatarNameFont())
			.avatarGradeFont(avatarDto.getAvatarGradeFont())
			.classNameFont(avatarDto.getClassNameFont())
			.baseEffectFont1(avatarDto.getBaseEffectFont1())
			.baseEffectFont2(avatarDto.getBaseEffectFont2())
			.effect1(avatarDto.getEffect1())
			.effect2(avatarDto.getEffect2())
			.tendencyText1(avatarDto.getTendencyText1())
			.tendencyText2(avatarDto.getTendencyText2())
			.tendencyFont1(avatarDto.getTendencyFont1())
			.tendencyFont2(avatarDto.getTendencyFont2())
			.tooltip(avatarDto.getTooltip())
			.price(itemPrice) // 0이 아니면 itemPrice, 0이면 null로 설정
			.build();
	}

	private HttpEntity<MarketSearchDto> makeHttpRequestOption(String className, String itemName) {
		MarketSearchDto httpBody = new MarketSearchDto(className, itemName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(httpBody, headers);
	}

	public static String characterClassNameSubString(String characterClassName) {
		int startIdx = characterClassName.indexOf('>') + 1; // '>' 다음 인덱스부터 시작
		int endIdx = characterClassName.indexOf("</FONT>", startIdx); // '</FONT>' 직전까지의 인덱스
		String text = characterClassName.substring(startIdx, endIdx);
		// " 전용"이라는 문자열이 포함되어 있다면, 제거한다.
		int exclusiveIdx = text.indexOf(" 전용");
		if (exclusiveIdx != -1) {
			text = text.substring(0, exclusiveIdx);
		}
		return text;
	}

	@Getter
	static class MarketSearchDto {
		private final String sort;
		private final Integer categoryCode;
		private final String characterClass;
		private final Integer itemTier;
		private final String itemGrade;
		private final String itemName;
		private final Integer pageNo;
		private final String sortCondition;

		public MarketSearchDto(String characterClass, String itemName) {
			this.sort = "GRADE";
			this.categoryCode = 20000;
			this.characterClass = characterClass;
			this.itemTier = null;
			this.itemGrade = "";
			this.itemName = itemName;
			this.pageNo = 0;
			this.sortCondition = "ASC";
		}
	}
}