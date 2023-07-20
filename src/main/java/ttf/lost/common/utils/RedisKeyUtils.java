package ttf.lost.common.utils;

public class RedisKeyUtils {
	public static String authenticationKey(String userId, String characterNickname) {
		return "%s:%s".formatted(userId, characterNickname);
	}
}
