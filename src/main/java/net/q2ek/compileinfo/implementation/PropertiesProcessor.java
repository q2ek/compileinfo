package net.q2ek.compileinfo.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesProcessor {
	private final Map<String, String> properties;

	PropertiesProcessor(Map<String, String> properties) {
		this.properties = properties;
	}

	public Map<String, String> properties() {
		return this.properties;
	}

	private static Map<String, String> convert(Properties properties) {
		return new HashMap<>((Map) properties);
	}

	public Map<String, String> filtered(String[] includeProperties) {
		if (includeProperties.length == 0) {
			return properties();
		}
		return filter(includeProperties);
	}

	private Map<String, String> filter(String[] includeProperties) {
		Map<String, String> result = new HashMap<>();
		for (String key : includeProperties) {
			result.put(key, this.properties.get(key));
		}
		return result;
	}

	public static PropertiesProcessor of(Properties properties) {
		return new PropertiesProcessor(convert(properties));
	}

	public static PropertiesProcessor of(Map<String, String> properties) {
		return new PropertiesProcessor(properties);
	}
}
