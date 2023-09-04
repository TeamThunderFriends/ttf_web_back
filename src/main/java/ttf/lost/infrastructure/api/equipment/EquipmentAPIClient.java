package ttf.lost.infrastructure.api.equipment;

import java.util.List;
public interface EquipmentAPIClient {
    /**
     * Lost Ark Open API에서 장비 정보 가져오기
     * @param characterName
     */
    List<EquipmentDto> getArmoryEquipment(String characterName);
}
