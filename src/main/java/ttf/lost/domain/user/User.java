package ttf.lost.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
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
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_no")
  private Long userNo;
  @Column(unique = true)
  private String userId;
  private String userPassword;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Avatar> avatars;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Accessory> accessories;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<UserCharacter> userCharacters;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Gem> gems;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Equipment> equipment;
}
