package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.function.Function;

import javax.tools.FileObject;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.ConstructorParameters;

/**
 * Takes a {@link SourceCodeGenerator} and writes the output to a file. It also has
 * some factory methods.
 *
 * @author Edze Kruizinga
 */
public class FileWriter {
	private final Properties systemProperties = System.getProperties();
	private final Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory;

	/**
	 * Factory method
	 *
	 * @return a {@link FileWriter} using a {@link BasicSourceCodeGenerator}
	 */
	public static FileWriter basic() {
		return new FileWriter(input -> new BasicSourceCodeGenerator(input));
	}

	/**
	 * Factory method
	 *
	 * @return a {@link FileWriter} using a {@link Base64SourceCodeGenerator}
	 */
	public static FileWriter base64() {
		return new FileWriter(input -> new Base64SourceCodeGenerator(input));
	}

	private FileWriter(Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		this.factory = factory;
	}

	/**
	 * Factory method
	 *
	 * @return a {@link ConstructorParameters} using the given {@link Writer} and
	 *         {@link Properties}
	 */
	public static SourceCodeGenerator.ConstructorParameters constructorParametersOf(Writer writer, Properties properties) {
		return new SourceCodeGenerator.ConstructorParameters() {
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

	private SourceCodeGenerator sourceCodeGenerator(Writer writer, Properties properties) {
		return this.factory.apply(constructorParametersOf(writer, properties));
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	public void writeFile(PackageAndClassName packageAndClassname, FileObject resource) {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			sourceCodeGenerator(writer, this.systemProperties).write(packageAndClassname);
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + packageAndClassname, e);
		}
	}
}
