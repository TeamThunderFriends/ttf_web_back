package ttf.lost.infrastructure.api.avatar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.domain.character.GameCharacter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarSaveDto {
	private String name;
	private String className;
	private String tooltip;
	private Integer price;

	private GameCharacter gameCharacter;

	public Avatar toEntity() {
		return Avatar.builder()
			.name(this.name)
			.characterClass(this.className)
			.tooltip(this.tooltip)
			.price(this.price)
			.gameCharacter(this.gameCharacter)
			.build();
	}
}
