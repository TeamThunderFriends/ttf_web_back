package ttf.lost.infrastructure.api.equipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ttf.lost.common.annotation.JsonPaths;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EquipmentDto {
    /*"투구"*/
    @JsonPaths("Type")
    private String type;

    /*"+23 전이된 몽환의 악몽 머리장식"*/
    @JsonPaths("Name")
    private String name;

    /*"https://cdn-lostark.game.onstove.com/efui_iconatlas/bm_item/bm_item_01_168.png"*/
    @JsonPaths("Icon")
    private String icon;

    /*"고대"*/
    @JsonPaths("Grade")
    private String grade;

    /*"<P ALIGN='CENTER'><FONT COLOR='#E3C7A1'>+22 타락한 마수의 사멸 검</FONT></P>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_000.value")
    private String armoryNameTagFont;

    /*"<FONT SIZE='12'><FONT COLOR='#E3C7A1'>고대 검</FONT></FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_001.value.leftStr0")
    private String armoryItemGradeFont;

    /*"<FONT SIZE='14'>품질</FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_001.value.leftStr1")
    private String armoryQualityFont;

    /*"<FONT SIZE='14'>아이템 레벨 1635 (티어 3)</FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_001.value.leftStr2")
    private String armoryItemLevelFont;

    /*"https://cdn-lostark.game.onstove.com/efui_iconatlas/bl_item/bl_item_177.png"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_001.value.slotData.iconPath")
    private String armoryItemIconPath;

    /*"<FONT SIZE='12'>블레이드 전용</FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_002.value")
    private String armoryCharacterClassFont;

    /*"<FONT COLOR='#A9D0F5'>기본 효과</FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_005.value.Element_000")
    private String armoryBasicEffectFont;

    /*"무기 공격력 +67764"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_005.value.Element_001")
    private String armoryBasicEffectValueText;

    /*"<FONT COLOR='#A9D0F5'>추가 효과</FONT>"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_006.value.Element_000")
    private String armoryAdditionalEffectFont;

    /*"추가 피해 +27.30%"*/
    /*공통*/
    @JsonPaths("Tooltip.Element_006.value.Element_001")
    private String armoryAdditionalEffectValueText;

    /*장비 품질 수치 93*/
    /*공통*/
    @JsonPaths("Tooltip.Element_001.value.qualityValue")
    private Integer armoryQualityValue;

    /*"<FONT COLOR='#A9D0F5'>세트 효과 레벨</FONT>"*/
    /*공통*/
    /*엘릭서가 아예 없는 유저 기준*/
    @JsonPaths("Tooltip.Element_008.value.Element_000")
    private String armorySetEffectLevelFont1;

    /*"<FONT COLOR='#A9D0F5'>세트 효과 레벨</FONT>"*/
    /*공통*/
    /*엘릭서가 조금만 된 유저 기준*/
    @JsonPaths("Tooltip.Element_009.value.Element_000")
    private String armorySetEffectLevelFont2;

    /*"<FONT COLOR='#A9D0F5'>세트 효과 레벨</FONT>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    @JsonPaths("Tooltip.Element_010.value.Element_000")
    private String armorySetEffectLevelFont3;

    /*"사멸 <FONT COLOR='#FFD200'>Lv.3</FONT>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    @JsonPaths("Tooltip.Element_008.value.Element_001")
    private String armorySetEffectLevelValueFont1;

    /*"사멸 <FONT COLOR='#FFD200'>Lv.3</FONT>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    @JsonPaths("Tooltip.Element_009.value.Element_001")
    private String armorySetEffectLevelValueFont2;

    /*"사멸 <FONT COLOR='#FFD200'>Lv.3</FONT>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    @JsonPaths("Tooltip.Element_010.value.Element_001")
    private String armorySetEffectLevelValueFont3;

    /*"<font size='14'><font color='#91fe02'>2 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    @JsonPaths("Tooltip.Element_009.value.Element_001.topStr")
    private String armoryEnableTwoSetEffectFont1;

    /*"<font size='14'><font color='#91fe02'>2 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    @JsonPaths("Tooltip.Element_010.value.Element_001.topStr")
    private String armoryEnableTwoSetEffectFont2;

    /*"<font size='14'><font color='#91fe02'>2 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    @JsonPaths("Tooltip.Element_011.value.Element_001.topStr")
    private String armoryEnableTwoSetEffectFont3;

    /*"<font size='12'><font color='#ffffff'>치명타 피해가 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 치명타 피해 증가 수치가 <FONT COLOR='#99FF99'>65%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    @JsonPaths("Tooltip.Element_009.value.Element_001.contentStr.Element_000.contentStr")
    private String armoryEnableTwoSetEffectValueFont1;

    /*"<font size='12'><font color='#ffffff'>치명타 피해가 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 치명타 피해 증가 수치가 <FONT COLOR='#99FF99'>65%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    @JsonPaths("Tooltip.Element_010.value.Element_001.contentStr.Element_000.contentStr")
    private String armoryEnableTwoSetEffectValueFont2;

    /*"<font size='12'><font color='#ffffff'>치명타 피해가 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 치명타 피해 증가 수치가 <FONT COLOR='#99FF99'>65%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    //@JsonPaths("Tooltip.Element_011.value.Element_001.contentStr.Element_000.contentStr")
    //private String armoryEnableTwoSetEffectValueFont3;

    /*"<font size='14'><font color='#91fe02'>4 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    //@JsonPaths("Tooltip.Element_009.value.Element_002.topStr")
    //private String armoryEnableFourSetEffectFont1;

    /*"<font size='14'><font color='#91fe02'>4 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    //@JsonPaths("Tooltip.Element_010.value.Element_002.topStr")
    //private String armoryEnableFourSetEffectFont2;

    /*"<font size='14'><font color='#91fe02'>4 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    //@JsonPaths("Tooltip.Element_011.value.Element_002.topStr")
    //private String armoryEnableFourSetEffectFont3;

    /*"<font size='12'><font color='#ffffff'>치명타 적중률이 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    //@JsonPaths("Tooltip.Element_009.value.Element_002.contentStr.Element_000.contentStr")
    //private String armoryEnableFourSetEffectValueFont1;

    /*"<font size='12'><font color='#ffffff'>치명타 적중률이 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    //@JsonPaths("Tooltip.Element_010.value.Element_002.contentStr.Element_000.contentStr")
    //private String armoryEnableFourSetEffectValueFont2;

    /*"<font size='12'><font color='#ffffff'>치명타 적중률이 <FONT COLOR='#99FF99'>22%</FONT> 증가한다.</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    //@JsonPaths("Tooltip.Element_011.value.Element_002.contentStr.Element_000.contentStr")
    //private String armoryEnableFourSetEffectValueFont3;

    /*"<font size='14'><font color='#91fe02'>6 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    //@JsonPaths("Tooltip.Element_009.value.Element_003.topStr")
    //private String armoryEnableSixSetEffectFont1;

    /*"<font size='14'><font color='#91fe02'>6 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    //@JsonPaths("Tooltip.Element_010.value.Element_003.topStr")
    //private String armoryEnableSixSetEffectFont2;

    /*"<font size='14'><font color='#91fe02'>6 세트 효과<BR>[<font size='14'><font color='#aaaaaa'>Lv.1 / Lv.2 / <FONT COLOR='#FFD200'>Lv.3</FONT></font></font>]</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    //@JsonPaths("Tooltip.Element_011.value.Element_003.topStr")
    //private String armoryEnableSixSetEffectFont3;

    /*"<font size='12'><font color='#ffffff'>적에게 주는 피해가 <FONT COLOR='#99ff99'>9%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 적에게 주는 피해 수치가 <FONT COLOR='#99ff99'>26%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 아예 안된 유저 기준*/
    //@JsonPaths("Tooltip.Element_009.value.Element_003.contentStr.Element_000.contentStr")
    //private String armoryEnableSixSetEffectValueFont1;

    /*"<font size='12'><font color='#ffffff'>적에게 주는 피해가 <FONT COLOR='#99ff99'>9%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 적에게 주는 피해 수치가 <FONT COLOR='#99ff99'>26%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 조금 된 유저 기준*/
    //@JsonPaths("Tooltip.Element_010.value.Element_003.contentStr.Element_000.contentStr")
    //private String armoryEnableSixSetEffectValueFont2;

    /*"<font size='12'><font color='#ffffff'>적에게 주는 피해가 <FONT COLOR='#99ff99'>9%</FONT> 증가한다.<BR>백어택 및 헤드어택 공격 적중 시 적에게 주는 피해 수치가 <FONT COLOR='#99ff99'>26%</FONT>로 적용된다.</font></font>"*/
    /*공통*/
    /*엘릭서가 완성된 유저 기준*/
    //JsonPaths("Tooltip.Element_011.value.Element_003.contentStr.Element_000.contentStr")
    //private String armoryEnableSixSetEffectValueFont3;

    /*"<FONT SIZE='12' COLOR='#A9D0F5'>엘릭서 효과</FONT><br><font color='#91fe02'><FONT size='12'>지혜의 엘릭서</FONT></font>"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_008.value.Element_000.topStr")
    //private String defenseArmoryElixirEffectFont;

    /*"<FONT color='#FFD200'>[공용]</FONT> 무기 공격력 <FONT color='#FFD200'>Lv.4</FONT><br>무기 공격력 +1110"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_008.value.Element_000.contentStr.Element_000.contentStr")
    //private String defenseArmorylixirEffectFirstValueFont;

    /*"<FONT color='#FFD200'>[공용]</FONT> 무기 공격력 <FONT color='#FFD200'>Lv.4</FONT><br>무기 공격력 +1110"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_008.value.Element_000.contentStr.Element_001.contentStr")
    //private String defenseArmoryElixirEffectSecondValueFont;

    /*"<FONT SIZE='12' COLOR='#A9D0F5'>연성 추가 효과</FONT><br><FONT SIZE='12' color='#91FE02'>회심 (2단계)</FONT>"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_009.value.Element_000.topStr")
    //private String defenseArmoryElixirAdditionalEffectFont;

    /*"<font color='#ffffff'><font color='#FFD200'>1단계 : <FONT size='12'>지혜의 엘릭서</FONT> 레벨 합 35</font><br>공격이 치명타로 적중 시 적에게 주는 피해가 <FONT color='#D4FF88'>6%</FONT> 추가로 증가한다.</font>"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_009.value.Element_000.contentStr.Element_000.contentStr")
    //private String defenseArmoryElixirLevelOneEffectFont;

    /*"<font color='#ffffff'><font color='#FFD200'>2단계 : <FONT size='12'>지혜의 엘릭서</FONT> 레벨 합 40</font><br>1단계 효과가 강화되어, 공격이 치명타로 적중 시 적에게 주는 피해 증가 수치가 <FONT color='#D4FF88'>12%</FONT>로 적용된다.</font>"*/
    /*방어구 전용*/
    //@JsonPaths("Tooltip.Element_009.value.Element_000.contentStr.Element_001.contentStr")
    //private String defenseArmoryElixirLevelTwoEffectFont;

    /*장비 강화 수치*/
    //private String upgradeLevel;

    /*장비 레벨*/
    //private String itemLevel;
}
