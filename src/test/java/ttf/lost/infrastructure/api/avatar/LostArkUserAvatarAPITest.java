package ttf.lost.infrastructure.api.avatar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class LostArkUserAvatarAPITest {
	@Autowired
	private LostArkUserAvatarAPI lostArkUserAvatarAPI;

	@Test
	@DisplayName("착용 아바타 검색 API 성공 List 첫 번째 요소 비교")
	void success_findAvatar_API() {
		// given
		String nickname = "yebo";
		AvatarDto expectedAvatarDto = AvatarDto.builder()
			.type("무기 아바타")
			.name("4주년 용기 총 (이벤트)")
			.icon("https://cdn-lostark.game.onstove.com/efui_iconatlas/shop_icon/shop_icon_6243.png")
			.grade("영웅")
			.isSet("false")
			.isInner("false")
			.avatarNameFont("<P ALIGN='CENTER'><FONT COLOR='#ce43fc'>4주년 용기 총 (이벤트)</FONT></P>")
			.avatarGradeFont("<FONT SIZE='12'><FONT COLOR='#ce43fc'>영웅 총 아바타</FONT></FONT>")
			.classNameFont("<FONT SIZE='12'>건슬링어 전용</FONT>")
			.baseEffectFont1("<FONT COLOR='#A9D0F5'>기본 효과</FONT>")
			.baseEffectFont2("")
			.effect1("민첩 +1.00%")
			.effect2("")
			.tendencyText1("&tdc_courage 담력 : 5<BR>&tdc_charm 매력 : 10<BR><BR>")
			.tendencyText2("")
			.tendencyFont1(
				"<FONT SIZE='12'><FONT COLOR='#C24B46'>판매불가</FONT>, <FONT COLOR='#C24B46'>분해불가</FONT></FONT>")
			.tendencyFont2("")
			.characterClassName("")
			.build();
		// When
		List<AvatarDto> resultAvatarList = lostArkUserAvatarAPI.findAvatar(nickname);
		// Then
		AvatarDto firstAvatar = resultAvatarList.get(0);

		assertEquals(expectedAvatarDto.getType(), firstAvatar.getType());
		assertEquals(expectedAvatarDto.getName(), firstAvatar.getName());
		assertEquals(expectedAvatarDto.getIcon(), firstAvatar.getIcon());
		assertEquals(expectedAvatarDto.getGrade(), firstAvatar.getGrade());
		assertEquals(expectedAvatarDto.getIsSet(), firstAvatar.getIsSet());
		assertEquals(expectedAvatarDto.getIsInner(), firstAvatar.getIsInner());
		assertEquals(expectedAvatarDto.getAvatarNameFont(), firstAvatar.getAvatarNameFont());
		assertEquals(expectedAvatarDto.getAvatarGradeFont(), firstAvatar.getAvatarGradeFont());
		assertEquals(expectedAvatarDto.getClassNameFont(), firstAvatar.getClassNameFont());
		assertEquals(expectedAvatarDto.getBaseEffectFont1(), firstAvatar.getBaseEffectFont1());
		assertEquals(expectedAvatarDto.getBaseEffectFont2(), firstAvatar.getBaseEffectFont2());
		assertEquals(expectedAvatarDto.getEffect1(), firstAvatar.getEffect1());
		assertEquals(expectedAvatarDto.getEffect2(), firstAvatar.getEffect2());
		assertEquals(expectedAvatarDto.getTendencyText1(), firstAvatar.getTendencyText1());
		assertEquals(expectedAvatarDto.getTendencyFont1(), firstAvatar.getTendencyFont1());
		assertEquals(expectedAvatarDto.getCharacterClassName(), firstAvatar.getCharacterClassName());

	}
}