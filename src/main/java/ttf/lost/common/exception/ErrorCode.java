package ttf.lost.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INVALID_REQUEST("잘못된 요청입니다.", "1001", HttpStatus.NOT_FOUND),
	INTERNAL_SERVER_ERROR("서버 오류 입니다.", "1002", HttpStatus.INTERNAL_SERVER_ERROR),
	ALREADY_USER_ID("중복된 아이디 입니다.", "1003", HttpStatus.BAD_REQUEST),
	INVALID_PARAMETER("잘못된 파라미터 값입니다.", "1004", HttpStatus.BAD_REQUEST),
	NOT_LOGIN("로그인이 되어 있지 않습니다.", "1005", HttpStatus.UNAUTHORIZED),
	NOT_FOUND_USER("유저를 찾을 수 없습니다.", ":1006", HttpStatus.BAD_REQUEST),
	NOT_SAME_PASSWORD("잘못된 비밀번호 입니다.", "1007", HttpStatus.BAD_REQUEST);

	private final String errorMessage;
	private final String errorNumber;
	private final HttpStatus httpStatus;
}