package ttf.lost.domain.avatar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ttf.lost.domain.user.User;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access =AccessLevel.PROTECTED)
public class Avatar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "avatar_no")
  private Long avatarNo;
  private String baseEffect;
  private String additionEffect1;
  private String additionEffect2;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private User user;
}
