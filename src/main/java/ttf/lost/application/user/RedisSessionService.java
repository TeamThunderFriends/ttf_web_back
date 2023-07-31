package ttf.lost.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.domain.user.User;
import ttf.lost.infrastructure.repository.user.UserRepository;

@Service
public class RedisSessionService implements SessionService {

	private final String USER_NO;
	private final HttpSession httpSession;
	private final UserRepository userRepository;

	@Autowired
	public RedisSessionService(@Value("${spring.redis.session.key}") String USER_NO, HttpSession httpSession,
		UserRepository userRepository) {
		this.USER_NO = USER_NO;
		this.httpSession = httpSession;
		this.userRepository = userRepository;
	}

	@Override
	public void setUserSession(Long authorizedUserNo) {
		httpSession.setAttribute(USER_NO, authorizedUserNo);
	}

	@Override
	public User getUserSession() {
		Long userNo = (Long)httpSession.getAttribute(USER_NO);
		if (userNo == null) {
			throw new GlobalException(ErrorCode.NOT_FOUND_USER);
		}

		return userRepository.findById(userNo).orElseThrow(
			() -> new GlobalException(ErrorCode.NOT_FOUND_USER, "not fount : " + userNo)
		);
	}

}
