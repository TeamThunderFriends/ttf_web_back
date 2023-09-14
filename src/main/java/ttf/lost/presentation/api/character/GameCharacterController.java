package ttf.lost.presentation.api.character;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ttf.lost.application.character.GameCharacterService;
import ttf.lost.infrastructure.api.character.GameCharacterDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gameCharacter")
@Tag(name = "find_user_game_character", description = "유저 인게임 캐릭터 정보 가져오기")
public class GameCharacterController {
	private final GameCharacterService gameCharacterService;

	@Operation(
		operationId = "find_user_game_character",
		summary = "findUserGameCharacter API 테스트",
		description = "유저의 정보(닉네임)을 가져와서 유저 원정대의 캐릭터 정보들을 가져옵니다.",
		tags = {"find_user_game_character"},
		responses = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
			}),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
		}
	)
	@GetMapping("/{nickname}")
	public ResponseEntity<List<GameCharacterDto>> findUserGameCharacters(
		@PathVariable String nickname) {
		List<GameCharacterDto> userGameCharacters = gameCharacterService.findUserGameCharacters(nickname);
		return ResponseEntity.ok(userGameCharacters);
	}
}
