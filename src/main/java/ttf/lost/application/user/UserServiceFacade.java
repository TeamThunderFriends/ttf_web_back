package ttf.lost.application.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.domain.user.User;

@Service
@RequiredArgsConstructor
public class UserServiceFacade {

	private final UserService userService;
	private final SessionService sessionService;

	public void login(User authorizedUser) {
		userService.login(authorizedUser);
		sessionService.setUserSession(authorizedUser.getUserNo());
	}

	public void join(User joinUser) {
		userService.join(joinUser);
	}
}
