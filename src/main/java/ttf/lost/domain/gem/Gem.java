package ttf.lost.domain.gem;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gem_no")
  private Long gemNo;

  private String name;
  private String description;
  private String iconPath;
  private Integer tier;
  private String grade;
  private Integer level;
  private String characterClassName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private User user;
}
