package ttf.lost.domain.equipment;

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
public class Equipment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "equipment_no")
  private Long equipmentNo;
  private String name;
  private String type;
  private Integer qualityValue;
  private Integer tier;
  private String iconPath;
  private String grade;
  private String characterClassName;
  private String baseEffect1; // 물방
  private String baseEffect2; // 마방
  private String baseEffect3;
  private String baseEffect4;
  private String additionEffect1;
  private String additionEffect2;
  private String additionEffect3;
  private String itemLevel;
  private String setEffect;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private User user;
}
