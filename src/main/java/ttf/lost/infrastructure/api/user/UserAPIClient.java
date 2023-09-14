package ttf.lost.infrastructure.api.user;

import org.springframework.stereotype.Service;

@Service
public interface UserAPIClient {
	AuthParseDto authenticationCheck(Long urlNumber);
}
