package net.q2ek.compileinfo.implementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.basics.ClassAttributes;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

public class FileObjectWriterTest {
	static final String TEST_CLASS_NAME = "CompileInfoTestOutput";
	static final String TEST_PACKAGE_NAME = "test.package";
	static final ClassAttributes PACKAGE_AND_CLASSNAME = ClassAttributes.of(
			TEST_PACKAGE_NAME,
			TEST_CLASS_NAME);

	@Test
	public void write_writes() {
		TestFileObject resource = new TestFileObject();
		PropertyWriterFactory pwFactory = appender -> new Base64PropertyWriter(
				appender, SourceCodeTestHelper.properties());
		SourceCodeGeneratorFactory factory = appender -> new Base64SourceCodeGenerator(
				PACKAGE_AND_CLASSNAME, appender, pwFactory);
		FileObjectWriter.writeFile(resource, factory);

		String actual = resource.bytesAsString();
		SourceCodeTestHelper.assertContent(actual, PACKAGE_AND_CLASSNAME);
	}

	@Test
	public void write_looksGood() {
		TestFileObject resource = new TestFileObject();
		PropertyWriterFactory pwFactory = appender -> new Base64PropertyWriter(
				appender, SourceCodeTestHelper.properties());
		SourceCodeGeneratorFactory factory = appender -> new Base64SourceCodeGenerator(
				PACKAGE_AND_CLASSNAME, appender, pwFactory);
		FileObjectWriter.writeFile(resource, factory);

		String actual = resource.bytesAsString();
		System.out.println(actual);
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

		String bytesAsString() {
			return new String(this.baos.toByteArray());
		}

	}
}
