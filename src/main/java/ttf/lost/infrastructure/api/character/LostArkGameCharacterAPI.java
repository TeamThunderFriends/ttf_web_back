package ttf.lost.infrastructure.api.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LostArkGameCharacterAPI implements GameCharacterAPIClient {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public List<GameCharacterDto> findCharacters(String nickname) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(headers);
		String urlString = "https://developer-lostark.game.onstove.com/characters/{value}/siblings";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(urlString).buildAndExpand(nickname);
		ResponseEntity<String> result =
			restTemplate.exchange(uri.toUriString(), HttpMethod.GET, request, String.class);
		String body = result.getBody();
		List<GameCharacterDto> resultList = new ArrayList<>();
		try {
			GameCharacterDto[] gameCharacterDto = objectMapper.readValue(body, GameCharacterDto[].class);
			resultList.addAll(Arrays.asList(gameCharacterDto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
