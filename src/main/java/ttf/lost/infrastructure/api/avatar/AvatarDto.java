package ttf.lost.infrastructure.api.avatar;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ttf.lost.common.annotation.JsonPaths;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AvatarDto {
	@JsonPaths("Type")
	private String type;
	@JsonPaths("Name")
	private String name;
	@JsonPaths("Icon")
	private String icon;
	@JsonPaths("Grade")
	private String grade;
	@JsonPaths("IsSet")
	private String isSet;
	@JsonPaths("IsInner")
	private String isInner;

	//**<P ALIGN='CENTER'><FONT COLOR='#F99200'>암흑 사제의 블레이드</FONT></P>*//*
	@JsonPaths("Tooltip.Element_000.value")
	private String avatarNameFont;
	//**"leftStr0": "<FONT SIZE='12'><FONT COLOR='#F99200'>전설 검 아바타</FONT></FONT>"*//*
	@JsonPaths("Tooltip.Element_001.value.leftStr0")
	private String avatarGradeFont;
	//**"<FONT SIZE='12'>블레이드 전용</FONT>"*//*
	@JsonPaths("Tooltip.Element_002.value")
	private String classNameFont;
	/**"<FONT COLOR='#A9D0F5'>기본 효과</FONT>"*/
	@JsonPaths("Tooltip.Element_005.value.Element_000")
	private String baseEffectFont1;
	// Inner = true일 경우 -> 필드명에 1이 붙음
	@JsonPaths("Tooltip.Element_006.value.Element_000")
	private String baseEffectFont2;

	/**"민첩 +2.00%"*/
	@JsonPaths("Tooltip.Element_005.value.Element_001")
	private String effect1;
	// Inner false일 경우 -> 필드명에 2가 붙음
	@JsonPaths("Tooltip.Element_006.value.Element_000")
	private String effect2;
	/**"&tdc_smart 지성 : 5<BR>&tdc_courage 담력 : 5<BR>&tdc_charm 매력 : 5<BR>&tdc_kind 친절 : 5<BR><BR>"*/
	@JsonPaths("Tooltip.Element_006.value.contentStr")
	private String tendencyText1;
	/**"<FONT COLOR='#A9D0F5'>성향</FONT>"*/
	@JsonPaths("Tooltip.Element_007.value.contentStr")
	private String tendencyText2;

	@JsonPaths("Tooltip.Element_007.value")
	private String tendencyFont1;
	@JsonPaths("Tooltip.Element_007.value.titleStr")
	private String tendencyFont2;
	@JsonPaths("ArmoryProfile.CharacterClassName")
	private String characterClassName;
	@JsonPaths("Tooltip")
	private String tooltip;
}


