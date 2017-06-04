package net.q2ek.compileinfo.implementation;

import java.io.CharArrayWriter;
import java.util.Properties;
import java.util.function.Function;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

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
	private static final PackageAndClassName PACKAGE_AND_CLASSNAME = PackageAndClassName.of(
			TEST_PACKAGE_NAME,
			TEST_CLASS_NAME);

	private void write_resultContainsPackageAndClass(
			Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		CharArrayWriter writer = new CharArrayWriter();
		WriteParameters writeParameters = WriteParameters.of(PACKAGE_AND_CLASSNAME, true);
		factory.apply(SourceCodeGenerator.ConstructorParameters.of(writer, PROPERTIES)).write(writeParameters);
		String actual = String.valueOf(writer.toCharArray());
		SourceCodeAsserter.assertContent(actual, PACKAGE_AND_CLASSNAME);
	}

	private void resultLooksNice(
			Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		CharArrayWriter writer = new CharArrayWriter();
		WriteParameters writeParameters = WriteParameters.of(PACKAGE_AND_CLASSNAME, true);
		factory.apply(SourceCodeGenerator.ConstructorParameters.of(writer, PROPERTIES)).write(writeParameters);
		String result = String.valueOf(writer.toCharArray());
		System.out.println(result);
	}

	@Test
	public void base64_write_resultContainsPackageAndClass() {
		write_resultContainsPackageAndClass(input -> new Base64SourceCodeGenerator(input));
	}

	@Test
	public void base64_resultLooksNice() {
		resultLooksNice(input -> new Base64SourceCodeGenerator(input));
	}
}
