package ttf.lost.application.user;

import ttf.lost.domain.user.User;

public interface SessionService {

	/**
	 * session 저장
	 * @param authorizedUserNo 세션에 저장 할 유저 번호
	 */
	void setUserSession(Long authorizedUserNo);

	User getUserSession();
}
