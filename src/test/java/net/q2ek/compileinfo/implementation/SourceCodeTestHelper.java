package net.q2ek.compileinfo.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Properties;

import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.MapDefinition;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class SourceCodeTestHelper {
	private final Class<?> annotationProcessor;
	private final ClassAttributes classAttributes;

	SourceCodeTestHelper(ClassAttributes classAttributes) {
		this(SourceCodeTestHelper.class, classAttributes);
	}

	SourceCodeTestHelper(Class<?> annotationProcessor, ClassAttributes classAttributes) {
		this.annotationProcessor = annotationProcessor;
		this.classAttributes = classAttributes;
	}

	static Properties testSystemProperties() {
		Properties result = new Properties();
		result.put("user.name", SourceCodeGeneratorTest.class.getSimpleName());
		result.put("java.version", SourceCodeGeneratorTest.class.getSimpleName());
		result.put("hack", "\\\"hack");
		result.put("double\"hack", "hack\\\"hack");
		result.put("javadoc hack */ explosions", "nothing really");
		return result;
	}

	static Map<String, String> properties() {
		return PropertiesProcessor.of(testSystemProperties()).unfiltered();
	}

	void assertContent(String actual) {
		assertThat(actual).contains("package " + this.classAttributes.packagename());
		assertThat(actual).contains("import javax.annotation.Generated;");
		assertThat(actual).contains("import java.time.ZonedDateTime;");
		assertThat(actual).contains("import java.util.Map;");
		assertThat(actual).contains("@Generated");
		assertThat(actual).contains(this.annotationProcessor.getCanonicalName());
		assertThat(actual).contains("class " + this.classAttributes.classname());
		assertThat(actual).contains("ZonedDateTime zonedDateTime()");
	}

	static PropertyMapCodeGenerator propertyMapGenerator() {
		return new Base64PropertyMapCodeGenerator(
				MapDefinition.of(SourceCodeTestHelper.properties(), "TEST_PROPERTIES"),
				MapDefinition.of(SourceCodeTestHelper.properties(), "MORE_PROPERTIES"));
	}

	SourceCodeGeneratorFactory scgFactory() {
		return scgFactory(propertyMapGenerator());
	}

	SourceCodeGeneratorFactory scgFactory(PropertyMapCodeGenerator propertyMapGenerator) {
		return appender -> new ClassSourceCodeGenerator(
				this.annotationProcessor, this.classAttributes, appender, propertyMapGenerator);
	}
}
