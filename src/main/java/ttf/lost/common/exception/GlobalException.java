package ttf.lost.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String loggingErrorMessage;
	private Exception rootCause;

	public GlobalException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.loggingErrorMessage = "";
	}

	public GlobalException(ErrorCode errorCode, Exception rootCause) {
		this.errorCode = errorCode;
		this.loggingErrorMessage = "";
		this.rootCause = rootCause;
	}

	public GlobalException(ErrorCode errorCode, String loggingErrorMessage) {
		this.errorCode = errorCode;
		this.loggingErrorMessage = loggingErrorMessage;
	}

}
