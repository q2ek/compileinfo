package net.q2ek.compileinfo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.function.Function;

import javax.tools.FileObject;

/**
 * Takes a {@link SourceCodeGenerator} and writes the output to a file. It also has
 * some factory methods.
 *
 * @author Edze Kruizinga
 */
public class FileWriter {
	private final Properties systemProperties = System.getProperties();
	private final Function<SourceCodeGenerator.Input, SourceCodeGenerator> factory;

	/**
	 * Factory method
	 *
	 * @return a {@link FileWriter} using a {@link BasicSourceCodeGenerator}
	 */
	static FileWriter basic() {
		return new FileWriter(input -> new BasicSourceCodeGenerator(input));
	}

	/**
	 * Factory method
	 *
	 * @return a {@link FileWriter} using a {@link Base64SourceCodeGenerator}
	 */
	static FileWriter base64() {
		return new FileWriter(input -> new Base64SourceCodeGenerator(input));
	}

	private FileWriter(Function<SourceCodeGenerator.Input, SourceCodeGenerator> factory) {
		this.factory = factory;
	}

	/**
	 * Factory method
	 *
	 * @return a {@link SourceCodeGenerator.Input} using the given {@link Writer} and
	 *         {@link Properties}
	 */
	public static SourceCodeGenerator.Input inputOf(Writer writer, Properties properties) {
		return new SourceCodeGenerator.Input() {
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

	private SourceCodeGenerator contentCreator(Writer writer, Properties properties) {
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
