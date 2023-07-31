package ttf.lost.application.user;

import ttf.lost.common.exception.GlobalException;

public interface AuthService {
	String setAuthenticationNumberAndReturn(String userId, String characterName) throws GlobalException;

	void authenticationCheck(String userId, String characterNickname, Long urlNumber);
}
