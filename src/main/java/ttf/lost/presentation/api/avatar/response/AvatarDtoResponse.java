package ttf.lost.presentation.api.avatar.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarDtoResponse {
	private String name;
	private String tooltip;
	private BigDecimal price;
}
