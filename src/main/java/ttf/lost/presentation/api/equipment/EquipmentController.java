package ttf.lost.presentation.api.equipment;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttf.lost.application.equipment.EquipmentServiceFacade;
import ttf.lost.infrastructure.api.equipment.EquipmentDto;
import ttf.lost.infrastructure.api.equipment.LostArkUserEquipmentAPI;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipment")
@Tag(name = "get_user_armory_equipment", description = "유저 장비와 장비의 가치(골드) 가져오기")
public class EquipmentController {
    private final LostArkUserEquipmentAPI api;
    private final EquipmentServiceFacade equipmentServiceFacade;

    @GetMapping("/test/{characterName}")
    public List<EquipmentDto> getTest(@PathVariable String characterName) throws JsonProcessingException {
        return api.getArmoryEquipment(characterName);
    }
}
