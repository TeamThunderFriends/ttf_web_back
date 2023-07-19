package ttf.lost.domain.accessory;

import java.math.BigDecimal;

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
import ttf.lost.domain.base.ItemGrade;
import ttf.lost.domain.character.GameCharacter;

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
	private Integer type;
	private Integer qualityValue;
	private Integer tier;
	private ItemGrade grade;
	private String element1; // 각인 1
	private String element2; // 각인 2
	private String additionEffect1; // 추가 효과 1
	private String additionEffect2; // 추가 효과 2
	private String tooltip;
	private BigDecimal price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "character_no")
	private GameCharacter gameCharacter;
}
