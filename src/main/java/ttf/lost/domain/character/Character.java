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
import ttf.lost.domain.accessory.Accessory;
import ttf.lost.domain.avatar.Avatar;
import ttf.lost.domain.equipment.Equipment;
import ttf.lost.domain.gem.Gem;
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
  private String itemAvgLevel; // 현재 아이템 레벨
  private String itemMaxLevel; // 최대 달성한 아이템 레벨

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<UserCharacter> userCharacters;

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<Avatar> avatars;

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<Gem> gems;

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<Accessory> accessories;

  @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
  private List<Equipment> equipment;
}
