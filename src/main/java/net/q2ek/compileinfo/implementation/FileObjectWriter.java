package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
public class FileObjectWriter {
	private final Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory;

	public static FileObjectWriter base64() {
		return new FileObjectWriter(input -> new Base64SourceCodeGenerator(input));
	}

	private FileObjectWriter(
		Function<SourceCodeGenerator.ConstructorParameters, SourceCodeGenerator> factory) {
		this.factory = factory;
	}

	private SourceCodeGenerator sourceCodeGenerator(Writer writer) {
		return this.factory.apply(ConstructorParameters.of(writer));
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	public void writeFile(FileObject resource, WriteParameters writeParameters) {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			sourceCodeGenerator(writer).write(writeParameters);
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + writeParameters.packageAndClassName(), e);
		}
	}
}
