package ttf.lost.presentation.api.avatar.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvatarAndTotalPriceResponse {
	private int totalPrice;
	private List<AvatarResponse> avatarList;
}
