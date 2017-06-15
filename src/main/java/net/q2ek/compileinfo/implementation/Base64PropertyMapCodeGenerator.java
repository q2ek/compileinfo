package net.q2ek.compileinfo.implementation;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.function.Consumer;

import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;

class Base64PropertyMapCodeGenerator implements PropertyMapCodeGenerator {
	private final SortedMap<String, String> properties;

	public Base64PropertyMapCodeGenerator(SortedMap<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public void imports(Consumer<CharSequence> consumer) {
		consumer.accept("import java.util.Base64;\n");
		consumer.accept("import java.util.Collections;\n");
		consumer.accept("import java.util.HashMap;\n");
		consumer.accept("import java.util.Map;\n");
	}

	@Override
	public void write(Consumer<CharSequence> consumer) {
		new MapCodeGenerator(consumer).write(this.properties);
	}

	private static class MapCodeGenerator {
		private final Encoder encoder = Base64.getEncoder();
		private final Consumer<CharSequence> consumer;

		private MapCodeGenerator(Consumer<CharSequence> consumer) {
			this.consumer = consumer;
		}

		void write(SortedMap<String, String> properties) {
			writePropertiesMap();
			writePropertiesMethod();
			writeCreateMap(properties);
			mapBuilder();
		}

		private void append(CharSequence value) {
			this.consumer.accept(value);
		}

		private void methodEnd() {
			append("    }\n\n");
		}

		private void writePropertiesMap() {
			append("    private static final Map<String, String> PROPERTIES = createMap();\n\n");
		}

		private void writePropertiesMethod() {
			append("    static Map<String, String> properties() {\n");
			append("        return PROPERTIES;\n");
			methodEnd();
		}

		private void writeCreateMap(SortedMap<String, String> properties) {
			append("    private static Map<String, String> createMap() {\n");
			append("        MapBuilder builder = MapBuilder.builder();\n");
			for (Entry<String, String> entry : properties.entrySet()) {
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
			append("    private static class MapBuilder {\n");
			append("        private final Base64.Decoder decoder = Base64.getDecoder();\n");
			append("        private final Map<String, String> map = new HashMap<>();\n");
			append("        \n");
			append("        static MapBuilder builder() {\n");
			append("            return new MapBuilder();\n");
			append("        }\n");
			append("        \n");
			append("        private void put(String key, String value) {\n");
			append("            map.put(new String(decoder.decode(key)), new String(decoder.decode(value)));\n");
			append("        }\n");
			append("        \n");
			append("        Map<String, String> build() {\n");
			append("            return Collections.unmodifiableMap(map);\n");
			append("        };\n");
			append("    }\n");
		}

		private String base64(String value) {
			return this.encoder.encodeToString(value.getBytes());
		}
	}
}
