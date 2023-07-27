package ttf.lost.application.user;

import static ttf.lost.common.exception.ErrorCode.*;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.common.utils.RedisKeyUtils;
import ttf.lost.infrastructure.api.user.AuthParseDto;
import ttf.lost.infrastructure.api.user.UserAPIClient;
import ttf.lost.infrastructure.repository.user.AuthenticationRepository;

@Service
@RequiredArgsConstructor
public class LostArkAuthService implements AuthService {

	private final UserAPIClient userAPIClient;
	private final AuthenticationRepository authenticationRepository;

	@Override
	public String setAuthenticationNumberAndReturn(String userId, String characterName) throws GlobalException {
		String userKey = RedisKeyUtils.authenticationKey(userId, characterName);

		//이미 받은 인증 번호가 존재하는지 체크
		if (authenticationRepository.hasKey(userKey)) {
			throw new GlobalException(AUTH_REQUEST_LIMIT_EXCEEDED);
		}

		String authenticationNumber = UUID.randomUUID().toString().split("-")[0];

		//인증 번호 redis 저장
		authenticationRepository.saveAuthenticationNumberEachUser(userKey, authenticationNumber);
		return authenticationNumber;
	}

	@Override
	public void authenticationCheck(String userId, String characterNickname, Long urlNumber) {
		String findAuthNumber = getAuthenticationNumber(RedisKeyUtils.authenticationKey(userId, characterNickname));
		AuthParseDto authParseDto = userAPIClient.authenticationCheck(urlNumber);
		if (isCheckAuthentication(characterNickname, findAuthNumber, authParseDto)) {
			throw new GlobalException(INVALID_AUTHENTICATION_NUMBER,
				"요청 id : %s 닉네임 : %s".formatted(userId, characterNickname));
		}
	}

	private boolean isCheckAuthentication(String characterNickname, String findAuthNumber,
		AuthParseDto authParseDto) {
		return !findAuthNumber.equals(authParseDto.authenticationNumber()) ||
			!characterNickname.equals(authParseDto.characterNickname());
	}

	private String getAuthenticationNumber(String userKey) {
		if (!authenticationRepository.hasKey(userKey)) {
			throw new GlobalException(AUTH_REQUIRED, userKey);
		}
		return authenticationRepository.getAuthenticationNumber(userKey);
	}

}
