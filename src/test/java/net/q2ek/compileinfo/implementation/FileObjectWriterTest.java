package net.q2ek.compileinfo.implementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

import org.junit.Test;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

public class FileObjectWriterTest {
	private static final String TEST_CLASS_NAME = "CompileInfoTestOutput";
	private static final String TEST_PACKAGE_NAME = "test.package";
	private static final PackageAndClassName PACKAGE_AND_CLASSNAME = PackageAndClassName.of(
			TEST_PACKAGE_NAME,
			TEST_CLASS_NAME);

	FileObjectWriter fileWriter = FileObjectWriter.base64();

	@Test
	public void write_writes() {
		TestFileObject resource = new TestFileObject();
		WriteParameters writeParameters = WriteParameters.of(PACKAGE_AND_CLASSNAME, true);
		this.fileWriter.writeFile(resource, writeParameters);

		String actual = resource.bytesAsString();
		SourceCodeAsserter.assertContent(actual, PACKAGE_AND_CLASSNAME);
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

		byte[] bytes() {
			return this.baos.toByteArray();
		}

		String bytesAsString() {
			return new String(this.baos.toByteArray());
		}

	}
}
