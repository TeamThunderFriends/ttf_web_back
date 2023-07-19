package ttf.lost.presentation.api.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ttf.lost.application.user.UserServiceFacade;
import ttf.lost.presentation.api.user.request.UserRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "login/join", description = "로그인, 회원가입")
public class LoginController {

	private final UserServiceFacade userServiceFacade;
	@Operation(
		operationId = "login",
		summary = "login API 테스트",
		description = "유저의 아이디와 비밀번호를 가져와서 로그인합니다.",
		tags = { "login/join" },
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
		}
	)
	@PostMapping("/login")
	public void login(@RequestBody UserRequest userRequest) {
		userServiceFacade.login(userRequest.toEntity());
	}


	@Operation(
		operationId = "join",
		summary = "join API 테스트",
		description = "유저의 아이디와 비밀번호를 가져와서 회원가입합니다.",
		tags = { "login/join" },
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
		}
	)
	@PostMapping("/join")
	public void join(@RequestBody UserRequest userRequest) {
		userServiceFacade.join(userRequest.toEntity());
	}

}
