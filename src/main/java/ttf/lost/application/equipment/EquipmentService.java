package ttf.lost.application.equipment;

import ttf.lost.domain.equipment.Equipment;

public interface EquipmentService {
    Equipment getArmoryEquipment(String characterName);
}
