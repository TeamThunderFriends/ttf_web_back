package ttf.lost.application.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ttf.lost.common.exception.GlobalException;
import ttf.lost.domain.user.User;
import ttf.lost.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class LostArkUserService implements UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Override
	public void login(User authorizedUser) {
		User findUser = userRepository.findByUserId(authorizedUser.getUserId()).orElseThrow(GlobalException::new);

		if (!passwordEncoder.matches(authorizedUser.getUserPassword(), findUser.getUserPassword())) {
			throw new GlobalException();
		}
	}

	@Override
	public void join(User joinUser) {
		User findUser = userRepository.findByUserId(joinUser.getUserId()).orElse(null);

		if (findUser != null) { // 회원이 있는경우
			throw new GlobalException();
		}

		joinUser.setEncryptionPassword(passwordEncoder.encode(joinUser.getUserPassword()));

		try {
			userRepository.save(joinUser);
		} catch (DuplicateKeyException e) { // 같은아이디가 있는경우
			throw new GlobalException();
		}
	}

}
