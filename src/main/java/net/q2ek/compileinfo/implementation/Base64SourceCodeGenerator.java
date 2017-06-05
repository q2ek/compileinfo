package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.q2ek.compileinfo.CompileInfo;
import net.q2ek.compileinfo.CompileInfoAnnotationProcessor;

/**
 * Generates the java class source code using {@link Base64} encoding to store
 * data.
 *
 * @see SourceCodeGenerator
 *
 * @author Edze Kruizinga
 */
class Base64SourceCodeGenerator implements SourceCodeGenerator {
	private final Writer writer;
	private final Encoder encoder = Base64.getEncoder();

	Base64SourceCodeGenerator(Writer writer) {
		this.writer = writer;
	}

	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	private void append(CharSequence value) {
		try {
			this.writer.append(value);
		} catch (IOException e) {
			throw new IOProblem("Could not append to writer " + this.writer + " value " + value, e);
		}
	}

	@Override
	public void write(WriteParameters parameters) {
		packageDeclaration(parameters.packageAndClassName().packagename());
		imports();
		classJavaDoc();
		classDeclaration(parameters.packageAndClassName().classname());
		isoDateTimeConstant();
		zonedDateTimeConstant();
		writeLocalDateTime();
		writeZonedDateTime();
		if (parameters.addProperties()) {
			writeProperties(parameters.properties());
		}
		classEnd();
	}

	private void writeProperties(Map<String, String> properties) {
		writePropertiesMap();
		writeGetMethod();
		writeKeySetMethod();
		writePropertiesMapCreater(properties);
		mapBuilder();
	}

	private void packageDeclaration(String packagename) {
		append("package " + packagename + ";\n\n");
	}

	private void imports() {
		append("import java.util.Map;\n");
		append("import java.util.Set;\n");
		append("import java.time.LocalDateTime;\n");
		append("import java.time.ZonedDateTime;\n");
		append("\n");
	}

	private void classJavaDoc() {
		append("/**\n");
		append(" * @author Generated by " + CompileInfoAnnotationProcessor.class.getCanonicalName() + "\n");
		append(" * @see " + CompileInfo.class.getSimpleName() + "\n");
		append(" */\n");
	}

	private void classDeclaration(String classname) {
		append("public class " + classname + "\n");
		append("{\n");
	}

	private void classEnd() {
		append("}\n");
	}

	private void isoDateTimeConstant() {
		append("    static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse(\"");
		append(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		append("\");\n");
		append("    \n");
	}

	private void zonedDateTimeConstant() {
		append("    static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.parse(\"");
		append(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		append("\");\n");
		append("    \n");
	}

	private void writeLocalDateTime() {
		append("    static LocalDateTime localDateTime() {\n");
		append("        return LOCAL_DATE_TIME;\n");
		methodEnd();
	}

	private void writeZonedDateTime() {
		append("    static ZonedDateTime zonedDateTime() {\n");
		append("        return ZONED_DATE_TIME;\n");
		methodEnd();
	}

	private void writePropertiesMap() {
		append("    static final Map<String, String> PROPERTIES = createMap();\n\n");
	}

	private void writePropertiesMapCreater(Map<String, String> properties) {
		append("    private static Map<String, String> createMap() {\n");
		append("    	MapBuilder builder = MapBuilder.builder();\n");
		List<String> keys = sortedKeys(properties);
		for (String key : keys) {
			putKeyValue(properties, key);
		}
		append("        return builder.build();\n");
		methodEnd();
	}

	private void putKeyValue(Map<String, String> properties, String key) {
		String value = properties.get(key);
		String putFormat = "        builder.put(\"%s\",\n                    \"%s\");\n";
		String putCommand = String.format(putFormat, base64(key), base64(value));
		append(putCommand);
	}

	private void mapBuilder() {
		append("    private static class MapBuilder {\n");
		append("    	private final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();\n");
		append("    	private final Map<String, String> result = new java.util.HashMap<>();\n");
		append("    	\n");
		append("    	static MapBuilder builder() {\n");
		append("    		return new MapBuilder();\n");
		append("    	}\n");
		append("    	\n");
		append("    	private void put(String key, String value) {\n");
		append("    		result.put(new String(decoder.decode(key)), new String(decoder.decode(value)));\n");
		append("    	}\n");
		append("    	\n");
		append("    	Map<String, String> build() {\n");
		append("    		return result;\n");
		append("    	};\n");
		append("    }\n");
	}

	private String base64(String value) {
		return this.encoder.encodeToString(value.getBytes());
	}

	private static List<String> sortedKeys(Map<String, String> properties) {
		Set<String> keySet = properties.keySet();
		List<String> keys = new ArrayList<>(keySet.size());
		keySet.forEach(key -> keys.add(key.toString()));
		Collections.sort(keys);
		return keys;
	}

	private void writeGetMethod() {
		append("    static String get(String key) {\n");
		append("        return PROPERTIES.get(key);\n");
		methodEnd();
	}

	private void writeKeySetMethod() {
		append("    static Set<String> keySet() {\n");
		append("        return PROPERTIES.keySet();\n");
		methodEnd();
	}

	private void methodEnd() {
		append("    }\n\n");
	}
}
