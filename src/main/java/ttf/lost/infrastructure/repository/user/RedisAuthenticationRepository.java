package ttf.lost.infrastructure.repository.user;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisAuthenticationRepository implements AuthenticationRepository {

	private final RedisTemplate<String, String> redisTemplate;
	private final Long EXPIRE_TIME;

	@Autowired
	public RedisAuthenticationRepository(RedisTemplate<String, String> redisTemplate,
		@Value("${policy.character-authentication-sec}") Long EXPIRE_TIME) {
		this.redisTemplate = redisTemplate;
		this.EXPIRE_TIME = EXPIRE_TIME;
	}

	@Override
	public void saveAuthenticationNumberEachUser(String userKey, String AuthenticationNumber) {
		redisTemplate.opsForValue().set(userKey, AuthenticationNumber, EXPIRE_TIME, TimeUnit.SECONDS);
	}

	@Override
	public Boolean hasKey(String userKey) {
		return redisTemplate.hasKey(userKey);
	}

	@Override
	public String getAuthenticationNumber(String userKey) {
		return redisTemplate.opsForValue().get(userKey);
	}

	@Override
	public Long getRemainingAuthenticationTime(String userKey) {
		return redisTemplate.getExpire(userKey, TimeUnit.SECONDS);
	}
}
