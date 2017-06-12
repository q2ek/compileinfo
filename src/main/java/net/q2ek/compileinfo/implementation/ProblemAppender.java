package net.q2ek.compileinfo.implementation;

import java.io.IOException;
import java.io.Writer;

class ProblemAppender implements Appender {
	private final Writer writer;

	ProblemAppender(Writer writer) {
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

	private void append(CharSequence value) {
		try {
			this.writer.append(value);
		} catch (IOException e) {
			throw new IOProblem("Could not append to writer " + this.writer + " value " + value, e);
		}
	}
}
