package ttf.lost.domain.equipment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ttf.lost.domain.base.ItemGrade;
import ttf.lost.domain.character.Character;

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
  private String tooltip;
  private BigDecimal price;
  private String type;
  private Integer upgradeLevel; // 강화 수치
  private Integer itemLevel; // 아이템 레벨
  private ItemGrade itemGrade; // 등급
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private Character character;
}
