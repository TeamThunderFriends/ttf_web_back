package ttf.lost.application.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttf.lost.presentation.api.equipment.response.EquipmentDtoResponse;

@Service
@RequiredArgsConstructor
public class EquipmentServiceFacade {
    private final EquipmentService equipmentService;

    public EquipmentDtoResponse getArmoryEquipmentAndPrice(String characterName) {
        // TODO : Open API 호출 후 Dto에 저장
        equipmentService.getArmoryEquipment(characterName);

        // TODO : Repository에 정보 및 가격 저장
        // TODO : 가격을 모두 더해주기
        return null;
    }
}
