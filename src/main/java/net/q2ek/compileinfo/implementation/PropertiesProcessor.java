package net.q2ek.compileinfo.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class PropertiesProcessor {
	private final Map<String, String> properties;

	static PropertiesProcessor of(Properties properties) {
		return new PropertiesProcessor(convert(properties));
	}

	static PropertiesProcessor of(Map<String, String> properties) {
		return new PropertiesProcessor(properties);
	}

	private PropertiesProcessor(Map<String, String> properties) {
		this.properties = properties;
	}

	Map<String, String> properties() {
		return this.properties;
	}

	Map<String, String> filtered(String[] includeProperties) {
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

	private static Map<String, String> convert(Properties properties) {
		return new HashMap<>((Map) properties);
	}
}
