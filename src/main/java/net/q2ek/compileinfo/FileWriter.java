package net.q2ek.compileinfo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.function.Function;

import javax.tools.FileObject;

public class FileWriter {
	private final Properties systemProperties = System.getProperties();
	private final Function<ContentCreator.Input, ContentCreator> factory;

	static FileWriter basic() {
		return new FileWriter(input -> new BasicContentCreator(input));
	}

	static FileWriter base64() {
		return new FileWriter(input -> new Base64ContentCreator(input));
	}

	private FileWriter(Function<ContentCreator.Input, ContentCreator> factory) {
		this.factory = factory;
	}

	public static ContentCreator.Input inputOf(Writer writer, Properties properties) {
		return new ContentCreator.Input() {
			@Override
			public Writer writer() {
				return writer;
			}

			@Override
			public Properties properties() {
				return properties;
			}
		};
	}

	private ContentCreator contentCreator(Writer writer, Properties properties) {
		return this.factory.apply(inputOf(writer, properties));
	}

	/**
	 * @throws IOException
	 *             when FileObject cannot be used.
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	public void writeFile(String packageName, String name, FileObject resource) throws IOException {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			contentCreator(writer, this.systemProperties).write(packageName, name);
			writer.flush();
		}
	}
}
