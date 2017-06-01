package net.q2ek.compileinfo;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public interface ContentCreator {
	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	void write(String packageName, String name);

	public interface Input {
		Writer writer();

		Properties properties();
	}
}
