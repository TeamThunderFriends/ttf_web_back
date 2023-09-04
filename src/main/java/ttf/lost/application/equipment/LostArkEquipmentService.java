package ttf.lost.application.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttf.lost.domain.equipment.Equipment;
import ttf.lost.infrastructure.api.equipment.EquipmentAPIClient;

@Service
@RequiredArgsConstructor
public class LostArkEquipmentService implements EquipmentService{
    private final EquipmentAPIClient equipmentAPIClient;

    @Override
    public Equipment getArmoryEquipment(String characterName) {
        equipmentAPIClient.getArmoryEquipment(characterName);
        return null;
    }
}
