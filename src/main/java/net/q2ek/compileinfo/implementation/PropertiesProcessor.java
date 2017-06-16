package net.q2ek.compileinfo.implementation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

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

	Map<String, String> unfiltered() {
		return this.properties;
	}

	public Map<String, String> filtered(Predicate<String> predicate) {
		SortedMap<String, String> result = new TreeMap<>();
		for (Entry<String, String> entry : this.properties.entrySet()) {
			if (predicate.test(entry.getKey()))
				result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static SortedMap<String, String> convert(Properties properties) {
		return new TreeMap<String, String>((Map) properties);
	}
}
