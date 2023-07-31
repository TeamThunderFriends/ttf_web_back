package ttf.lost.presentation.api.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ttf.lost.application.user.AuthService;
import ttf.lost.application.user.SessionService;
import ttf.lost.domain.user.User;
import ttf.lost.presentation.api.user.request.CharacterAuthenticationRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "character_authentication", description = "인증")
public class AuthController {

	private final AuthService authService;
	private final SessionService sessionService;

	@Operation(
		operationId = "generateAuthenticationNumber",
		summary = "인증 번호 생성 API 테스트",
		description = "인증 번호를 생성하고 인증 정보를 redis에 저장합니다.",
		tags = {"character_authentication"},
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input", ref = "sdasd"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
		}
	)
	@PostMapping("/auth/character")
	public ResponseEntity<String> generateAuthenticationNumber(
		@RequestBody @NotBlank @Length(max = 12, min = 2) String authNickName) {
		
		User userSession = sessionService.getUserSession();
		String authenticationNumber = authService.setAuthenticationNumberAndReturn(userSession.getUserId(),
			authNickName);

		return ResponseEntity.ok().body(authenticationNumber);
	}

	@Operation(
		operationId = "authenticationCheck",
		summary = "캐릭터 인증 API 테스트",
		description = "유저의 닉네임 정보와 게시글 번호를 입력 받아 해당 게시글의 제목의 인증번호와 redis에 저장된 인증번호를 비교해서 인증합니다.",
		tags = {"character_authentication"},
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
		}
	)
	@PostMapping("/auth/number")
	public ResponseEntity<Void> authenticationCheck(
		@RequestBody @Valid CharacterAuthenticationRequest characterAuthenticationRequest) {

		User userSession = sessionService.getUserSession();

		authService.authenticationCheck(userSession.getUserId(),
			characterAuthenticationRequest.authNickName(),
			characterAuthenticationRequest.boardNumber());

		return ResponseEntity.ok().build();
	}

}
