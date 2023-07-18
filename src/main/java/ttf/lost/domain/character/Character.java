package ttf.lost.domain.character;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ttf.lost.domain.usercharacter.UserCharacter;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "character_no")
  private Long characterNo;

  private String characterName;
  private Integer characterLevel;
  private String characterClassName;
  private String serverName;
  private String itemAvgLevel;
  private String itemMaxLevel;

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<UserCharacter> userCharacters;
}
