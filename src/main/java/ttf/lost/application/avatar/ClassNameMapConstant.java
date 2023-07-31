package ttf.lost.application.avatar;

import java.util.Map;

public final class ClassNameMapConstant {
	private static final Map<String, Integer> classNameMap = Map.ofEntries(
		Map.entry("버서커", 1),
		Map.entry("디스트로이어", 2),
		Map.entry("워로드", 3),
		Map.entry("홀리나이트", 4),
		Map.entry("슬레이어", 5),
		Map.entry("아르카나", 6),
		Map.entry("서머너", 7),
		Map.entry("바드", 8),
		Map.entry("소서리스", 9),
		Map.entry("인파이터", 10),
		Map.entry("기공사", 11),
		Map.entry("창술사", 12),
		Map.entry("스트라이커", 13),
		Map.entry("블레이드", 14),
		Map.entry("데모닉", 15),
		Map.entry("리퍼", 16),
		Map.entry("소울이터", 17),
		Map.entry("호크아이", 18),
		Map.entry("데빌헌터", 19),
		Map.entry("블래스터", 20),
		Map.entry("스카우터", 21),
		Map.entry("건슬링어", 22),
		Map.entry("도화가", 23),
		Map.entry("기상술사", 24)
	);

	private static final Map<String, String> classLineMap = Map.ofEntries(
		Map.entry("전사(남)계열", "버서커"),
		Map.entry("마법사계열", "아르카나"),
		Map.entry("무도가(여)계열", "인파이터"),
		Map.entry("무도가(남)계열", "스트라이커"),
		Map.entry("암살자계열", "데모닉"),
		Map.entry("헌터(남)계열", "호크아이"),
		Map.entry("헌터(여)계열", "건슬링어"),
		Map.entry("스페셜리스트계열", "도화가")
	);

	public static String isClassName(String className) {
		// 예시 : 블레이드
		if (classNameMap.containsKey(className)) {
			return className;
		}
		// 예시 : 암살자계열
		return classLineMap.get(className);
	}
}
