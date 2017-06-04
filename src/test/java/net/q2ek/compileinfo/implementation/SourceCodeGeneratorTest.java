package net.q2ek.compileinfo.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.CharArrayWriter;
import java.util.Properties;
import java.util.function.Function;

import org.junit.Test;

//@SuppressWarnings("static-method")
public class SourceCodeGeneratorTest {
	private static final Properties PROPERTIES = testProperties();

	private static Properties testProperties() {
		Properties result = new Properties();
		result.put("user.name", SourceCodeGeneratorTest.class.getSimpleName());
		result.put("hack", "\\\"hack");
		result.put("double\"hack", "hack\\\"hack");
		result.put("javadoc hack */ explosions", "nothing really");
		return result;
	}

	private static final String TEST_CLASS_NAME = "CompileInfoTestOutput";
	private static final String TEST_PACKAGE_NAME = "test.package";

	private void write_resultContainsPackageAndClass(Function<SourceCodeGenerator.Input, SourceCodeGenerator> factory) {
		CharArrayWriter writer = new CharArrayWriter();
		factory.apply(FileWriter.inputOf(writer, PROPERTIES)).write(TEST_PACKAGE_NAME, TEST_CLASS_NAME);
		String actual = String.valueOf(writer.toCharArray());
		assertThat(actual).contains("package " + TEST_PACKAGE_NAME);
		assertThat(actual).contains("import java.util.HashMap;");
		assertThat(actual).contains("import java.util.Map;");
		assertThat(actual).contains("import java.util.Set;");
		assertThat(actual).contains("import java.time.LocalDateTime;");
		assertThat(actual).contains("@author");
		assertThat(actual).contains("class " + TEST_CLASS_NAME);
		assertThat(actual).contains("LocalDateTime localDateTime()");
	}

	private void resultLooksNice(Function<SourceCodeGenerator.Input, SourceCodeGenerator> factory) {
		CharArrayWriter writer = new CharArrayWriter();
		factory.apply(FileWriter.inputOf(writer, PROPERTIES)).write(TEST_PACKAGE_NAME, TEST_CLASS_NAME);
		String result = String.valueOf(writer.toCharArray());
		System.out.println(result);
	}

	@Test
	public void basic_write_resultContainsPackageAndClass() {
		write_resultContainsPackageAndClass(input -> new BasicSourceCodeGenerator(input));
	}

	@Test
	public void base64_write_resultContainsPackageAndClass() {
		write_resultContainsPackageAndClass(input -> new Base64SourceCodeGenerator(input));
	}

	@Test
	public void base64_resultLooksNice() {
		resultLooksNice(input -> new Base64SourceCodeGenerator(input));
	}

	@Test
	public void basic_resultLooksNice() {
		resultLooksNice(input -> new BasicSourceCodeGenerator(input));
	}
}