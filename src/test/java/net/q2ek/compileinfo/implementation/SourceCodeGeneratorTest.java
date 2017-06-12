package net.q2ek.compileinfo.implementation;

import java.io.CharArrayWriter;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class SourceCodeGeneratorTest {
	private static final ClassAttributes CLASS_ATTRIBUTES = ClassAttributes.of(
			"test.package",
			"CompileInfoTestOutput");
	private final SourceCodeTestHelper helper = new SourceCodeTestHelper(SourceCodeGeneratorTest.CLASS_ATTRIBUTES);

	private void write_resultContainsPackageAndClass(SourceCodeGeneratorFactory factory) {
		CharArrayWriter writer = new CharArrayWriter();
		Appender appender = new IOAppender(writer);
		factory.apply(appender).write();
		String actual = String.valueOf(writer.toCharArray());
		this.helper.assertContent(actual);
	}

	private void resultLooksNice(SourceCodeGeneratorFactory factory) {
		Appender appender = sequence -> System.out.print(sequence);
		factory.apply(appender).write();
	}

	@Test
	public void current_write_resultContainsPackageAndClass() {
		SourceCodeGeneratorFactory factory = this.helper.scgFactory();

		write_resultContainsPackageAndClass(factory);
	}

	@Test
	public void current_resultLooksNice() {
		SourceCodeGeneratorFactory factory = this.helper.scgFactory();

		resultLooksNice(factory);
	}
}
