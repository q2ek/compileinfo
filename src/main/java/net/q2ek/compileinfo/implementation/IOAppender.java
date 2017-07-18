package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;

import javax.tools.FileObject;

import net.q2ek.compileinfo.implementation.basics.Appender;
import net.q2ek.compileinfo.implementation.basics.SourceCodeGeneratorFactory;

class IOAppender implements Appender {
	private final Writer writer;

	IOAppender(Writer writer) {
		this.writer = writer;
	}

	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	@Override
	public void accept(CharSequence value) {
		append(value);
	}

	/**
	 * @throws IOProblem
	 *             when an {@link IOException} happens
	 */
	static void writeFile(FileObject fileObject, SourceCodeGeneratorFactory factory) {
		try (Writer writer = fileObject.openWriter()) {
			Appender appender = new IOAppender(writer);
			factory.apply(appender).write();
			writer.flush();
		} catch (IOException e) {
			throw new IOProblem("Could not write: " + fileObject.getName(), e);
		}
	}

	private void append(CharSequence value) {
		try {
			this.writer.append(value);
		} catch (IOException e) {
			throw new IOProblem("Could not append to writer " + this.writer + " value " + value, e);
		}
	}
}
