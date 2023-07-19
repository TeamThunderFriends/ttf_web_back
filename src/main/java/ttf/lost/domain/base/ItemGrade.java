package ttf.lost.domain.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemGrade {
  일반("#b8b8b8"),
  고급("#91fe02"),
  희귀("#00b5ff"),
  영웅("#bf00fe"),
  전설("#f39303"),
  유물("#ff6000"),
  고대("#c9a472"),
  에스더("#3cf2e6");

  private final String fontColor;
}
