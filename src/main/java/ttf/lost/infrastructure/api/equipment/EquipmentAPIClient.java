package ttf.lost.infrastructure.api.equipment;

import java.util.List;
public interface EquipmentAPIClient {
    /**
     * Lost Ark Open API에서 장비 정보 가져오기
     * @param characterName
     */
    List<EquipmentDto> getArmoryEquipment(String characterName);

    /**
     * Lost Ark Open API Markets 에서 각 강화 재료의 가격과 비용을 합산하여 가져오기
     *
     */
}
