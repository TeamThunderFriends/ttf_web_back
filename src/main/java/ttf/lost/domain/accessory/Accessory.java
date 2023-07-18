package ttf.lost.domain.accessory;

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
public class Accessory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "accessory_no")
  private Long accessoryNo;
  private String name;
  private String type;
  private Integer quantityValue;
  private Integer tier;
  private String iconPath;
  private String grade;
  private String element1;
  private String element2;
  private String element3;
  private String baseEffect1;
  private String baseEffect2;
  private String additionEffect1;
  private String additionEffect2;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private User user;
}
