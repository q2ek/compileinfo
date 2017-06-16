package net.q2ek.compileinfo.implementation.basics;

import java.util.Map;

public interface MapDefinition {

	Map<String, String> map();

	CharSequence name();

	static MapDefinition of(Map<String, String> properties, String name) {
		return new MapDefinition() {
			@Override
			public Map<String, String> map() {
				return properties;
			}

			@Override
			public CharSequence name() {
				return name;
			}
		};
	}
}
