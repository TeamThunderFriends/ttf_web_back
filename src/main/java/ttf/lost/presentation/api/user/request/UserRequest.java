package ttf.lost.presentation.api.user.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import ttf.lost.domain.user.User;

@Getter
public class UserRequest {
	@NotEmpty(message = "아이디를 입력해주세요.")
	@Size(min = 5, max = 20, message = "아이디는 5~20자 영문 소문자 혹은 숫자로 구성되어야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 영문 소문자 혹은 숫자로만 구성되어야 합니다.")
	private String userId;

	@NotEmpty(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 16, message = "비밀번호는 8~16자 영문 대문자, 소문자, 숫자로 구성되어야 합니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "비밀번호는 영문 대문자, 소문자, 숫자가 포함되어야 합니다.")
	private String userPassword;

	public User toEntity() {
		return User.builder()
			.userId(this.userId)
			.userPassword(this.userPassword)
			.build();
	}

}
