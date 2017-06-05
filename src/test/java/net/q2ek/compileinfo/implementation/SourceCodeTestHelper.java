package net.q2ek.compileinfo.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Properties;

public class SourceCodeTestHelper {

	static Properties testSystemProperties() {
		Properties result = new Properties();
		result.put("user.name", SourceCodeGeneratorTest.class.getSimpleName());
		result.put("hack", "\\\"hack");
		result.put("double\"hack", "hack\\\"hack");
		result.put("javadoc hack */ explosions", "nothing really");
		return result;
	}

	static Map<String, String> properties() {
		return PropertiesProcessor.of(testSystemProperties()).properties();
	}

	static void assertContent(String actual, ClassAttributes packageAndClassname) {
		assertThat(actual).contains("package " + packageAndClassname.packagename());
		assertThat(actual).contains("import java.util.Map;");
		assertThat(actual).contains("import java.time.ZonedDateTime;");
		assertThat(actual).contains("import java.time.LocalDateTime;");
		assertThat(actual).contains("@author");
		assertThat(actual).contains("class " + packageAndClassname.classname());
		assertThat(actual).contains("LocalDateTime localDateTime()");
		assertThat(actual).contains("ZonedDateTime zonedDateTime()");
	}

}
