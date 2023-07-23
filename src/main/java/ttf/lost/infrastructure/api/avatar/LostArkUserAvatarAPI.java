package ttf.lost.infrastructure.api.avatar;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
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
		headers.set("Authorization",
			"bearer {OPEN_API_KEY}"); // Open API Key 부분은 올리지 않고, yml 처리도 아직 되지 않았기에 따로 문자열로 선언합니다.

		HttpEntity<?> request = new HttpEntity<>(headers);

		String urlString = "https://developer-lostark.game.onstove.com/armories/characters/{value}?filters=avatars";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(urlString).buildAndExpand(nickname);

		ResponseEntity<String> result =
			restTemplate.exchange(uri.toUriString(), HttpMethod.GET, request, String.class);
		String body = result.getBody();
		return LostArkAPIResponseParser.parseJson(body, AvatarDto.class, objectMapper);
	}
}
