package ttf.lost.infrastructure.api.user;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;

@Service
@Slf4j
public class LostArkUserAPIClient implements UserAPIClient {

	private final String LOSTARK_AUTHENTICATION_API;

	@Autowired
	public LostArkUserAPIClient(@Value("${lost-ark.user-auth-host}") String LOSTARK_AUTHENTICATION_API) {
		this.LOSTARK_AUTHENTICATION_API = LOSTARK_AUTHENTICATION_API;
	}

	/**
	 * 로스트아크 게시글 퍼블리싱 태그가 변경 될시 코드 수정이 필요함
	 * @param urlNumber
	 * @return
	 */
	@Override
	public AuthParseDto authenticationCheck(Long urlNumber) {
		String url = UriComponentsBuilder.fromHttpUrl(LOSTARK_AUTHENTICATION_API)
			.buildAndExpand(urlNumber)
			.toUriString();
		try {
			Document doc = Jsoup.connect(url).get();
			Element authorDiv = doc.selectFirst("div.article__author");
			Element titleElement = doc.selectFirst("span.article__title");
			Element nameSpan = authorDiv.selectFirst("span.character-info__name");
			String parseAuthenticationNumber = titleElement.text();
			String parseUsername = nameSpan.attr("title");
			return new AuthParseDto(parseUsername, parseAuthenticationNumber);
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INVALID_POST_NUMBER, e, url);
		}
	}

}
