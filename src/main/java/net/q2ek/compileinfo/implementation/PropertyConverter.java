package net.q2ek.compileinfo.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConverter {

	public static Map<String, String> convert(Properties properties) {
		return new HashMap<>((Map) properties);
	}

}
