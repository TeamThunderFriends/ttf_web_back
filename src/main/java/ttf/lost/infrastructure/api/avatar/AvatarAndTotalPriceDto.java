package ttf.lost.infrastructure.api.avatar;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarAndTotalPriceDto {
	private int totalPrice;
	private List<AvatarResponseDto> avatarList;
}
