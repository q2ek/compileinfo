package net.q2ek.compileinfo.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConverter {

	public static Map<String, String> convert(Properties properties) {
		return new HashMap<>((Map) properties);
	}

	static Map<String, String> filter(Map<String, String> properties, String[] includeProperties) {
		Map<String, String> result = new HashMap<>();
		for (String key : includeProperties) {
			result.put(key, properties.get(key));
		}
		return result;
	}
}
