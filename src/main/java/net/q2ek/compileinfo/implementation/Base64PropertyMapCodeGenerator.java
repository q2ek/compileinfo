package net.q2ek.compileinfo.implementation;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map.Entry;
import java.util.function.Consumer;

import net.q2ek.compileinfo.implementation.basics.MapDefinition;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;

class Base64PropertyMapCodeGenerator implements PropertyMapCodeGenerator {
	private final Iterable<MapDefinition> mapDefinitions;

	Base64PropertyMapCodeGenerator(MapDefinition... mapDefinitions) {
		this(Arrays.asList(mapDefinitions));
	}

	Base64PropertyMapCodeGenerator(Iterable<MapDefinition> mapDefinitions) {
		this.mapDefinitions = mapDefinitions;
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
		for (MapDefinition mapDefinition : this.mapDefinitions) {
			MapCodeGenerator mapCodeGenerator = new MapCodeGenerator(consumer, mapDefinition);
			mapCodeGenerator.write();
		}
		consumer.accept(mapBuilder);
	}

	private static class MapCodeGenerator {
		private final Encoder encoder = Base64.getEncoder();
		private final Consumer<CharSequence> consumer;
		private final MapDefinition definition;
		private final CharSequence mapMethodName;
		private final CharSequence buildMapMethodName;

		private MapCodeGenerator(Consumer<CharSequence> consumer, MapDefinition definition) {
			this.consumer = consumer;
			this.definition = definition;
			String camelCase = camelCase(this.definition.name().toString());
			this.mapMethodName = lowerCaseFirstChar(camelCase);
			this.buildMapMethodName = buildMapMethodName(camelCase);
		}

		void write() {
			writePropertiesMap();
			writePropertiesMethod();
			writeCreateMap();
		}

		private static CharSequence buildMapMethodName(String name) {
			return "build" + name + "Map";
		}

		private static String camelCase(String value) {
			String[] parts = value.split("_");
			StringBuilder sb = new StringBuilder();
			for (String part : parts) {
				sb.append(part.substring(0, 1).toUpperCase());
				sb.append(part.substring(1, part.length()).toLowerCase());
			}
			return sb.toString();
		}

		private static String lowerCaseFirstChar(String value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value.substring(0, 1).toLowerCase());
			sb.append(value.substring(1, value.length()));
			return sb.toString();
		}

		private void append(CharSequence value) {
			this.consumer.accept(value);
		}

		private void methodEnd() {
			append("    }\n\n");
		}

		private void writePropertiesMap() {
			append("    private static final Map<String, String> ");
			append(this.definition.name());
			append(" = ");
			append(this.buildMapMethodName);
			append("();\n\n");
		}

		private void writePropertiesMethod() {
			append("    static Map<String, String> ");
			append(this.mapMethodName);
			append("() {\n");
			append("        return ");
			append(this.definition.name());
			append(";\n");
			methodEnd();
		}

		private void writeCreateMap() {
			append("    private static Map<String, String> ");
			append(this.buildMapMethodName);
			append("() {\n");
			append("        MapBuilder builder = MapBuilder.builder();\n");
			for (Entry<String, String> entry : this.definition.map().entrySet()) {
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

		private String base64(String value) {
			return this.encoder.encodeToString(value.getBytes());
		}
	}

	private static String mapBuilder = "    private static class MapBuilder {\n"
				+ "        private final Base64.Decoder decoder = Base64.getDecoder();\n"
				+ "        private final Map<String, String> map = new HashMap<>();\n"
				+ "        \n"
				+ "        static MapBuilder builder() {\n"
				+ "            return new MapBuilder();\n"
				+ "        }\n"
				+ "        \n"
				+ "        private void put(String key, String value) {\n"
				+ "            map.put(new String(decoder.decode(key)), new String(decoder.decode(value)));\n"
				+ "        }\n"
				+ "        \n"
				+ "        Map<String, String> build() {\n"
				+ "            return Collections.unmodifiableMap(map);\n"
				+ "        };\n"
				+ "    }\n";
}
