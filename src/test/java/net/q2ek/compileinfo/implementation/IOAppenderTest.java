package net.q2ek.compileinfo.implementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.function.Consumer;

import javax.tools.SimpleJavaFileObject;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.PropertyMapCodeGenerator;
import net.q2ek.compileinfo.implementation.basics.PropertyWriterFactory;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class IOAppenderTest {
	private static final ClassAttributes CLASS_ATTRIBUTES = ClassAttributes.of(
			"test.package",
			"CompileInfoTestOutput");
	private final SourceCodeTestHelper helper = new SourceCodeTestHelper(IOAppenderTest.CLASS_ATTRIBUTES);

	@Test
	public void write_writes() {
		TestFileObject resource = new TestFileObject();
		SourceCodeGeneratorFactory factory = this.helper.scgFactory();
		IOAppender.writeFile(resource, factory);

		String actual = resource.bytesAsString();
		this.helper.assertContent(actual);
	}

	@Test
	public void write_looksGood() {
		TestFileObject resource = new TestFileObject();
		SourceCodeGeneratorFactory factory = this.helper.scgFactory();
		IOAppender.writeFile(resource, factory);

		resource.show(sequence -> System.out.print(sequence));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void explodingProperties_looksGood() {
		TestFileObject resource = new TestFileObject();
		SourceCodeGeneratorFactory factory = this.helper.scgFactory(explodingPropertyWriterFactory());
		IOAppender.writeFile(resource, factory);
	}

	@Test(expected = IOProblem.class)
	public void explodingFile_looksGood() {
		ExplodingFileObject resource = new ExplodingFileObject("This is a test.");
		SourceCodeGeneratorFactory factory = this.helper.scgFactory(explodingPropertyWriterFactory());
		IOAppender.writeFile(resource, factory);
	}

	private PropertyWriterFactory explodingPropertyWriterFactory() {
		return appender -> new PropertyMapCodeGenerator() {
			@Override
			public void write() {
				throw new UnsupportedOperationException("Boom");
			}

			@Override
			public boolean needsMapImport() {
				return false;
			}
		};
	}

	private class TestFileObject extends SimpleJavaFileObject {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		protected TestFileObject() {
			super(URI.create(""), Kind.SOURCE);
		}

		@Override
		public OutputStream openOutputStream() throws IOException {
			return this.baos;
		}

		void show(Consumer<CharSequence> consumer) {
			consumer.accept(bytesAsString());
		}

		private String bytesAsString() {
			return new String(this.baos.toByteArray());
		}

	}

	private class ExplodingFileObject extends SimpleJavaFileObject {
		private final String message;

		protected ExplodingFileObject(String message) {
			super(URI.create(""), Kind.SOURCE);
			this.message = message;
		}

		@Override
		public OutputStream openOutputStream() throws IOException {
			throw new IOException(this.message);
		}

	}
}
