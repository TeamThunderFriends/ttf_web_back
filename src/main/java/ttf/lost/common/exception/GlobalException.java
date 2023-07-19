package ttf.lost.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String loggingErrorMessage;

	public GlobalException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.loggingErrorMessage = "";
	}
}
