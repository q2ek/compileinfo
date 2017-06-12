package net.q2ek.compileinfo.implementation;

import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

class PropertiesProcessor {
	private final SortedMap<String, String> properties;

	static PropertiesProcessor of(Properties properties) {
		return new PropertiesProcessor(convert(properties));
	}

	static PropertiesProcessor of(SortedMap<String, String> properties) {
		return new PropertiesProcessor(properties);
	}

	private PropertiesProcessor(SortedMap<String, String> properties) {
		this.properties = properties;
	}

	SortedMap<String, String> unfiltered() {
		return this.properties;
	}

	SortedMap<String, String> filtered(Iterable<String> includeProperties) {
		return filter(includeProperties);
	}

	private SortedMap<String, String> filter(Iterable<String> includeProperties) {
		SortedMap<String, String> result = new TreeMap<>();
		for (String key : includeProperties) {
			String value = this.properties.get(key);
			if (value != null) result.put(key, value);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static SortedMap<String, String> convert(Properties properties) {
		return new TreeMap<String, String>((Map) properties);
	}
}
