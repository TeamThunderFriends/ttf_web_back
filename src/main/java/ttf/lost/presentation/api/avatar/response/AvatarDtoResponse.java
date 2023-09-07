package ttf.lost.presentation.api.avatar.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarDtoResponse {
	private String type;
	private String name;
	private String icon;
	private String grade;
	private String isSet;
	private String isInner;

	//**<P ALIGN='CENTER'><FONT COLOR='#F99200'>암흑 사제의 블레이드</FONT></P>*//*
	private String avatarNameFont;
	//**"leftStr0": "<FONT SIZE='12'><FONT COLOR='#F99200'>전설 검 아바타</FONT></FONT>"*//*
	private String avatarGradeFont;
	//**"<FONT SIZE='12'>블레이드 전용</FONT>"*//*
	private String classNameFont;
	/**"<FONT COLOR='#A9D0F5'>기본 효과</FONT>"*/
	private String baseEffectFont1;
	// Inner = true일 경우 -> 필드명에 1이 붙음
	private String baseEffectFont2;

	/**"민첩 +2.00%"*/
	private String effect1;
	// Inner false일 경우 -> 필드명에 2가 붙음
	private String effect2;
	/**"&tdc_smart 지성 : 5<BR>&tdc_courage 담력 : 5<BR>&tdc_charm 매력 : 5<BR>&tdc_kind 친절 : 5<BR><BR>"*/
	private String tendencyText1;
	/**"<FONT COLOR='#A9D0F5'>성향</FONT>"*/
	private String tendencyText2;

	private String tendencyFont1; // 성향
	private String tendencyFont2;
	private Integer price;
}
