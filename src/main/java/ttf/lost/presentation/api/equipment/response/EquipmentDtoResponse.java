package ttf.lost.presentation.api.equipment.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDtoResponse {
    private String name;
    private String tooltip;
    private BigDecimal price;
}
