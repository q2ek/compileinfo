package net.q2ek.compileinfo;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

/**
 * Any class implementing this interface generates java class source code
 * content. This would then presumably be put into a file.
 *
 * @author Edze Kruizinga
 */
public interface SourceCodeGenerator {
	/**
	 * @throws IOProblem
	 *             when {@link IOException} happens
	 */
	void write(String packageName, String name);

	/**
	 * Constructor parameter for classes that implement a ContentCreator.
	 */
	public interface Input {
		Writer writer();

		Properties properties();
	}
}
