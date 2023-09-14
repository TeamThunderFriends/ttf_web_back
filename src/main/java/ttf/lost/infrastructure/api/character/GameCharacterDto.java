package ttf.lost.infrastructure.api.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GameCharacterDto {
	/*
	"ServerName": "카단",
    "CharacterName": "y2b0",
    "CharacterLevel": 53,
    "CharacterClassName": "소울이터",
    "ItemAvgLevel": "1,467.50",
    "ItemMaxLevel": "1,467.50"
	 */
	@JsonProperty("ServerName")
	private String serverName;
	@JsonProperty("CharacterName")
	private String characterName;
	@JsonProperty("CharacterLevel")
	private Integer characterLevel;
	@JsonProperty("CharacterClassName")
	private String characterClassName;
	@JsonProperty("ItemAvgLevel")
	private String itemAvgLevel;
	@JsonProperty("ItemMaxLevel")
	private String itemMaxLevel;
}
