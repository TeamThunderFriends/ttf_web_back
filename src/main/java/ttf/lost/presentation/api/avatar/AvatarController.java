package ttf.lost.presentation.api.avatar;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ttf.lost.application.avatar.AvatarServiceFacade;
import ttf.lost.infrastructure.api.avatar.AvatarDto;
import ttf.lost.infrastructure.api.avatar.LostArkUserAvatarAPI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avatar")
@Tag(name = "find_user_avatar", description = "유저 아바타와 가격 가져오기")
public class AvatarController {
	private final LostArkUserAvatarAPI apiTest;
	private final AvatarServiceFacade avatarServiceFacade;

	/**
	 * 임시 DTO 변환 확인 테스트이기에
	 * 별도로 Controller를 따로 만들어 테스트를 진행하였습니다.
	 */
	@GetMapping("/test/{nickname}")
	public List<AvatarDto> getTest(
		@PathVariable String nickname
	) throws JsonProcessingException {
		return apiTest.findAvatar(nickname);
	}

	@Operation(
		operationId = "find_user_avatar",
		summary = "findUserAvatar API 테스트",
		description = "유저의 정보(닉네임)을 가져와서 착용 중인 캐릭터의 아바타 정보를 가져옵니다.",
		tags = {"find_user_avatar"},
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
		}
	)
	@GetMapping("/{nickname}")
	public ResponseEntity<Object> findUserAvatar(
		@PathVariable String nickname) {
		avatarServiceFacade.findUserAvatarAndPrice(nickname);
		return null;
	}
}
