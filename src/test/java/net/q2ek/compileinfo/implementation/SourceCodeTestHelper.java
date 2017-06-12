package net.q2ek.compileinfo.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;
import java.util.SortedMap;

import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class SourceCodeTestHelper {

	private final ClassAttributes classAttributes;

	SourceCodeTestHelper(ClassAttributes classAttributes) {
		this.classAttributes = classAttributes;
	}

	static Properties testSystemProperties() {
		Properties result = new Properties();
		result.put("user.name", SourceCodeGeneratorTest.class.getSimpleName());
		result.put("hack", "\\\"hack");
		result.put("double\"hack", "hack\\\"hack");
		result.put("javadoc hack */ explosions", "nothing really");
		return result;
	}

	static SortedMap<String, String> properties() {
		return PropertiesProcessor.of(testSystemProperties()).unfiltered();
	}

	void assertContent(String actual) {
		assertThat(actual).contains("package " + this.classAttributes.packagename());
		assertThat(actual).contains("import java.util.Map;");
		assertThat(actual).contains("import java.time.ZonedDateTime;");
		assertThat(actual).contains("import java.time.LocalDateTime;");
		assertThat(actual).contains("@author");
		assertThat(actual).contains("class " + this.classAttributes.classname());
		assertThat(actual).contains("LocalDateTime localDateTime()");
		assertThat(actual).contains("ZonedDateTime zonedDateTime()");
	}

	static PropertyWriterFactory propertyWriterFactory() {
		return appender -> new Base64PropertyWriter(appender, SourceCodeTestHelper.properties());
	}

	SourceCodeGeneratorFactory scgFactory() {
		return scgFactory(propertyWriterFactory());
	}

	SourceCodeGeneratorFactory scgFactory(PropertyWriterFactory propertyWriterFactory) {
		return appender -> new ClassSourceCodeGenerator(
				this.classAttributes, appender, propertyWriterFactory);
	}
}
