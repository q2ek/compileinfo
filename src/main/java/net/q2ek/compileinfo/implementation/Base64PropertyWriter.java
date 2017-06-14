package net.q2ek.compileinfo.implementation;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map.Entry;
import java.util.SortedMap;

import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.PropertyWriter;

class Base64PropertyWriter implements PropertyWriter {
	private final Encoder encoder = Base64.getEncoder();

	private final Appender appender;
	private final SortedMap<String, String> properties;

	public Base64PropertyWriter(Appender appender, SortedMap<String, String> properties) {
		this.appender = appender;
		this.properties = properties;
	}

	@Override
	public boolean needsMapImport() {
		return true;
	}

	@Override
	public void write() {
		writeProperties();
	}

	private void append(CharSequence value) {
		this.appender.accept(value);
	}

	private void methodEnd() {
		append("    }\n\n");
	}

	private void writeProperties() {
		writePropertiesMap();
		writeGetMethod();
		writeKeySetMethod();
		writePropertiesMapCreater();
		mapBuilder();
	}

	private void writePropertiesMap() {
		append("    private static final Map<String, String> PROPERTIES = createMap();\n\n");
	}

	private void writeGetMethod() {
		append("    static String get(String key) {\n");
		append("        return PROPERTIES.get(key);\n");
		methodEnd();
	}

	private void writeKeySetMethod() {
		append("    static Map<String, String> properties() {\n");
		append("    	return PROPERTIES;\n");
		methodEnd();
	}

	private void writePropertiesMapCreater() {
		append("    private static Map<String, String> createMap() {\n");
		append("    	UnmodifiableMapBuilder builder = UnmodifiableMapBuilder.builder();\n");
		for (Entry<String, String> entry : this.properties.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
		append("        return builder.build();\n");
		methodEnd();
	}

	private void put(String key, String value) {
		String putFormat = "        builder.put(\"%s\",\n                    \"%s\");\n";
		String putCommand = String.format(putFormat, base64(key), base64(value));
		append(putCommand);
	}

	private void mapBuilder() {
		append("    private static class UnmodifiableMapBuilder {\n");
		append("    	private final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();\n");
		append("    	private final Map<String, String> map = new java.util.HashMap<>();\n");
		append("    	\n");
		append("    	static UnmodifiableMapBuilder builder() {\n");
		append("    		return new UnmodifiableMapBuilder();\n");
		append("    	}\n");
		append("    	\n");
		append("    	private void put(String key, String value) {\n");
		append("    		map.put(new String(decoder.decode(key)), new String(decoder.decode(value)));\n");
		append("    	}\n");
		append("    	\n");
		append("    	Map<String, String> build() {\n");
		append("    		return java.util.Collections.unmodifiableMap(map);\n");
		append("    	};\n");
		append("    }\n");
	}

	private String base64(String value) {
		return this.encoder.encodeToString(value.getBytes());
	}
}
