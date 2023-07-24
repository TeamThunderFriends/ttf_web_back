package ttf.lost.infrastructure.api.avatar;

import java.util.ArrayList;
import java.util.List;

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

import lombok.RequiredArgsConstructor;
import ttf.lost.application.avatar.ClassNameMapConstant;
import ttf.lost.common.utils.LostArkAPIResponseParser;

@Service
@RequiredArgsConstructor
public class LostArkUserAvatarAPI implements AvatarAPIClient {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

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
		return LostArkAPIResponseParser.parseJson(body, AvatarDto.class, objectMapper);
	}

	@Override
	public List<AvatarAndPriceDto> findAvatarPrice(
		List<AvatarDto> apiList) throws JsonProcessingException {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<AvatarAndPriceDto> list = new ArrayList<>();
		for (int i = 0; i < apiList.size(); i++) {
			String clName = null;
			// Font 값 가져오기
			String classNameFont = apiList.get(i).getClassNameFont();
			// 가져온 Font 값 자르기 (블레이드 or 암살자계열)
			String classNameSubString = characterClassNameSubString(classNameFont);
			// 만약 '계열'이라면
			if (isCheckLine(classNameSubString)) {
				// ex) 암살자계열 -> 데모닉 (default) 으로 검색하게끔 처리
				clName = ClassNameMapConstant.isClassName(classNameSubString);
			}
			clName = ClassNameMapConstant.isClassName(classNameSubString);
			// ex) 페이튼 새벽빛 악기...
			String itemName = apiList.get(i).getName();
			String httpBody = "{\n"
				+ "  \"sort\": \"GRADE\",\n"
				+ "  \"categoryCode\": 20000,\n"
				+ "  \"characterClass\": \"" + clName + "\",\n"
				+ "  \"itemTier\": null,\n"
				+ "  \"itemGrade\": \"\",\n"
				+ "  \"itemName\": \"" + itemName + "\", \n"
				+ "  \"pageNo\": 0,\n"
				+ "  \"sortCondition\": \"ASC\"\n"
				+ "}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity request = new HttpEntity<>(httpBody, headers);

			String urlString = "https://developer-lostark.game.onstove.com/markets/items";
			UriComponents uri = UriComponentsBuilder.fromHttpUrl(urlString).build();
			ResponseEntity<String> result =
				restTemplate.exchange(uri.toUriString(), HttpMethod.POST, request, String.class);
			String body = result.getBody();

			JsonNode rootNode = objectMapper.readTree(body);
			Integer itemPrice;
			// 만약 body(API List size = 0)가 없다면, 가격의 정보가 없어 0 처리
			if (body.equals("{\"PageNo\":1,\"PageSize\":10,\"TotalCount\":0,\"Items\":[]}")) {
				itemPrice = 0;
			} else {
				itemPrice = rootNode.get("Items").get(0).get("CurrentMinPrice").asInt();
			}

			AvatarAndPriceDto build = AvatarAndPriceDto.builder()
				.type(apiList.get(i).getType())
				.name(apiList.get(i).getName())
				.icon(apiList.get(i).getIcon())
				.grade(apiList.get(i).getGrade())
				.isSet(apiList.get(i).getIsSet())
				.isInner(apiList.get(i).getIsInner())
				.avatarNameFont(apiList.get(i).getAvatarNameFont())
				.avatarGradeFont(apiList.get(i).getAvatarGradeFont())
				.classNameFont(apiList.get(i).getClassNameFont())
				.baseEffectFont1(apiList.get(i).getBaseEffectFont1())
				.baseEffectFont2(apiList.get(i).getBaseEffectFont2())
				.effect1(apiList.get(i).getEffect1())
				.effect2(apiList.get(i).getEffect2())
				.tendencyText1(apiList.get(i).getTendencyText1())
				.tendencyText2(apiList.get(i).getTendencyText2())
				.tendencyFont1(apiList.get(i).getTendencyFont1())
				.tendencyFont2(apiList.get(i).getTendencyFont2())
				.tooltip(apiList.get(i).getTooltip())
				.price(itemPrice != 0 ? itemPrice : null) // 0이 아니면 itemPrice, 0이면 null로 설정
				.build();
			list.add(build);
		}
		return list;
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

	public boolean isCheckLine(String className) {
		if (className.contains("계열")) {
			return true;
		}
		return false;
	}
}
