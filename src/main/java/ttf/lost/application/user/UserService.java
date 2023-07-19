package ttf.lost.application.user;

import ttf.lost.domain.user.User;

public interface UserService {

	/**
	 * 로그인
	 * @param authorizedUser 로그인 유저
	 */
	void login(User authorizedUser);

	/**
	 * 뢰원가입
	 * @param joinUser 회원가입 유저
	 */
	void join(User joinUser);
}
