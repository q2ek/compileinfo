package net.q2ek.compileinfo.implementation;

import java.io.CharArrayWriter;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class SourceCodeGeneratorTest {
	private static final String TEST_CLASS_NAME = "CompileInfoTestOutput";
	private static final String TEST_PACKAGE_NAME = "test.package";
	private static final ClassAttributes PACKAGE_AND_CLASSNAME = ClassAttributes.of(
			TEST_PACKAGE_NAME,
			TEST_CLASS_NAME);

	private void write_resultContainsPackageAndClass(SourceCodeGeneratorFactory factory) {
		CharArrayWriter writer = new CharArrayWriter();
		Appender appender = new IOAppender(writer);
		factory.apply(appender).write();
		String actual = String.valueOf(writer.toCharArray());
		SourceCodeTestHelper.assertContent(actual, PACKAGE_AND_CLASSNAME);
	}

	private void resultLooksNice(SourceCodeGeneratorFactory factory) {
		CharArrayWriter writer = new CharArrayWriter();
		Appender appender = new IOAppender(writer);
		factory.apply(appender).write();
		String result = String.valueOf(writer.toCharArray());
		System.out.println(result);
	}

	@Test
	public void current_write_resultContainsPackageAndClass() {
		PropertyWriterFactory factory = SourceCodeTestHelper.propertyWriterFactory();
		write_resultContainsPackageAndClass(
				appender -> new Base64SourceCodeGenerator(
						PACKAGE_AND_CLASSNAME, appender, factory));
	}

	@Test
	public void current_resultLooksNice() {
		PropertyWriterFactory factory = SourceCodeTestHelper.propertyWriterFactory();
		resultLooksNice(
				appender -> new Base64SourceCodeGenerator(
						PACKAGE_AND_CLASSNAME, appender, factory));
	}
}
