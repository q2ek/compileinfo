package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.tools.FileObject;

class FileObjectWriter {

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	static void writeFile(FileObject resource, SourceCodeGeneratorFactory factory) {
		try (OutputStream stream = resource.openOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(stream)) {
			Appender appender = new ProblemAppender(writer);
			factory.apply(appender).write();
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + resource.getName(), e);
		}
	}
}
