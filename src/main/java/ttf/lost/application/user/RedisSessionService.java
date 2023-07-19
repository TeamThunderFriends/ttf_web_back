package ttf.lost.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class RedisSessionService implements SessionService {

	private final String USER_NO;
	private final HttpSession httpSession;

	@Autowired
	public RedisSessionService(@Value("${spring.redis.session.key}") String USER_NO, HttpSession httpSession) {
		this.USER_NO = USER_NO;
		this.httpSession = httpSession;
	}

	@Override
	public void setUserSession(Long authorizedUserNo) {
		httpSession.setAttribute(USER_NO, authorizedUserNo);
	}

}
