package ttf.lost.presentation.api.user.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterAuthenticationRequest(@NotBlank @Length(max = 12, min = 2) String authNickName,
											 @Digits(integer = 20, fraction = 0) @NotNull Long boardNumber) {

}
