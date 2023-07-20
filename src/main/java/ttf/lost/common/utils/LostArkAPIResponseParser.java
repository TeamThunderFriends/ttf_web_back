package ttf.lost.common.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ttf.lost.common.annotation.JsonPaths;
import ttf.lost.common.exception.ErrorCode;
import ttf.lost.common.exception.GlobalException;

public class LostArkAPIResponseParser {

	public static <T> List<T> parseJson(String json, Class<T> clazz, ObjectMapper objectMapper) {
		List<T> resultList = new ArrayList<>();

		try {
			JsonNode rootNode = objectMapper.readTree(json);
			JsonNode armoryAvatarsNode = rootNode.path("ArmoryAvatars");
			resultList = parseJsonArrayNode(armoryAvatarsNode, clazz, objectMapper);
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR, e, "api 파싱 실패");
		}

		return resultList;
	}

	private static <T> List<T> parseJsonArrayNode(JsonNode arrayNode, Class<T> clazz, ObjectMapper objectMapper) throws
		IOException,
		InvocationTargetException,
		IllegalAccessException,
		InstantiationException,
		NoSuchMethodException {

		List<T> resultList = new ArrayList<>();

		for (JsonNode node : arrayNode) {
			T instance = parseJsonObjectNode(node, clazz, objectMapper);
			resultList.add(instance);
		}

		return resultList;
	}

	private static <T> T parseJsonObjectNode(JsonNode objectNode, Class<T> clazz, ObjectMapper objectMapper) throws
		IllegalAccessException,
		InvocationTargetException,
		InstantiationException,
		NoSuchMethodException,
		IOException {
		T instance = clazz.getDeclaredConstructor().newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			JsonPaths annotation = field.getAnnotation(JsonPaths.class);

			if (annotation == null) {
				continue;
			}

			String value = annotation.value();
			String[] split = value.split("\\.");

			JsonNode currentNode = objectNode;
			for (int i = 0; i < split.length - 1; i++) {
				String s = split[i];
				if (s.equals("Tooltip")) {
					currentNode = currentNode.path(s);
					currentNode = objectMapper.readTree(currentNode.asText());
					continue;
				}
				currentNode = currentNode.path(s);
			}
			String fieldValue = currentNode.path(split[split.length - 1]).asText();
			field.set(instance, fieldValue);
		}

		return instance;
	}

}
