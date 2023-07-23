package ttf.lost.infrastructure.repository.user;

public interface AuthenticationRepository {
	void saveAuthenticationNumberEachUser(String userKey, String AuthenticationNumber);

	Boolean hasKey(String userKey);

	String getAuthenticationNumber(String userKey);

	Long getRemainingAuthenticationTime(String userKey);
}
