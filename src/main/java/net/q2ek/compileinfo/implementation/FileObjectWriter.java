package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.function.Function;

import javax.tools.FileObject;

import net.q2ek.compileinfo.implementation.SourceCodeGenerator.WriteParameters;

/**
 * Takes a {@link SourceCodeGenerator} and writes the output to a file. It also has
 * some factory methods.
 *
 * @author Edze Kruizinga
 */
class FileObjectWriter {
	private final Function<Writer, SourceCodeGenerator> factory;

	static FileObjectWriter base64() {
		return new FileObjectWriter(writer -> new Base64SourceCodeGenerator(writer));
	}

	private FileObjectWriter(
		Function<Writer, SourceCodeGenerator> factory) {
		this.factory = factory;
	}

	private SourceCodeGenerator sourceCodeGenerator(Writer writer) {
		return this.factory.apply(writer);
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	void writeFile(FileObject resource, WriteParameters writeParameters) {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			sourceCodeGenerator(writer).write(writeParameters);
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + writeParameters.packageAndClassName(), e);
		}
	}
}
