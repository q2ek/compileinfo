package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.function.Function;

import javax.tools.FileObject;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.ConstructorParameters;
import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

/**
 * Takes a {@link SourceCodeGenerator} and writes the output to a file. It also has
 * some factory methods.
 *
 * @author Edze Kruizinga
 */
public class FileWriter {
	private final Properties properties;
	private final Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory;

	public static FileWriter basic() {
		return new FileWriter(input -> new BasicSourceCodeGenerator(input));
	}

	public static FileWriter base64() {
		return new FileWriter(input -> new Base64SourceCodeGenerator(input));
	}

	private FileWriter(Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		this(System.getProperties(), factory);
	}

	private FileWriter(
		Properties properties,
		Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		this.properties = properties;
		this.factory = factory;
	}

	private SourceCodeGenerator sourceCodeGenerator(Writer writer, Properties properties) {
		return this.factory.apply(ConstructorParameters.of(writer, properties));
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	public void writeFile(FileObject resource, WriteParameters writeParameters) {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			sourceCodeGenerator(writer, this.properties).write(writeParameters);
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + writeParameters.packageAndClassName(), e);
		}
	}
}
